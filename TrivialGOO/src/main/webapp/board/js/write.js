/**
 * 
 */
 
 function submitFnc(){
	const formId = document.getElementById("fomrId");
	const titleId = document.getElementById("titleId");
	const contentsId = document.getElementById("areaId");
	
	if(titleId.value == "" || titleId.value == null){
		alert("제목을 입력하세요.");
	}else if(contentsId.value == "" || contentsId.value == null){
		alert("내용을 입력하세요.");
	}else{
		formId.submit();
	}
	
}