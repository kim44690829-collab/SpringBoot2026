let write = document.getElementById("writeBtn");

write.addEventListener("click", function(){
	const isLogin = this.dataset.login;
	
	if(isLogin === "true"){
		location.href="/board/write";	
	}else{
		alert("로그인 후 이용 가능합니다.");
		location.href="/member/login";
	}
})