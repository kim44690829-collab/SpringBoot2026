/*회원가임 유효성 검사*/
function signupForm() {
    console.log('회원가입폼');

    let form = document.signup_form;
    if (form.id.value === '') {
        alert('새로운 id 입력');
        form.id.focus();
    } else if (form.pw.value === '') {
        alert('새로운 pw 입력!!');
        form.pw.focus();
    } else if (form.mail.value === '') {
        alert('새로운 email 입력!!');
        form.mail.focus();
    } else if (form.phone.value === '') {
        alert('새로운 전화번호 입력!!');
        form.phone.focus();
    } else {
        form.submit();
    }
}

// 회원이 로그인 된 상태면 글쓰기 가능, 아니면 '로그인 후 사용가능' 메시지 출력
    let write = document.getElementById("writeBtn");
	write.addEventListener("click", function(){
		const isLogin = this.dataset.login;
		
		if(isLogin === true){
			/* 로그인이 된 상태 => 글쓰기 => /board/write*/
			location.href = "/board/write"
		}else{
			/* 로그인이 안된 상태 => 글쓰기 안됨 => alert창띄우기 */
			/* 로그인 폼으로 이동한다. => /member/login*/
			alert("로그인 후 사용가능");
			location.href="/member/login";
		}
	})
	
	
	
	
	

