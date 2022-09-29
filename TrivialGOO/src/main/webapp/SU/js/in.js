/**
 * 
 */
 function submitFnc(){
		const formId = document.getElementById("formId");
		
		formId.submit();
	}
	
	function nextFnc() {
		
		var idCk = false;
		var emailCk = false;
		var pwdCk = false;
		
		const id = document.getElementById("id");
		const id2 = document.getElementById("id2");
		const innerId = document.getElementById("searchId");
		
		if((id.value == "" || id.value == null) && (id2.value == "" || id2.value == null)){
			innerId.innerHTML = "성과 이름을 입력하세요.";
		}else if((id.value == "" || id.value == null)){
			innerId.innerHTML = "성을 입력하세요.";
		}else if((id2.value == "" || id2.value == null)){
			innerId.innerHTML = "이름을 입력하세요.";
		}else{
			innerId.innerHTML = "";
			idCk = true;
		}
		
		const emailC = document.getElementById("emailId");
		const innerEmailId = document.getElementById("searchEamilId");
		
		if(emailC.value == "" || emailC.value == null){
			innerEmailId.innerHTML = "이메일 주소를 입력해주세요.";
		}else if(emailC.value.search('@') == -1){
			innerEmailId.innerHTML = "정확한 Email구조로 입력해주세요.";
		} 
		else{
			innerEmailId.innerHTML = "";
			emailCk = true;
		}
		
		const pwd1 = document.getElementById("PwdId1");
		const pwd2 = document.getElementById("PwdId2");
		const innerPwdId = document.getElementById("searchPwdId");
		
		if(pwd1.value == "" || pwd1.value == null){
			innerPwdId.innerHTML = "비밀번호를 입력해주세요.";
		}else if(pwd1.value != pwd2.value){
			innerPwdId.innerHTML = "비밀번호가 일치하지 않습니다.";
		}else{
			innerPwdId.innerHTML = "";
			pwdCk = true;
		}
		
		if(idCk == true && pwdCk == true && emailCk == true){
			submitFnc();
		}
		
	}