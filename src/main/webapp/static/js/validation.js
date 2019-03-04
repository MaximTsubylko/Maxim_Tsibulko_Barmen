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

    if (validEmailByPattern(email) && validConfirmingPassword(password, confirm_password)) {
        return true;
    } else {
        return false;
    }

}

function validEmailByPattern(email) {
    var email_pattern = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (email_pattern.test(email) == false) {
        swal("Ooopss..", "Wrong email!", "error");
        return false;
    } else {
        return true;
    }
}

function validConfirmingPassword(password, confirm_password) {
    if (password == confirm_password) {
        return true;
    } else {
        swal("Ooopss..", "Passwords you entered do not match", "error");
        return false;
    }
}

function validLoginByPattern(login) {
    var login_pattern = /^[a-zA-Z1-9]+$/;
    if (login_pattern.test(login) === false) {
        swal("Ooopss..", "Login should consist only of latin letters and numbers!", "error");
        return false;
    } else {
        return true;
    }
}

function validLoginBylength(login) {

    if (login.length < 4) {
        swal("Ooopss..", "Login must be at least 4 characters long!", "error");
        return false;
    } else {
        return true;
    }

    if (login.length > 20) {
        swal("Ooopss..", "Login must be no longer than 20 characters!", "error");
        return false;
    } else {
        return true;
    }
}