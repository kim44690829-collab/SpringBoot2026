function boardForm(){
	/* DOM 으로 form을 연결 */
	let form = document.board_form;
	if(form.title.value === ""){
		alert("제목을 입력하세요");
		form.title.focus();
	}else if(form.writer.value === ""){
		alert("작성자를 입력하세요");
		form.writer.focus();
	}else if(form.content.value === ""){
		alert("글을 입력하세요");
		form.content.focus();
	}else{
		/* 데이터 전송 */
		form.submit();
	}
}