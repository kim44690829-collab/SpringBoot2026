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