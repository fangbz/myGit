<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
     username:<s:property value="#request.person.username"/>
     password:<s:property value="#request.person.password"/>
     age:<s:property value="#request.person.age"/>
     registerdate:<s:property value="#request.person.registerdate"/>
</body>
</html>