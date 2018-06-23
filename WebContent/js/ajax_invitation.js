/**
 * ajax_invitation.js
 * load invitation
 */

$(function(){  
    $.ajax({
        type:"GET",
        url:"./InvitationServlet",
        data:{
        	func:"getInvitationofReceiver",
        },
        dataType:"json",
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data, textStatus) {
        	console.log(data);
        	if (jQuery.isEmptyObject(data)) {
        		$("#receiver").hide();
        	} else {
        		var tr = $("#receiverTr");
        		$.each(data, function(index, element) {
        			//$.each遍历json对象data
        			var clonedTr = tr.clone();
        			var _index = index;
        			clonedTr.children("td").each(function(inner_index) {
        				switch(inner_index) {
        				case(0):
        					$(this).html(element.senderID).css({"padding-top": "2%"});
        				break;
        				case(1):
        					$(this).html(element.receiverID).css({"padding-top": "2%"});
        				break;
        				case(2):
        					$(this).html(element.documentID).css({"padding-top": "2%"});
        				break;
        				case(3):
        					console.log($(this));
        					addAcceptandIgnoreButton($(this), element.senderID, element.receiverID, element.documentID);
        				break;
        				}
        			});
        			clonedTr.insertBefore(tr);
        		});
        		//$("#cloneTr").hide();
        		//$("#receiver").show();
        	}
        	
        },
    });
}); 


$(function(){  
    $.ajax({
        type:"GET",
        url:"./InvitationServlet",
        data:{
        	func:"getInvitationofSender",
        },
        dataType:"json",
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data, textStatus) {
    		console.log(data);
        	if (jQuery.isEmptyObject(data)) {
        		$("#sneder").hide();
        	} else {
        		var tr = $("#senderTr");
        		$.each(data, function(index, element) {
        			//$.each遍历json对象data
        			var clonedTr = tr.clone();
        			var _index = index;
        			clonedTr.children("td").each(function(inner_index) {
        				switch(inner_index) {
        				case(0):
        					$(this).html(element.senderID).css({"padding-top": "2%"});
        				break;
        				case(1):
        					$(this).html(element.receiverID).css({"padding-top": "2%"});
        				break;
        				case(2):
        					$(this).html(element.documentID).css({"padding-top": "2%"});
        				break;
        				case(3):
        					addCancleButton($(this), element.senderID, element.receiverID, element.documentID);
        				break;
        				}
        			});
        			clonedTr.insertBefore(tr);
        		});
        	}
        	
        },
    });
}); 

function addAcceptandIgnoreButton(td, senderID, receiverID, documentID) {
    var acceptBtn = document.createElement("button");
    var ignoreBtn = document.createElement("button");
    
    acceptBtn.innerHTML = '接受';
    ignoreBtn.innerHTML = '忽略';
    
    acceptBtn.setAttribute("class", "btn btn-success");
    ignoreBtn.setAttribute("class", "btn btn-danger");
    
    acceptBtn.onclick = function () {
    	window.location.href='InvitationServlet?function=accept&sender_id=' + senderID 
    		+ '&receiver_id=' + receiverID + '&document_id=' + documentID;
    };
    ignoreBtn.onclick = function () {
    	window.location.href='InvitationServlet?function=ignore&sender_id=' + senderID 
    		+ '&receiver_id=' + receiverID + '&document_id=' + documentID;
    };
    
    td.append(acceptBtn).append(ignoreBtn);
}

function addCancleButton(td, senderID, receiverID, documentID) {
    var btn = document.createElement("button");
    btn.innerHTML = '撤销'; 
    btn.setAttribute("class", "btn btn-danger");
    btn.onclick = function () {
    	window.location.href='InvitationServlet?function=cancel&sender_id=' + senderID 
    		+ '&receiver_id=' + receiverID + '&document_id=' + documentID;
    };
    td.append(btn);
}
