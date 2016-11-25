<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>WebSocketTestPage</title>
</head>
<body class="container">
	<div class="page-header" id="tou"></div>
	<hr>
	<input id="text" type="text"/>
	<button id="button">发送</button>
	<button onclick="sendAll()">SendAll</button>
	<div id="message"></div>
</body>

<script type="text/javascript">
	var websocket = null;
	var transports = [];
	
	var url = '${pageContext.request.contextPath }' + '/sockjs'; 
	websocket = new SockJS(url, undefined, {transports: transports});
	
	websocket.onopen = function() {
		console.log('open');
	};
	websocket.onmessage = function(e) {
		console.log('message', e.data);
	};
	websocket.onclose = function() {
		console.log('close');
	};
	websocket.onheartbeat = function() {
	    console.log('heartbeat');
	};

	$('#button').click(function() {
  		if (websocket != null) {
  			var message = $('#text').val();
  			websocket.send(message);
  			console.log("Have sent message:" + message); 
  		} else {
  			alert("未与服务器链接.");
  		};
  	});
	
	function sendAll() {
		if (websocket != null) {
			websocket.send("sendAll#MESSAGETOPUBLIC");
  		} else {
  			alert("未与服务器链接.");
  		};
	}
	
</script>

</html>
