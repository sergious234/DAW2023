/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

const especial_simbols = "!\"·$%#&/()=?¿^*¨Ç:;,.-´ç+`¡'º[]{}@|";
const numbers = "1234567890";


let pass = document.getElementById("pass");
let pass_span = document.getElementById("spanClave");
pass.onchange = function () {
    const password = pass.value;
    
    let esp_chars = Array.from(password).filter( e => especial_simbols.includes(e, 0));
    let numbers_chars = Array.from(password).filter( e => numbers.includes(e, 0));
    
    if (password.length < 10) {
        pass_span.innerText = "Al menos 10 caracteres";
    } else if (esp_chars.length < 1) {
        pass_span.innerText = "Al menos un caracter especial";
    } else if (numbers_chars.length < 1) {
        pass_span.innerText = "Al menos un numero";
    } else {
        pass_span.innerText = "";
    }
};
