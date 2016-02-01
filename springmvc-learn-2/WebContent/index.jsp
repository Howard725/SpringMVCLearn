<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#testJson").click(function(){
			var url = this.href;
			var args = {};
			$.post(url, params, function(data){
				for(var i = 0; i < data.length; i++ ){
					var id = data[i].id;
					var lastName = data[i].lastName;
					
					alert(id + ":" + lastName);
				}
			});
			return false;
		})
	})
</script>
</head>
<body>

	<form action="testFileUpload" method="POST" enctype="multipart/form-data">
		File: <input type="file" name="file"/>
		Desc: <input type="text" name="desc"/>
		<input type="submit" value="Submit"/>
	</form>
	<br><br>

	<a href="emps">List All Employees</a>
	<br><br>
	
	<a href="testJson" id="testJson">Test Json</a>
	<br><br>
	
	<form action="testHttpMessageConverter" method="POST" enctype="multipart/form-data">
		File: <input type="file" name="file"/>
		Desc: <input type="text" name="desc"/>
		<input type="submit" value="Submit"/>
	</form>
	<br><br>
	
	<a href="testResponseEntity">Test ResponseEntity</a>
	<br><br>

	<a href="i18n">I18N PAGE</a>
	<br><br>

</body>
</html>