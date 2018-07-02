/**
 * doc.js
 * file
 */

$('input[id=update_file]').change(function() {
	$('#update_path').val($(this).val().substring(12));
});

$('input[id=upload_file]').change(function() {
	$('#upload_path').val($(this).val().substring(12));
});


/**
 * return previous page
 */
var page = '${sessionScope.page}';
console.log(page);
$(document).ready(function() {
    if (location.hash) {
        $('a[href=' + location.hash + ']').tab('show');
    }
    if (page) {
    	$('a[href=#' + page + ']').tab('show');
    }
    $(document.body).on("click", "a[data-toggle]", function(event) {
        location.hash = this.getAttribute("href");
    });
});

$(window).on('popstate', function() {
    var anchor = location.hash || $("a[data-toggle=tab]").first().attr("href");
    $('a[href=' + anchor + ']').tab('show');
    console.log(anchor);
});