<%@ page language="java" import="java.util.*,com.opensymphony.xwork2.*,com.opensymphony.xwork.util.*,bz.action.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags" %>
<html>
  <head>
    <title>My JSP 'ognl.jsp' starting page</title>
  </head>
  <body>
       
       
         username <s:property value="username"/>  <br>
         password <s:property value="password"/>  <br>
        <hr>
        parameters username <s:property value="#parameters.username"/>  <br>
         password <s:property value="#parameters.password"/>  <br>
          <hr>
        request  username <s:property value="#request.username"/>  <br>
         password <s:property value="#request.password"/>  <br>
          <hr>
       session  username <s:property value="#session.username"/>  <br>
         password <s:property value="#session.password"/>  <br>
          <hr>
          appliaction username <s:property value="#appliaction.username"/>  <br>
           password <s:property value="#appliaction.password"/>  <br>
          <hr>
          ActionContext.request: <%=((Map)ActionContext.getContext().get("request")).get("username")%><br>
          ActionContext.session: <%=((Map)ActionContext.getContext().get("session")).get("username")%><br>
          ActionContext.session: <%=ActionContext.getContext().getSession().get("username")%><br>
          ActionContext.session: <%=ActionContext.getContext().getSession().get("username")%><br>
          ActionContext.appliaction: <%=ActionContext.getContext().getApplication().get("username")%><br>
          ActionContext.appliaction: <%=((Map)ActionContext.getContext().get("application")).get("username")%><br>
         
           ActionContext.attr: <%=((Map)ActionContext.getContext().get("attr")).get("username")%><br>
           <hr><hr>
           person1:address <s:property value="list[0].address"/>  <br>
           person1 : cat1.name <s:property value="list[0].cat.name"/>  <br>
           personList.size <s:property value="list.size"/>  <br>
           personList.isEmpty <s:property value="list.isEmpty"/>  <br>
           person2:age <s:property value="list[1].age"/>  <br>
           <hr>
              person1:address <%=((OgnlAction)ActionContext.getContext().getValueStack().peek()).getList().get(0).getName()%> <br>
              person1 :cat1.name <%=((OgnlAction)ActionContext.getContext().getValueStack().peek()).getList().get(0).getCat().getName()%> <br>
              person2:age <%=((OgnlAction)ActionContext.getContext().getValueStack().peek()).getList().get(1).getAge()%> <br>
            <hr>
              person2 third friends(第二个人的第三个朋友)  <s:property value="list[1].friends[2]"/>  <br>
              person2 third friends(第二个人的第三个朋友) <%=((OgnlAction)ActionContext.getContext().getValueStack().peek()).getList().get(1).getFriends()[2]%> <br>
              <hr>
               获取第二个人名字里面叫 hello2 的值 <s:property value="list[1].map['hello2']"/>  <br>
               <hr>
                过滤   获取list里面名字长度大于5的对象   <s:property value="list.{? #this.name.length()>5}"/>  注意这里返回来的是一个集合 <br>
                过滤   获取list里面名字长度大于5的对象   <s:property value="list.{? #this.name.length()>5}[0].name"/>   <br>
             
                使用循环遍历集合 <br>
                <s:iterator value="list.{? #this.name.length()>2}" var="person">
<%--                   <s:property value="#person.name"/>--%>
                   <s:property value="name"/>   
                   <s:property value="cat.color"/>
                   <s:property value="friends[0]"/>
                   <br>
                </s:iterator>
               <hr>
                投影(返回列的子集) 返回所有的person的年龄 <br>
                <s:iterator value="list.{age}">
                   <s:property/>
                   <br>
                </s:iterator>
         <s:debug></s:debug>
  </body>
</html>
