function validate() {
    var pass = form.password.value;
    var confirmpass = form.confirm_password.value;

    if (pass == "") {
        swal("Ooopss..", "Passwords you entered do not match", "error");
        return false;
    }


    if (pass == confirmpass) {
        return true;
    } else {
        swal("Ooopss..", "Passwords you entered do not match", "error");
        return false;
    }


}