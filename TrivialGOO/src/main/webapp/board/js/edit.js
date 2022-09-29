/**
 * 
 */
 
function editFnc(){
	const formId = document.getElementById("fomrId");
	
	let result = confirm("수정 하시겠습니까?");
	
	if(result){
		formId.submit();
	}
}