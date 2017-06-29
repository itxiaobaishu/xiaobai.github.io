<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	ul{list-style: none;}
</style>
</head>
<body>
<h1>超市管理</h1>
<input id = "count" type="hidden" value = "${count}">
<input id = "page" type="hidden" value = "${page}">
<ul>
	<li>
		<input type="checkbox" id = "sel">
		<span>编号</span>
		<span>超市名称</span>
		<span>超市负责人</span>
		<span>超市简介</span>
		<span>超市成立时间</span>
	</li>
	<%-- <c:set var = "index" value = "0"/> --%>
	<c:set var = "index" value = "${page*3}"/>
	<c:forEach items = "${list}" var = "li" >
	<c:set var = "index" value = "${index+1}"/>
	
		<li>
			<input type="checkbox" value = "${li.id}" class = "select" >
			<span>${index}</span>
			<span>${li.name}</span>
			<span>${li.people}</span>
			<span>${li.intro}</span>
			<span>${li.time}</span>
		</li>
	</c:forEach>	
</ul>
<button class = "add">添加</button>
<button class = "del">批量删除</button>
<button class = "upd">修改</button>
<a class = "start">首页</a>
<a class = "up">上一页</a>
<a class = "down">下一页</a>
<a class = "end">末页</a>

</body>
<script type="text/javascript" src = "../jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	var p = $('#page').val();
	var c = $('#count').val();
/* 首页 */
$('.start').click(function(){
	location.href="shopList?i=0";
})
/* 下一页  */
$('.down').click(function(){
	var q=Math.floor(c/3)-1;
	if(p==q){
		alert("当前为最后一页");
		return;
	}
	location.href="shopList?i="+(parseInt(p)+1);
})
/* 上一页   */
$('.up').click(function(){
	if(p==0){
		alert("当前为第一页");
		return;
	}
	location.href="shopList?i="+(p-1);
})
/* 末页 */
$('.end').click(function(){
	location.href="shopList?i="+ Math.floor(c/3);
	
})
/* 修改 */
$('.upd').click(function(){
	var ids = '';
	$('.select:checked').each(function(){
		ids += ',' + $(this).val();
	});
	location.href="shopUpdate?ids="+ids.substring(1);
	
	
})


/* 添加 */
$('.add').click(function(){
	location.href="shopAdd";
})
/* 批量删除 */
$('.del').click(function(){
	var ids = '';
	$('.select:checked').each(function(){
		ids += ',' + $(this).val();
	});
	location.href="shopDelete?ids="+ids.substring(1);
})
/* 全选  反选 */
$('#sel').click(function(){
	$('.select').each(function(){
		if($(this).attr('checked') == 'checked'){
			$(this).attr('checked',false);
		}else{
			$(this).attr('checked',true);
		}
	})
})
</script>
</html>