/* 회원가입 유효성 검사 규칙*/
function signupForm(){
	console.log("회원가입 폼")
	/* DOM 으로 form을 연결 */
	let form = document.signup_form;
	if(form.id.value === ""){
		alert("아이디를 입력하세요");
		/* 커서를 id로 지정 */
		form.id.focus();
	}else if(form.pw.value === ""){
		alert("비밀번호를 입력하세요");
		/* 커서를 pw로 지정 */
		form.pw.focus();
	}else if(form.mail.value === ""){
		alert("Email을 입력하세요");
		/* 커서를 email로 지정 */
		form.mail.focus();
	}else if(form.phone.value === ""){
		alert("전화 번호를 입력하세요");
		/* 커서를 phone으로 지정 */
		form.phone.focus();
	}else{
		/* 데이터 전송 */
		form.submit();
	}
}

// writeBtn
// 회원이 로그인 된 상태 => 글쓰기 가능
// 회원이 비로그인 상태 => 로그인 후 사용가능 메시지 출력
	let write = document.getElementById("writeBtn");
	write.addEventListener("click", function(){
		const isLogin = this.dataset.login;
		
		if(isLogin === "true"){
			/* 로그인 된 상태 => board/write */
			location.href = "/board/write";
		}else{
			/* 로그인이 안된 상태 => alert창 띄우기 => 로그인 폼으로 이동 /member/login */
			alert("로그인 후 사용 가능");
			location.href = "/member/login";
		}
	})