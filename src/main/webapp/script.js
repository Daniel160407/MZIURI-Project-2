$(document).ready(function () {
    $("#registerForm").submit(function (event) {
        event.preventDefault();
        $.ajax({
            url: "/messenger/user",
            type: "POST",
            data: $(this).serialize(),
            success: function () {
                alert("You were successfully registered!");
                this.reset();
            },
            error: function (jqXHR) {
                alert("User with username you provided already exists! Error: " + jqXHR.status);
                this.reset();
            }
        });

        $("#registerUsername").val("");
        $("#registerPassword").val("");
    });

    $("#messageForm").submit(function (event) {
        event.preventDefault();
        $.ajax({
            url: "/messenger/message",
            type: "POST",
            data: $(this).serialize(),
            success: function () {
                alert("Your message successfully sent!");
                this.reset();
            },
            error: function (jqXHR) {
                alert("User with username you provided, doesn`t exists or your message is not valid! Error: " + jqXHR.status);
                this.reset();
            }
        });

        $("#username").val("");
        $("#message").val("");
    });

    $("#inboxForm").submit(function (event) {
        event.preventDefault();
        $.ajax({
            url: "/messenger/message",
            type: "GET",
            data: $(this).serialize(),
            success: function (data) {
                if (data.length > 0) {
                    const messages = data.map(function (obj) {
                        return obj.message;
                    });

                    let formattedData = messages.join("<br/>");

                    document.getElementById("inbox").style.display = "block";
                    document.getElementById("messages").innerHTML = formattedData;

                    alert("Your messages were successfully loaded!");
                } else {
                    alert("You don`t have any messages received!")
                }
                this.reset();
            },
            error: function (jqXRH) {
                alert("Username or password you provided isn`t correct! Error: " + jqXRH.status);
                this.reset();
            }
        })

        $("#inboxUsername").val("");
        $("#inboxPassword").val("");
    });
});