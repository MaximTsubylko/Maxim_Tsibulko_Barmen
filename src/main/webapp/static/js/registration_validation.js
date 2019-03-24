function validLogin() {

    if (validLoginByPattern() && validLoginBylength()) {
        return Boolean(1);
    } else {
        return Boolean(0);
    }

}


function validRegistration() {

    if (validEmailByPattern() && validConfirmingPassword() && validPasswordByLength() && validLogin()) {
        return Boolean(1);
    } else {
        return Boolean(0);
    }

}

function validPassword() {
    if (validPasswordByLength() && validConfirmingPassword()) {
        return Boolean(1);
    } else {
        return Boolean(0);
    }
}

function validEmailByPattern() {
    var email_pattern = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var email = document.getElementById("email").value;
    if (!email_pattern.test(email)) {
        var title = document.getElementById("e_title").textContent;
        var message = document.getElementById("e12_message").textContent;
        swal(title, message, "error",);
        return Boolean(0);
    } else {
        return Boolean(1);
    }
}

function validConfirmingPassword() {
    if (!document.getElementById("password").value.localeCompare(document.getElementById("confirm_password").value)) {
        return Boolean(1);
    } else {
        var title = document.getElementById("e_title").textContent;
        var message = document.getElementById("e13_message").textContent;
        swal(title, message, "error");
        return Boolean(0);
    }
}

function validLoginByPattern() {
    var login_pattern = /^[a-zA-Z1-9]+$/;
    if (!login_pattern.test(document.getElementById("login").value)) {
        var title = document.getElementById("e_title").textContent;
        var message = document.getElementById("e16_message").textContent;
        swal(title, message, "error");
        return Boolean(0);
    } else {
        return Boolean(1);
    }
}

function validLoginBylength() {

    if (document.getElementById("login").value.length < 4) {
        var title = document.getElementById("e_title").textContent;
        var message = document.getElementById("e15_message").textContent;
        swal(title, message, "error");
        return Boolean(0);
    } else {
        return Boolean(1);
    }

    if (document.getElementById("login").value.length > 20) {
        var title = document.getElementById("e_title").textContent;
        var message = document.getElementById("e14_message").textContent;
        swal(title, message, "error");
        return Boolean(0);
    } else {
        return Boolean(1);
    }
}

function validPasswordByLength() {
    var password = document.getElementById("password").value;
    if (password.length < 6) {
        var title = document.getElementById("e_title").textContent;
        var message = document.getElementById("e17_message").textContent;
        swal(title, message, "error");
        return Boolean(0);
    } else {
        return Boolean(1);
    }

    if (password.length > 20) {
        var title = document.getElementById("e_title").textContent;
        var message = document.getElementById("e18_message").textContent;
        swal(title, message, "error");
        return Boolean(0);
    } else {
        return Boolean(1);
    }

}
