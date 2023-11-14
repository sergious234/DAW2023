
let reload_tanks_table_body = function () {
    
    let body = document.getElementById("table_body");

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "table", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            console.log(xhr.responseText)
            body.innerHTML = xhr.responseText;
        } else {
            // La solicitud falló
            alert("Hubo un fallo al recargar la tabla");
        }
    }

    xhr.send(null);
    
}


document.getElementById("update_tank_id").addEventListener("change", function () {
    var id = document.getElementById("update_tank_id").value;


    var xhr = new XMLHttpRequest();
    var queryString = "tank_id=" + encodeURIComponent(id);
    xhr.open("POST", "get", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            var nombre_input = document.getElementById("new_tank_name");
            var hp_input = document.getElementById("new_tank_hp");
            var dmg_input = document.getElementById("new_tank_dmg");

            nombre_input.value = xhr.getResponseHeader("tank_name");
            hp_input.value = xhr.getResponseHeader("tank_hp");
            dmg_input.value = xhr.getResponseHeader("tank_dmg");
        } else {
            // La solicitud falló
            alert("Hubo un fallo al procesar la solicitud");
        }
    }

    xhr.send(queryString);
});

document.addEventListener("DOMContentLoaded", function () {
    let i = 0;
    let tankElement = document.getElementById(`tank_rmv_${i}`);

    while (tankElement) {
        let tankIdElement = document.getElementById(`tank_id_${i}`);
        tankElement.onclick = function () {
            let id = tankIdElement.textContent;
            let resultado = confirm(`Está seguro de que quiere eliminar el tanque ${id} de la base de datos ?`);

            if (resultado) {
                // Borrar tanque
                var xhr = new XMLHttpRequest();
                var queryString = 'tank_id=' + encodeURIComponent(id);
                xhr.open("POST", "delete", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onload = function () {
                    if (xhr.status >= 200 && xhr.status < 300) {

                        reload_tanks_table_body();
                    } else {
                        // La solicitud falló
                        alert("Hubo un fallo al procesar la solicitud");
                    }
                }

                xhr.send(queryString);
            } else {
                // No borrar tanque
            }
        }
        i++;
        tankElement = document.getElementById(`tank_rmv_${i}`);
    }
})

document.getElementById("add_form").addEventListener("submit", function () {
    event.preventDefault();
    var resultado = confirm("Seguro que quieres añadir el tanque?");

    console.log(resultado);
    if (resultado) {
        // Añadir tanque

        var tank_name = document.getElementById("tank_name").value;
        var tank_dmg = document.getElementById("tank_dmg").value;
        var tank_hp = document.getElementById("tank_hp").value;

        var xhr = new XMLHttpRequest();
        var queryString = 'tank_name=' + encodeURIComponent(tank_name)
                + '&tank_dmg=' + encodeURIComponent(tank_dmg)
                + '&tank_hp=' + encodeURIComponent(tank_hp);

        xhr.open("POST", "add", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onload = function () {
            console.log(xhr.status);
            if (xhr.status >= 200 && xhr.status < 300) {
                //window.location.href = "crud";
                reload_tanks_table_body();
            } else {
                // La solicitud falló
                alert("Hubo un fallo al procesar la solicitud");
            }
        };

        xhr.send(queryString);
    }
});

document.getElementById("update_form").addEventListener("submit", function () {
    event.preventDefault();
    var resultado = confirm("Seguro que quieres actualizar el tanque?");

    console.log(resultado);
    if (resultado) {
        // Añadir tanque

        var tank_name = document.getElementById("new_tank_name").value;
        var tank_dmg = document.getElementById("new_tank_dmg").value;
        var tank_hp = document.getElementById("new_tank_hp").value;

        var xhr = new XMLHttpRequest();
        var queryString = 'tank_name=' + encodeURIComponent(tank_name)
                + '&tank_dmg=' + encodeURIComponent(tank_dmg)
                + '&tank_hp=' + encodeURIComponent(tank_hp);

        xhr.open("POST", "update", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onload = function () {
            if (xhr.status >= 200 && xhr.status < 300) {
                reload_tanks_table_body();
                // window.location.href = "crud";

            } else {
                // La solicitud falló
                alert("Hubo un fallo al procesar la solicitud");
            }
        };

        xhr.send(queryString);
    }
})