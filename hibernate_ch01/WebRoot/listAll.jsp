<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<html>
<head>
<title>Insert title here</title>

<script type="text/javascript">
   function del(){
	   
	   if(confirm("确定删除吗?")){
		   return true;
	   }
	   
	   return false;
   }
</script>
</head>
<body>

<table border="1">
   <tr>
      <td>序号</td>
      <td>username</td>
      <td>password</td>
      <td>age</td>
      <td>reisterdate</td>
      <td>update</td>
      <td>delete</td>
   </tr>
   
   <s:iterator value="#request.list"  id="person" status="st">
   
      <tr>
          <td><s:property value="#st.index+1" /></td>
	      <td><s:a href="getPerson.action?id=%{#person.id}"><s:property value="username" /></s:a> </td>
	      <td><s:property value="password" /> </td>
	      <td><s:property value="age" /> </td>
	      <td><s:property value="registerdate" /> </td>
	      <td><s:a href="toUpdatePerson.action?id=%{#person.id}">更新</s:a></td>
	      <td><s:a href="deletePerson.action?id=%{#person.id}" onclick="return del();">删除</s:a></td>
    </tr>
   
   </s:iterator>
   
</table>
<s:debug></s:debug>
</body>
</html>