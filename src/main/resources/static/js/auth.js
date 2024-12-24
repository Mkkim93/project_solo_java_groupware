fetch("/reissue", {
    method: "POST",
    credentials: 'same-origin'  // 쿠키를 서버로 전송하려면 이 옵션을 추가합니다
})
    .then(response => response.json())
    .then(data => {
    console.log(data);
    })
    .catch(error => {
    console.log('error', error);
});
