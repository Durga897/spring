function isNumber(event) {
    if (event.charCode >= 48 && event.charCode <= 57) {
        return true;
    } else {
        alert("please enter only numbers")
        return false;
    }
}

function testValidations() {
    let passwordExpression = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,30}/;
    let password = document.forms["loginForm"]["password"].value;
    let userId = document.forms["loginForm"]["id"].value;

    if (userId === '') {
        alert("userId is required")
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
        document.forms["loginForm"].submit();
    }
}