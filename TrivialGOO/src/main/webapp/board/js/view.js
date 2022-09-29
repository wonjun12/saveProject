/**
 * 
 */

function deleteFnc() {
	const formId = document.getElementById("fomrId");

	let result = confirm("삭제 하시겠습니까?");

	if (result) {
		formId.submit();
	}
}
function vwFnc() {
	const formId2 = document.getElementById("formId2");
		const inputId = document.getElementById("commentWId");

		if (inputId.value == "" || inputId.value == null) {
			alert("댓글을 작성해 주세요.");
		} else {
			formId2.submit();
		}
	
}