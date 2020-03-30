window.addEventListener("load", getStatus, false);
document.getElementById('button').addEventListener("click", setStatus, false);

var getSetStatus = new XMLHttpRequest();
var newStatusRequest = new XMLHttpRequest();

function setStatus() {
    var statusText = document.getElementById("statustext").value;
    // encodeURIComponent om UTF-8 te gebruiken en speciale karakters om te zetten naar code
    var information = "status=" + encodeURIComponent(statusText);
    newStatusRequest.open("POST", "Controller?action=Test", true);
    // belangrijk dat dit gezet wordt anders kan de servlet de informatie niet interpreteren!!!
    // protocol header information
    newStatusRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    newStatusRequest.send(information);

    getStatus(); // update status
}

// status json vragen aan controller
function getStatus() {
    getSetStatus.open("GET", "Controller?action=Test", true);
    getSetStatus.onreadystatechange = getData;
    getSetStatus.send(null);
}

function getData() {
    if (getSetStatus.readyState == 4) {
        if (getSetStatus.status == 200) {
            var serverResponse = JSON.parse(getSetStatus.responseText);
            alert("parse: " + serverResponse + "\n,response: " + getSetStatus.responseText + "\nQuotetext: " + serverResponse["quote"] + "\n, nog quote text: " + serverResponse.quote); //todo test

            document.getElementById("status").innerText = serverResponse["status"];
            document.getElementById("statustext").value = "";
        }
    }
}