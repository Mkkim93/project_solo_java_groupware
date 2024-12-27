function renderCategory(selectCategory) {


    if (selectCategory == 'register') {
        updateRegisterForm();
    }
}

function updateRegisterForm () {
    const contentArea = document.getElementById('content-area');
    const url = `/api/register`;
    fetch(url, {
    }).then(response => response.json())
        .then(data => {
            contentArea.innerHTML = '';
            console.log('data', data);
            contentArea.innerHTML = `
                <h2>직원 등록</h2>
                <form id="register-form" action="/admin/registerProc" method="post">
                <label for="empEmail">이메일</label>
                <input type="email" name="empEmail" id="empEmail"></br>
                
                <label for="empPass">비밀번호</label>
                <input type="password" name="empPass" id="empPass"></br>
                
                <label for="departId">부서</label>
                <select name="departId" id="departId">
                    <option value="1">기획팀</option>
                    <option value="2">인사팀</option>
                    <option value="3">총무팀</option>
                    <option value="4">개발팀</option>
                </select>
            
                <button type="submit">등록</button>
                </form>
            `;
        }).catch(error => {
            console.log('error', error);
        contentArea.innerHTML = `<p>오류 발생: 데이터를 불러올 수 없습니다.</p>`;
    })
}