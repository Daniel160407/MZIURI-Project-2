$(document).ready(function () {
    $("#registerForm").submit(function (event) {
        event.preventDefault();

        $.ajax({
            url: "/messenger/user",
            type: "POST",
            data: $(this).serialize(),
            success: function () {
                alert("You were successfully registered!");
            },
            error: function (jqXHR) {
                alert("User with username you provided already exists! Error: " + jqXHR.status);
            }
        });
    });

    $("#messageForm").submit(function (event) {
        event.preventDefault();
        $.ajax({
            url: "/messenger/message",
            type: "POST",
            data: $(this).serialize(),
            success: function () {
                alert("Your message successfully sent!");
            },
            error: function (jqXHR) {
                alert("Your message is not valid! Error: " + jqXHR.status);
            }
        })
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