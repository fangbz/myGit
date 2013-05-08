<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<html>
<head>
<title>Insert title here</title>
</head>
<body>

     
     
     <form action="updatePerson.action">
         username: <s:textfield name="username" value="%{#request.person.username}" readonly="true"></s:textfield>
         password: <s:textfield name="password" value="%{#request.person.password}"></s:textfield>
         age: <s:textfield name="age" value="%{#request.person.age}" ></s:textfield>
         registerdate: <s:textfield name="registerdate" value="%{#request.person.registerdate}"  readonly="true"></s:textfield>
         <s:hidden name="id" value="%{#request.person.id}" ></s:hidden>
         <s:submit></s:submit>
     </form>

</body>
</html>