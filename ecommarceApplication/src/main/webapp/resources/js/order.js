function showCashPayment() {
    document.getElementById('cardPayment').style.display = 'none';
    document.getElementById('cashPayment').style.display = 'block';
}

function showCardPayment() {
    document.getElementById('cardPayment').style.display = 'block';
    document.getElementById('cashPayment').style.display = 'none';
}

function generateCaptcha() {
    let alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
        'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
    for (let i = 0; i < 4; i++) {
        var firstAlpha = alphabet[Math.floor(Math.random() * alphabet.length)];
        var secondAlpha = alphabet[Math.floor(Math.random() * alphabet.length)];
        var thirdAlpha = alphabet[Math.floor(Math.random() * alphabet.length)];
        var fourthAlpha = alphabet[Math.floor(Math.random() * alphabet.length)];
    }
    document.getElementById("captcha").value = firstAlpha + '' + secondAlpha + '' + '' + thirdAlpha + '' + fourthAlpha
}

function checkValidCaptcha(orderProduct) {
    let captcha = removeSpaces(document.getElementById('captcha').value);
    let captchaValue = removeSpaces(document.getElementById('captchaCode').value);
    if (captcha === captchaValue) {
        document.getElementById("order").formAction = orderProduct;
        return true;
    } else {
        alert("Please enter a valid captcha.");
        document.getElementById("order").formAction = '/checkout';
        return false;
    }
}

function removeSpaces(string) {
    return string.split(' ').join('');
}