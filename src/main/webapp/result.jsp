<%@page import="essencialjmt.ImageData"%>
<%@page import="essencialjmt.cap4.AsyncIterable"%>
<html>

<body>
	<%
	    AsyncIterable iter = (AsyncIterable) request.getAttribute("iterable");
	    while (iter.hasNext()) {
	        ImageData data = iter.next();
	        out.write("<img src='ImageLoaderServlet?imgName=" + data.getName() + "'/> ");
	        out.write(data.getResolution());	        
	    }
	%>
	<br/>
	<a href='/index.jsp'>Back</a>
</body>

</html>