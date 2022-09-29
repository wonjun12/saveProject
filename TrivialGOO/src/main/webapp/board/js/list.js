/**
 * 
 */
 
 function searchFnc() {
		const formId = document.getElementById("formId");
		const inputId = document.getElementById("scId");

		if (inputId.value == "" || inputId.value == null) {
			alert("찾으실 내용을 입력해 주세요.");
		} else {
			formId.submit();
		}

	}