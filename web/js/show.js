function _go(id,name,price,img){
	var url = "/bookstore/user/buy?action=addToCar&id="+id+
			"&name="+name+"&currentPrice="+price+"&img="+img;
	//对图书中文进行URI加密
	url = encodeURI(url);
	window.location.href=url;
}
