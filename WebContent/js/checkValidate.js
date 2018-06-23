/**
 * checkValidate.js
 * register.jsp
 * check this form's validity
 */

function isRightID(str) {
	var reID = /^[[a-zA-Z0-9]{5,20}$/;
	return(reID.test(str));
}

function isRightEmail(str) {
	var reEmail = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
	return(reEmail.test(str));
}

function isRightPassword(str) {
	var rePassword = /^[[a-zA-Z0-9]{5,20}$/;
	return(rePassword.test(str));
}

function isPasswordConsistent(str0, str1) {
	return (str0 == str1);
}

function isValidPassword() {
	
	var p1 = document.getElementById("password1").value;
	var p2 = document.getElementById("password2").value;
	if (!isRightPassword(p1)) {
		alert("密码格式错误");
		return false;
	}
	if (!isPasswordConsistent(p1, p2)) {
		alert("两次密码不一致");
		return false;
	}
	return true;
}
//Test
/*
	var id = "as123456789";
	var email = "1abc@163.com";
	var str0 = "Abcd";
	var str1 = "abcd";
	if (isPasswordConsistent(str0, str1)) {
		alert("Consistent");
	} else {
		alert("Inconsistent");
	}
	if (isRightEmail(email)) {
		alert("right Email");
	}
	if (isRightID(id)) {
		alert("right ID");
	} else {
		alert("wrong ID");
	}
*/
function isValid() {
	var id = document.getElementById("accountid").value;
	var email = document.getElementById("email").value;
	var name = document.getElementById("name").value;
	var p0 = document.getElementById("password0").value;
	var p1 = document.getElementById("password1").value;
	if (!isRightID(id)) {
		alert("账号ID应为5-20位的字母或数字");
		return false;
	} 
	if (!isRightEmail(email)) {
		alert("邮箱格式错误");
		return false;
	}
	if (!isRightID(name)) {
		alert("用户名应为5-20位的字母或数字");
		return false;
	}
	if (!isRightPassword(p0)) {
		alert("密码格式错误");
		return false;
	}
	if (!isPasswordConsistent(p0, p1)) {
		alert("两次密码不一致");
		return false;
	}
	return true;
}