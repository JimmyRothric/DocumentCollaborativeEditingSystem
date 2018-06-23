/**
 * ajax_notification
 * count notification
 */
$(function(){  
    $.ajax({
        type:"POST",
        url:"./InvitationServlet",
        data:{
        	func:"countNotification",
        },
        dataType:"text",
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data, textStatus) {
        	if (data == "0") {
        		$("#notification_icon").css({"color": "#aaaaaa"});
        		$("#notification_cnt").hide();
        	} else {
        		$("#notification_icon").css({"color": "#FF6347"});
        		$("#notification_cnt").css({"background-color": "#FF6347"});
        		$("#notification_cnt").html(data);
        	}
        },
    });
}); 
