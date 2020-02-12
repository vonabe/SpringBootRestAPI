var socket = new WebSocket("ws://127.0.0.1:8000");
socket.onopen = function (e) {
    alert("[open] Соединение установлено");
    alert("Отправляем данные на сервер");
    socket.send("Hello OnOpen");
};
socket.onmessage = function (message) {
    alert('[message] Данные получены с сервера: '+message.data)
};
socket.onclose = function (close) {
    if (close.wasClean) {
        alert('[close] Соединение закрыто чисто, код='+close.code+' причина='+close.reason);
    } else {
        // например, сервер убил процесс или сеть недоступна
        // обычно в этом случае event.code 1006
        var message = "[[${name}]]";
        alert('[close] Соединение прервано '+message);
    }
};
socket.onerror = function(error) {
    alert('[error] '+error.message);
};