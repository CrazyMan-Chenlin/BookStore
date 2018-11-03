<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
  	<link rel="stylesheet" type="text/css" href="<c:url value='/css/Pub.css'/>"/>
  	<script type="text/javascript" src="<c:url value='/js/user.js'/>"></script>
  	<script type="text/javascript">
  		var path = "<c:url value='/'/>";
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
  		<br/><br/><br/><br/>
  		<form name="f1" method="post"  action="${pageContext.request.contextPath}/Register?action=register" onsubmit="return valdate()">
  		<table>
			  <tr style="background:#7D7DFF;">
			  	<td colspan="2" align="center">
			  		用户注册
			  	</td>
			  </tr>
			  <tr>
			  	<td>
			  		姓名<font color="red">*</font>：
			  	</td>
			  	<td>
			  		<input class="txt" type="text" name="username"/>
					<span style="color:red;">${NameError}</span>
			  	</td>
			  </tr>	
			   <tr>
			  	<td>
			  		密码<font color="red">*</font>：
			  	</td>
			  	<td>
			  		<input class="txt" type="password" name="password"/>
			  	</td>
			  </tr>	
			   <tr>
			  	<td>
			  		再次输入密码<font color="red">*</font>：
			  	</td>
			  	<td>
			  		<input class="txt" type="password" name="password2"/>
			  	</td>
			  </tr>	
			   <tr>
			  	<td>
			  		电话：
			  	</td>
			  	<td>
			  		<input class="txt" type="text" name="phone"/>
			  	</td>
			  </tr>	
			   <tr>
			  	<td>
			  		Email：
			  	</td>
			  	<td>
			  		<input class="txt" type="email" name="email"/>
			  	</td>
			  </tr>	
			  <tr>
			  	<td>
			  		验证码：
			  	</td>
			  	<td >
			  		<input class="txt" type="text" style="width:100px;" name="sCode"/>
					<img src="${pageContext.request.contextPath }/ValidateCodeImg" style="margin-left: 50%"/><br>
					<span style="color:red;">${CodeError}</span>
			  	</td>
		     </tr>
			  <tr>
			  	<td id="msg" colspan="2" style="color:red;" align="center">
			  	</td>
			  </tr>
			  <tr>
			  	<td colspan="2" align="center">	
			  		<input type="submit" class="OperBtn" value="注册" style="margin:5px;"/>
			  		<button class="OperBtn" style="margin:5px;">取消</button>
			  	</td>
			  </tr>	
  		</table>
  		</form>
  </body>
</html>
  		