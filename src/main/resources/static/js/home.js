function viewSideBar() {
    const url = `api/sidebar`;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            console.log('data', data);
            renderProfile(data);
        }).catch(error => {
            console.log('error', error);
    })
}

function renderProfile(data) {
    const dashBoard = document.getElementById('sidebar');
    const menu = document.getElementById('menu');
    dashBoard.innerHTML = `
        <div class="sidebar-section" id="sidebar">
            <div class="profile-section" id="profile">
                <h3 type="text" id="empName" name="empName">${data.empName}</h3>
                <h3 type="text" id="empRank" name="empName">${data.empRank}</h3>
            </div>
        </div>
        <ul class="menu">
            <li><a href="/hr/home"></a></li>    
            <li><a href="/hr/board/all/list"></a></li>    
            <li><a href="/todo/detail"></a></li>    
            <li><a href="/mail/list"></a></li>    
            <li><a href="#"></a></li>    
            <li><a href="#"></a></li>    
            <li><a href="#"></a></li>    
            <li><a href="#"></a></li>    
        </ul>
    `;
}