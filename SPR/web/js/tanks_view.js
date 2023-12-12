var previous_button = document.getElementById("previous_page");
var next_button = document.getElementById("next_page");
var page = 0;

let reload_tanks_table_body = function () {
    let body = document.getElementById("table_div");

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "table", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            body.innerHTML = xhr.responseText;
            previous_button = document.getElementById("previous_page");
            next_button = document.getElementById("next_page");
        } else {
            // La solicitud falló
            alert("Hubo un fallo al recargar la tabla");
        }
    };

    let obj = JSON.stringify({"page": page});
    console.log(obj);
    xhr.send(obj);
};

let reload_prev_button = function () {
    previous_button.addEventListener("click", function () {
        page -= 1;
        if (page < 0) {
            page = 0;
        }
        document.getElementById("page_text").innerText = page;
        reload_tanks_table_body();
    });
};

let reload_next_button = function () {
    next_button.addEventListener("click", function () {
        page += 1;
        reload_tanks_table_body();
        document.getElementById("page_text").innerText = page;
    });
};

document.addEventListener("DOMContentLoaded", function () {
    reload_next_button();
    reload_prev_button();
    document.getElementById("page_text").innerText = page;
});