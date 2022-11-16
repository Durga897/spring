function isNumber(event) {
    if (event.charCode >= 48 && event.charCode <= 57) {
        return true;
    } else {
        return false;
    }
}

function testValidations() {
    let price = document.forms["registrationForm"]["price"].value;

    if (price === '') {
        alert("price is required");
        return false;
    }
    if (isNaN(price)) {
        alert("");
        return false;
    } else {
        document.forms["registrationForm"].submit();
    }
}