/**
 * 
 */
 function pwdCkFnc() {
		const checkId = document.getElementById("checkId");
		let pwdId = document.getElementById("pwdId");
		
		if(checkId.checked){
			pwdId.type = 'text';			
		}else{
			pwdId.type = 'password';
		}
	}
	
function submitFnc() {
	const formId = document.getElementById("formId");
	const pwdId = document.getElementById("pwdId").value;
	let searchPwdId = document.getElementById("searchPwdId");
	
	if(pwdId == "" || pwdId == null){
		searchPwdId.innerHTML = "비밀번호를 입력해주세요.";
	}else {
		formId.submit();
	}
	
	
}