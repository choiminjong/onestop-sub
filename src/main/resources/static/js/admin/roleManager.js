
async function registerRole() {
    /*
       registerRole 롤 등록
    */

    let data = {
        roleName: document.querySelector('#roleName').value,
        roleDesc: document.querySelector('#roleDesc').value
    }

    let url = "/admin/roles"
    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(data)
    });

    let error = await response.json();

    if (error['status'] == '200') {
        alert("롤을 추가했습니다.");
        location.href = "/admin/roles";
    } else {
        alert("롤 추가를 실패했습니다.");
    }
}

async function modifyRole() {
    /*
       modifyRole 롤 수정
    */

    let roleId = document.querySelector('#roleId').value;

    let data = {
        roleName: document.querySelector('#roleName').value,
        roleDesc: document.querySelector('#roleDesc').value
    }

    let url = "/admin/roles/"+roleId
    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(data)
    });

    let error = await response.json();

    if (error['status'] == '200') {
        alert("롤 수정했습니다.");
        location.href = "/admin/roles";
    } else {
        alert("롤 수정을 실패했습니다.");
    }
}




