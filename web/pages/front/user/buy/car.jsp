<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
  	<link rel="stylesheet" type="text/css" href="<c:url value='/css/Pub.css'/>"/>
  	<script type="text/javascript">
  		var path = "${pageContext.request.contextPath}";
  	</script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/car.js"></script>
  	<style type="text/css">
  		*{
  			font-size:10pt;
  		}
  		table{
  			border:1px solid gray;
  			border-collapse: collapse;
  			width:80%;
  		}
  		td{
  			border:1px solid gray;
  			padding:5px;
  		}
  		.head{
  			background:#9393FF;
  		}
  		.head td{
  			text-align:center;
  			font:bold;
  			padding:5px;
  		}
  		.even{
  			background:#DDDDFF;
  		}
  		td button{
  			margin-left:7px;
  		}
  		.oper{
  			cursor:pointer;
  			border:0px;
  			background:transparent;
  		}
  	</style>
  </head>
  <body>
  		<p>以下是你买的所有图书-><font color="red" style="font:bold;">订单明细</font>-->选择/添加收货地址->确认订单->网银付款或到货付款->查收货物</p>
  		<table id="table">

  			<tr class="head">
  				<td>
  					书名
  				</td>
  				<td>
  					价格
  				</td>
  				<td>
  					数量
  				</td>
  				<td>
  					合计
  				</td>
  				<td>
  					增/删
  				</td>
  			</tr>
           <c:forEach items="${sessionScope.carMap}" var="car">
  				<tr>
	  				<td>
	  					<a href="${pageContext.request.contextPath}/index?action=queryBook&id=${car.value.id}">
                            ${car.value.name}
	  					</a>
	  				</td>
	  				<td>
                       ${car.value.currentPrice}
	  				</td>
	  				<td>
                       ${car.value.count}
	  				</td>
	  				<td>
						<fmt:formatNumber pattern="#.##" value="${car.value.count*car.value.currentPrice}" />
	  				</td>
					<c:set var="total" value="${total+car.value.count*car.value.currentPrice}"/>
	  				<td>
	  					<input class="oper" type="button"  onclick="_add('${car.value.id}')" value="+"/>
	  					&nbsp;
	  					<input class="oper" type="button"  onclick="_del('${car.value.id}')" value="-"/>
	  				</td>
  				</tr>
		   </c:forEach>
  			<tr class="head">
  				<td colspan="5" style="text-align:right;">
  					总金额：
  					<label id="sum">
						<%--格式化数据--%>
						<fmt:formatNumber pattern="#.##" value="${total}"/>

  					</label>
  					元
  				</td>
  			</tr>
  			<tr style="background:#E0E0E0;">
  				<td colspan="5" style="text-align:right;">
  					<button class="OperBtn" onclick="sure();" style="width:220px;">下一步->选择收货地址</button>
  					<button class="OperBtn" onclick="cleanAll()" style="width:120px;">全部取消</button>
  				</td>
  			</tr>
  		</table>
  </body>
</html>
