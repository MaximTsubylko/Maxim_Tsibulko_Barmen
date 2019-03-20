function validateCocktailData() {
    if (validDescriptionByLength() && validPriceByLength() && validPriceBySign()){
        return Boolean(1);
    } else {
        return Boolean(0);
    }
}

function validDescriptionByLength() {
    var description = document.getElementById("description").value;

    if (description.length > 500){
        var title = document.getElementById("e_title").textContent;
        var message = document.getElementById("e19_message").textContent;
        swal(title, message, "error",);
       return Boolean(0);
    } else {
        return Boolean(1);
    }
}

function validPriceByLength() {
    var price = document.getElementById("price").value;

    if (price.length > 4){
        var title = document.getElementById("e_title").textContent;
        var message = document.getElementById("e20_message").textContent;
        swal(title, message, "error",);
        return Boolean(0);
    } else {
        return Boolean(1);
    }
}

function validPriceBySign() {
    var price = document.getElementById("price").value;
    if (price.length < 0){
        var title = document.getElementById("e_title").textContent;
        var message = document.getElementById("e21_message").textContent;
        swal(title, message, "error",);
        return Boolean(0);
    } else {
        return Boolean(1);
    }
}