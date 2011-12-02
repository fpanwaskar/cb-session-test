<!DOCTYPE html>
<html>
<head>
	<title>Session</title>
	<meta name="layout" content="main" />
</head>
<body>

	<div id="pageBody">
		<p>Current session inactivity timeout is set to : ${session.getMaxInactiveInterval()} secs</p>
		<p>Session last accessed : <g:formatDate date="${new Date(session.getLastAccessedTime())}" type="datetime" style="MEDIUM"/></p>
	</div>
	</br>
	
	<p>Set a id and message to place on the session.</p>
	<g:form name="sessionParamsForm" controller="session" action="addToSession">
		<g:textField name="id" value="" /></br>
		<g:textArea name="message" value="" />
		<g:submitButton name="Submit" value="Submit" />
	</g:form>
	</br>
	
	<p>Current session holds messages: ${httpSessionStorage?.getAllMessages()}</p>
</body>
</html>
