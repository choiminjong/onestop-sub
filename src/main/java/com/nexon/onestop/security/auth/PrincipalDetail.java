package com.nexon.onestop.security.auth;

import com.nexon.onestop.domain.entity.Account;
import com.nexon.onestop.domain.entity.Role;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Data
public class PrincipalDetail implements UserDetails {

    private Account account;

    public PrincipalDetail(Account account) {
        this.account = account;
    }

    //사용자 권한을 시큐리티 포맷에 맞춰 생성합니다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<Role> roles = account.getUserRoles();
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        for (Role role : roles) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            grantList.add(authority);
        }

        //System.out.println("grantList = " + grantList);
        return grantList;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
