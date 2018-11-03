
/**
 * 增加一本书
 */
function _add(id){//所有的操作都必须要先操作Session然后才操作页面数据
	//alert("===="+id);
	alert(id)
	var url = "/bookstore/user/buy?action=modifyCar&id="+id+"&num=1";
	window.location.href=url;
}
/**
 * 删除一本书
 */
function _del(id){
	var url = "/bookstore/user/buy?action=modifyCar&id="+id+"&num=-1";
	window.location.href=url;

}

/**
 * 提交生成订单
 */
function sure(){
	var tb = document.getElementById("table");
	if(tb.rows.length<=3){
		return;
	}
	window.location.href=path+"/user/address?action=queryAddress";
}
//清除购物车
function cleanAll() {
    var url = "/bookstore/user/buy?action=cleanAll";
    window.location.href=url;
}

