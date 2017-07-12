<html>

<body>
	<form action="ImageProcesserServlet">
		<%
		    String[] names = { "/img1.jpg", "/img2.jpg", "/img3.jpg", "/img4.jpg", "/img5.jpg", "/img6.jpg", "/img7.jpg", "/img8.jpg", "/img9.jpg",
		            "/img10.jpg" };
		    for (int i = 0; i < names.length; i++) {
		        out.write("<img src='ImageLoaderServlet?imgName=" + names[i] + "'/>");
		        out.write("<input type='checkbox' value='" + names[i] + "' name='imgName'/>");
		    }
		%>
		<br/>
		<input type="submit" value="Process selected images"/>
	</form>
</body>

</html>