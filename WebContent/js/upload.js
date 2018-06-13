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
var docid = getQueryString("docid");
var url = "UploadHandleServlet?function=" + func +"&docid=" + docid;
document.getElementById("form").action = url;

$('input[id=file]').change(function() {
	$('#path').val($(this).val().substring(12));
});

function create() {
	var url = "UploadHandleServlet?function=create";
	document.getElementById("create_form").action = url;
	document.getElementById("create_form").submit(); 
}
