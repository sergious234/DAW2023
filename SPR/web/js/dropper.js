// Code from:
//    https://developer.mozilla.org/en-US/docs/Web/API/HTML_Drag_and_Drop_API/File_drag_and_drop 
function dropHandler(ev) {
    console.log("File(s) dropped");
    console.log("Subiendo ficheros");

    // Prevent default behavior (Prevent file from being opened)
    ev.preventDefault();

    let item = ev.dataTransfer.items[0]

    if (item == null) return;
    if (item.kind != "file") return ;

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/SPR/replays/add", true);
    let formData = new FormData();
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            alert("Archivo subido con exito !")
        } else {
            // La solicitud falló
            alert("Se produjo un fallo al subir la replay!");
        }
    };

    let file = item.getAsFile()

    if (file) {
        formData.append('fileData', file);
        /*
        var reader = new FileReader();
        reader.onload = function (e) {
            formData.append(e.target.result)
        }

        reader.readAsArrayBuffer(file);
        */
    }
    xhr.send(formData);
}

function arrayBufferToBase64(buffer) {
    var binary = '';
    var bytes = new Uint8Array(buffer);
    var len = bytes.byteLength;
    for (var i = 0; i < len; i++) {
        binary += String.fromCharCode(bytes[i]);
    }
    return btoa(binary);
}



function dragOverHandler(ev) {
    console.log("File(s) in drop zone");

    // Prevent default behavior (Prevent file from being opened)
    ev.preventDefault();
}
