function validLogin(form) {
    var login = form.loginField.value;

    if (validLoginByPattern(login) && validLoginBylength(login)) {
        return true;
    } else {
        return false;
    }

}


function validRegistration(form) {
    var email = form.email.value;
    var password = form.password.value;
    var confirm_password = form.confirm_password.value;

    if (validEmailByPattern(email) && validConfirmingPassword(password, confirm_password) && validLogin(form)) {
        return true;
    } else {
        return false;
    }

}

function validEmailByPattern(email) {
    var email_pattern = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (email_pattern.test(email) == false) {
        var title = document.getElementById("e_title").textContent;
        var message = document.getElementById("e12_message").textContent;
        swal(title, message, "error",);
        return false;
    } else {
        return true;
    }
}

function validConfirmingPassword(password, confirm_password) {
    if (password == confirm_password) {
        return true;
    } else {
        var title = document.getElementById("e_title").textContent;
        var message = document.getElementById("e13_title").textContent;
        swal(title, message, "error");
        return false;
    }
}

function validLoginByPattern(login) {
    var login_pattern = /^[a-zA-Z1-9]+$/;
    if (login_pattern.test(login) === false) {
        var title = document.getElementById("e_title").textContent;
        var message = document.getElementById("e16_title").textContent;
        swal(title, message, "error");
        return false;
    } else {
        return true;
    }
}

function validLoginBylength(login) {

    if (login.length < 4) {
        var title = document.getElementById("e_title").textContent;
        var message = document.getElementById("e15_title").textContent;
        swal(title, message, "error");
        return false;
    } else {
        return true;
    }

    if (login.length > 20) {
        var message = document.getElementById("e14_title").textContent;
        swal(title, message, "error");
        return false;
    } else {
        return true;
    }
}