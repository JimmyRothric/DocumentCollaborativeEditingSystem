/**
 * 
 */
//get value from url by parameter name
function getQueryString(name) {
    var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
    if (result == null || result.length < 1) {
        return "";
    }
    return result[1];
}

var func = getQueryString("function");
var url = "UploadHandleServlet?function=" + func;
document.getElementById("form").action = url;