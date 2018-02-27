window.onload = init;
function init() {
	   // Bind "onsubmit" event handler to the "submit" button
	   document.getElementById("formTest").onsubmit = validateForm;
	   // Bind "onclick" event handler to "reset" button
	   document.getElementById("btnReset").onclick = clearForm;
	   // Set initial focus
	   document.getElementById("txtName").focus();
	}
function validateForm(theForm) {
	   with(theForm) {
	      // return false would prevent default submission
	      return (isNotEmpty(txtName, "Please enter your name!", elmNameError)
	           && isNumeric(txtZipcode, "Please enter a 5-digit zip code!", elmZipcodeError)
	           && isLengthMinMax(txtZipcode, 5, 5, "Please enter a 5-digit zip code!", elmZipcodeError)
	           && isSelected(selCountry, "Please make a selection!", elmCountryError)
	           && isChecked("gender", "Please check a gender!", elmGenderError)
	           && isChecked("color", "Please check a color!", elmColorError)
	           && isNumeric(txtPhone, "Please enter a valid phone number!", elmPhoneError)
	           && isValidEmail(txtEmail, "Enter a valid email!", elmEmailError)
	           && isValidPassword(txtPassword, "Password shall be 6-8 characters!", elmPasswordError)
	           && verifyPassword(txtPassword, txtPWVerified, "Different from new password!",
	                 elmPWVerifiedError)
	      );
	   }
	}
function postValidate(isValid, errMsg, errElm, inputElm) {
	   if (!isValid) {
	      // Show errMsg on errElm, if provided.
	      if (errElm !== undefined && errElm !== null
	            && errMsg !== undefined && errMsg !== null) {
	         errElm.innerHTML = errMsg;
	      }
	      // Set focus on Input Element for correcting error, if provided.
	      if (inputElm !== undefined && inputElm !== null) {
	         inputElm.classList.add("errorBox");  // Add class for styling
	         inputElm.focus();
	      }
	   } else {
	      // Clear previous error message on errElm, if provided.
	      if (errElm !== undefined && errElm !== null) {
	         errElm.innerHTML = "";
	      }
	      if (inputElm !== undefined && inputElm !== null) {
	         inputElm.classList.remove("errorBox");
	      }
	   }
	}
function isNotEmpty(inputElm, errMsg, errElm) {
	   var isValid = (inputElm.value.trim() !== "");
	   postValidate(isValid, errMsg, errElm, inputElm);
	   return isValid;
	}
function isNumeric(inputElm, errMsg, errElm) {
	   var isValid = (inputElm.value.trim().match(/^\d+$/) !== null);
	   postValidate(isValid, errMsg, errElm, inputElm);
	   return isValid;
	}
function isAlphabetic(inputElm, errMsg, errElm) {
	   var isValid = (inputElm.value.trim().match(/^[a-zA-Z]+$/) !== null) ;
	   postValidate(isValid, errMsg, errElm, inputElm);
	   return isValid;
	}
function isAlphanumeric(inputElm, errMsg, errElm) {
	   var isValid = (inputElm.value.trim().match(/^[0-9a-zA-Z]+$/) !== null);
	   postValidate(isValid, errMsg, errElm, inputElm);
	   return isValid;
	}
//Validate that input value is a valid email address
function isValidEmail(inputElm, errMsg, errElm) {
   var isValid = (inputElm.value.trim().match(
         /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/) !== null);
   postValidate(isValid, errMsg, errElm, inputElm);
   return isValid;
}
function isSelected(selectElm, errMsg, errElm) {
	   // You need to set the default value of <select>'s <option> to "".
	   var isValid = (selectElm.value !== "");   // value in selected <option>
	   postValidate(isValid, errMsg, errElm, selectElm);
	   return isValid;
	}
function clearForm() {
	   // Remove class "errorBox" from input elements
	   var elms = document.querySelectorAll('.errorBox');  // class
	   for (var i = 0; i < elms.length; i++) {
	      elms[i].classList.remove("errorBox");
	   }
	   // Remove previous error messages
	   elms = document.querySelectorAll('[id$="Error"]');  // id ends with Error
	   for (var i = 0; i < elms.length; i++) {
	      elms[i].innerHTML = "";
	   }
	 
	   // Set initial focus
	   document.getElementById("txtName").focus();
}