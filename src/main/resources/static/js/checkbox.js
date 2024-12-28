const ckAll = document.querySelector('.ck-all');
const ckArr = document.querySelectorAll('.ck');

// 체크박스를 선택하면 배열에 id 값을 담는다
let selectedMailBoxIds = [];
ckAll.addEventListener("click", () => {

    ckArr.forEach(ck => {
        ck.checked = ckAll.checked;
        if (ck.checked) {
            selectedMailBoxIds.push(ck.value);
        } else {
            selectedMailBoxIds = selectedMailBoxIds.filter(id => id !== ck.value);
        }
    })
});

/*click 제어*/
ckArr.forEach(ck => {
    ck.addEventListener("click", () => {
        let checkedCount = 0;
        ckArr.forEach(ck => {
            if (ck.checked == true) {
                selectedMailBoxIds.push(ck.value);
            } else {
                selectedMailBoxIds = selectedMailBoxIds.filter(id => id !== ck.value);
            }
            ckAll.checked = ckArr.length === selectedMailBoxIds.length;
            ckAll.indeterminate = selectedMailBoxIds.length > 0 && selectedMailBoxIds.length < ckArr.length;
        })

        if (checkedCount == ckArr.length) {
            ckAll.checked = true;
        } else {
            ckAll.checked = false;
        }
    });
})

// mailTableHead와 mailTableBody는 DOM이 로드된 후에만 정의할 수 있습니다.
const mailTableHead = document.querySelector('#mailTableHead');
const mailTableBody = document.querySelector('#mailTableBody');

// 전체 선택 체크박스에 대한 이벤트 처리 (이벤트 위임 사용)
mailTableHead.addEventListener('click', (e) => {
    if (e.target && e.target.classList.contains('ck-all')) {
        const ckArr = document.querySelectorAll('.ck');
        ckArr.forEach(ck => {
            ck.checked = e.target.checked;
        });
    }
});

// 개별 체크박스에 대한 이벤트 처리 (이벤트 위임 사용)
mailTableBody.addEventListener('click', (e) => {
    if (e.target && e.target.classList.contains('ck')) {
        const ckAll = document.querySelector('.ck-all');
        const ckArr = document.querySelectorAll('.ck');

        // 모든 체크박스가 체크되었는지 확인
        const allChecked = [...ckArr].every(ck => ck.checked);
        const someChecked = [...ckArr].some(ck => ck.checked);

        // 전체 체크박스의 상태를 업데이트
        ckAll.checked = allChecked;
        ckAll.indeterminate = !allChecked && someChecked;
    }
});

const deleteButton = document.querySelector('#deleteButton');
deleteButton.addEventListener("click", () => {
    const form = document.querySelector("#deleteForm");

    // 선택된 mail.id를 hidden input으로 폼에 추가
    const mailIdsInput = document.createElement('input');
    mailIdsInput.setAttribute('name', 'mailBoxIds');  // 서버에서 받을 name 속성
    /*mailIdsInput.setAttribute('value', selectedMailBoxIds.join(','));  // 배열을 콤마로 구분하여 전달*/

    form.appendChild(mailIdsInput);
    form.submit();
    alert('메일이 정상적으로 삭제 되었습니다.')// 폼 제출
});
