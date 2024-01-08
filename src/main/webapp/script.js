// Request-ის გაგზავნა
async function register() {
    var url = webserverName + servletUrl + '?param1=param1Value' + '&param2=param2Value';
    var method = "POST"
    var response = await fetch(url, {method: "POST"});

    // Response body-ს მიღება
    var body = await response.text();

    // HTML ელემენტის დამატება/შეცვლა
    var div = document.getElementById("some-div-id");
    div.innerHTML = '<h1>Hello World</h1>';
}








