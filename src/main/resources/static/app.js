const host = "localhost:8080";
var ws;
var connectedUsers = new Set();

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

function connect() {
    //connect to stomp where stomp endpoint is exposed
    var socket = new WebSocket("ws://" + host + "/messages");
    ws = Stomp.over(socket);

    ws.connect({}, function (frame) {
        ws.subscribe("/user/queue/errors", function (message) {
            alert("Error " + message.body);
        });

        ws.subscribe("/user/queue/reply", function (message) {
            showMessage(message.body);
        });

        ws.subscribe("/topic/public", function (message) {
            showActivity(message.body);
        });
    }, function (error) {
        alert("STOMP error " + error);
    });
    setConnected(true);

    getConnectedUsers();
}

function getConnectedUsers() {
    $.get("http://" + host + "/api/chat/connectedUsers", function (data) {
        console.log(data);
        connectedUsers = new Set(data);
        reloadConnectedUsersList();
    });
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    const data = JSON.stringify({
        'message': $("#message").val(),
        'destination': $("#destination").val()
    });
    ws.send("/app/message", {}, data);
}

function showMessage(message) {
    const parsedMsg = JSON.parse(message);
    $("#messages").append("<tr><td> " + parsedMsg.from + ": " + parsedMsg.content + "</td></tr>");
}

function showActivity(message) {
    const parsedMsg = JSON.parse(message);

    if (parsedMsg.type === "CONNECT") {
        connectedUsers.add(parsedMsg.username);
    } else if (parsedMsg.type === "DISCONNECT") {
        connectedUsers.delete(parsedMsg.username);
    }
    reloadConnectedUsersList();
}

function reloadConnectedUsersList() {
    $("#connectedUsers").empty();
    connectedUsers.forEach(username =>
        $("#connectedUsers").append("<p>" + username + "</p>")
    )
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
});
