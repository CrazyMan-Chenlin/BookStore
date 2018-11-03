<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
  	<link rel="stylesheet" type="text/css" href="<c:url value='/css/Pub.css'/>" />
  	<script type="text/javascript" src="<c:url value='/js/login.js'/>"></script>
  	<script type="text/javascript">
  		var path = "<c:url value='/'/>";//项目的根/book
  	</script>
  	<style type="text/css">
  		table{
  			font-size:10pt;
  			width:330px;
  		}
  		.txt{
  			border:0px;
  			border-bottom:1px solid blue;
  			width:200px;
  		}
  		td{
  			padding:5px;
  		}
  	</style>
  </head>
  <body style="text-align:center;">
  		<br/><br/>
  		<form name="f1" method="post" action="${pageContext.request.contextPath}/Login?action=login" onsubmit="return validate()" target="_parent">
  		<table>
			  <tr style="background:#7D7DFF;">
			  	<td colspan="2" align="center">
			  		用户登录
			  	</td>
			  </tr>
			  <tr>
			  	<td>
			  		姓名<font color="red">*</font>：
			  	</td>
			  	<td>
			  		<input class="txt" type="text" name="username"/><span style="color: red">${NameError}</span>
			  	</td>
			  </tr>	
			   <tr>
			  	<td>
			  		密码<font color="red">*</font>：
			  	</td>
			  	<td>
			  		<input class="txt" type="password" name="password"/><span style="color: red">${PasswordError}</span>
			  	</td>
			  </tr>	
			  <tr>
			  	<td>
			  		验证码<font color="red">*</font>：
			  	</td>
			  	<td>
			  		<input class="txt" type="text" style="width:100px;" name="code" />
			  		<img id="img" src="${pageContext.request.contextPath }/ValidateCodeImg" title="看不清?点击再换一张" onclick="changeImg()"/><br>
                    <span style="color: red">${CodeError}</span>
			  	</td>
			  </tr>
			  <tr>
			  	<td id="msg" colspan="2" style="color:red;" align="center">
			  	</td>
			  </tr>
			  <tr>
			  	<td colspan="2" align="center">
			  		<input type="submit" class="OperBtn" value="登录" style="margin:5px;"/>
			  		<button class="OperBtn" style="margin:5px;">取消</button>
			  		<a href="${pageContext.request.contextPath}/pages/front/login/reg.jsp">现在去注册一个新用户</a>
			  	</td>
			  </tr>	
  		</table>
  		</form>
        <script type="text/javascript">
            function changeImg(){
                var img = document.getElementById("img");
                img.src="${pageContext.request.contextPath}/ValidateCodeImg?"+new Date().getTime();
            }
        </script>
  </body>

</html>
  		
