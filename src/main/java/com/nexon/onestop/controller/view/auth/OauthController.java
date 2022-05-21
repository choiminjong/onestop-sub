package com.nexon.onestop.controller.view.auth;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexon.onestop.domain.dto.AccountDto;
import com.nexon.onestop.domain.dto.KakaoProfileDto;
import com.nexon.onestop.domain.dto.OAuthTokenDto;
import com.nexon.onestop.domain.entity.Account;
import com.nexon.onestop.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Controller
@RequestMapping("/auth")
public class OauthController {

    @Value("${oauth.kakao.authorize}")
    private String authorize;

    @Value("${oauth.kakao.client_id}")
    private String client_id;

    @Value("${oauth.kakao.redirect_uri}")
    private String redirect_uri;

    @Value("${oauth.kakao.oauth_token}")
    private String oauth_token;

    @Value("${oauth.kakao.user_into}")
    private String user_into;

    @Value("${oauth.oauthkey}")
    private String oauthkey;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/kakao")
    public String kakaoLogin() {
        return "redirect:"+authorize+"?client_id="+client_id+"&redirect_uri="+redirect_uri+"&response_type=code";
    }
    /*
     @ResponseBody 어노테이션 HTTP요청 바디를 자바객체로 변환하고 자바객체를 다시 HTTP 응답 바디로 변환해준다.
    */
    @GetMapping("/kakao/callback")
    public @ResponseBody String kakaoCallback(String code){

        //POST 방식으로 key=value 데이터를 카카오 로그인 서버로 전달
        RestTemplate rt = new RestTemplate();

        //HttpHeaders 오브젝트 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성
        //params.add는 변수를 따로 설정해서 사용해야하는데 직관적으로 이해하기위해서 다이렉트로 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id",client_id);
        params.add("redirect_uri",redirect_uri);
        params.add("code",code);

        //HttpHeaders와 HttpBody 오브젝트를 하나의 오브젝트로 담기
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
                new HttpEntity<>(params,httpHeaders);

        //Http 요청하기 -> Post 방식으로 요청하면 response 변수로 데이터를 응답 받음
        ResponseEntity response = rt.exchange(
                oauth_token, //어떤 곳으로 전달할건지
                HttpMethod.POST,    //메소드 타입
                kakaoTokenRequest,  // HttpEntity 객체가
                String.class        //응답 데이터 타입
        );

        //Gson,Json Simple, ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthTokenDto oauthToken = null;

        try {
            //JSON 데이터를 다중으로 받을때는 List 으로 받을수 있다.
            //List<OAuthToken> oauthToken = objectMapper.readValue(jsonArr, OAuthToken.class {});
            oauthToken = objectMapper.readValue((String) response.getBody(), OAuthTokenDto.class);
        }catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("카카오 엑세스 토큰 = " + oauthToken.getAccess_token() );

        //카카오 프로필 데이터 요청
        RestTemplate rt2 = new RestTemplate();

        //HttpHeaders 오브젝트 생성
        HttpHeaders httpHeaders2 = new HttpHeaders();
        httpHeaders2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
        httpHeaders2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeaders 오브젝트를 하나의 오브젝트로 담기
        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest =
                new HttpEntity<>(httpHeaders2);

        //Http 요청하기 -> Post 방식으로 요청하면 response 변수로 데이터를 응답 받음
        ResponseEntity response2 = rt2.exchange(
                user_into, //어떤 곳으로 전달할건지
                HttpMethod.POST,    //메소드 타입
                kakaoProfileRequest,  // HttpEntity 객체가
                String.class        //응답 데이터 타입
        );

        System.out.println("response2 = " + response2);

        //Gson,Json Simple, ObjectMapper
        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfileDto kakaoProfile = null;

        try {
            kakaoProfile = objectMapper2.readValue((String) response2.getBody(), KakaoProfileDto.class);
        }catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("카카오 아이디 = " + kakaoProfile.getId());
        System.out.println("카카오 이메일 = " + kakaoProfile.getKakao_account().getEmail());

        //builder 객체 생성
        // 을 깔끔하고 유연하게 하기 위한 기법
        Account kakaUser = Account.builder()
                .username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
                .password(oauthkey)  //UUID 타입을 문자열로 만들어 리턴한다.
                .email(kakaoProfile.getKakao_account().getEmail())
                .build();


        //가입자 혹은 비가입자인지 체크
        Account originUser = userServiceImpl.accoutSearch(kakaUser.getEmail());

        System.out.println("kakaUser builder = " + kakaUser);

        if(originUser.getUsername() == null){
            //회원가입
            System.out.println(" 기존 회원입니다. ");
            userServiceImpl.createUser(kakaUser);
        }


        System.out.println("kakaUser.getUsername() = " + kakaUser.getUsername());
        System.out.println("kakaUser.getPassword() = " + kakaUser.getPassword());


        //로그인 처리
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(kakaUser.getUsername(),oauthkey)
        );

        //인증정보를 저장해서 가지고있는 객체입니다. 해당 객체를 활용해서 인증정보를 뻇어 사용할 수 있습니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";


    }

}
