$(document).ready(function () {
    $("#registerForm").submit(function (event) {
        event.preventDefault();

        $.ajax({
            url: "/messenger/user",
            type: "POST",
            data: $(this).serialize(),
            success: function (data, textStatus, jqXHR) {
                alert("You were successfully registered!");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("User with username you provided already exists! Error: " + jqXHR.status);
            }
        });
    });

    $("#messageForm").submit(function (event) {
        event.preventDefault();
        $.post("/MessageServlet", $(this).serialize(), function (data) {
            alert(data);
        });
    });

    $("#username").change(function () {
        $.get("/MessageServlet", $(this).serialize(), function (data) {
            $("#messagesList").html("");
            data.split("\n").forEach(function (message) {
                if (message.trim() !== "") {
                    $("#messagesList").append("<li>" + message + "</li>");
                }
            });
        });
    });
});