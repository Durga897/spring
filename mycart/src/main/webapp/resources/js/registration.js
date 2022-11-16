function isNumber(event) {
    if (event.charCode >= 48 && event.charCode <= 57) {
        return true;
    } else {
        alert("please enter only numbers")
        return false;
    }
}

function testValidations() {
    let userNameExpression = /^[A-Za-z]+$/;
    let passwordExpression = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,30}/;
    let emailAddressExpression = /^[A-Za-z0-9_]{3,}@[A-Za-z]{3,}[.]{1}[A-Za-z]{2,6}$/;
    let userName = document.forms["registrationForm"]["userName"].value;
    let password = document.forms["registrationForm"]["password"].value;
    let email = document.forms["registrationForm"]["emailAddress"].value;
    let mobileNumber = document.forms["registrationForm"]["mobileNumber"].value;
    let userId = document.forms["registrationForm"]["id"].value;

    if (userId === '') {
        alert("userId is required")
        return false;
    }
    if (userName === '') {
        alert('username is required');
        return false;
    }
    if (!userName.match(userNameExpression)) {
        alert('Invalid username');
        return false;
    }
    if (email === '') {
        alert("email address is required")
        return false;
    }
    if (!email.match(emailAddressExpression)) {
        alert("Invalid email address")
        return false;
    }
    if (mobileNumber === '') {
        alert("phone number is required");
        return false;
    }
    if (isNaN(mobileNumber)) {
        alert("mobile number must contain numbers only");
        return false;
    }
    if ((mobileNumber.indexOf(9) !== 0) && (mobileNumber.indexOf(8) !== 0) && (mobileNumber.indexOf(7) !== 0)) {
        alert('Invalid Mobile Number')
        return false;
    }
    if (mobileNumber.length > 10 || mobileNumber.length < 10) {
        alert("phone number must contain 10 digits ");
        return false;
    }
    if (password === '') {
        alert('Password is required');
        return false;
    }
    if (!password.match(passwordExpression)) {
        alert('Password must contain at least one uppercase,lowercase,number and special character');
        return false;
    } else {
        document.forms["registrationForm"].submit();
    }
}