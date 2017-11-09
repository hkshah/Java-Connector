
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<body>
	<h2>Welcome to RapidValue Oracle Services</h2>
	
	<p>Click below button to redirect the result to new page</p>
<form:form method="GET" action="/sales/hello">
<table>
    <tr>
    <td>
    <input type="submit" value="Redirect Page"/>
    </td>
    </tr>
</table>  
</form:form>
</body>
</html>
