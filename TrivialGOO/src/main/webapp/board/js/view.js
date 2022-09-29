/**
 * 
 */
 
 function deleteFnc() {
	const formId = document.getElementById("fomrId");

	let result = confirm("삭제 하시겠습니까?");
	
	if(result){
		formId.submit();
	}
}