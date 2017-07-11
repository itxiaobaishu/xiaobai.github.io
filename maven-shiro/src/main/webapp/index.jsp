
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.apache.shiro.authc.UnknownAccountException"%>
<%@page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@page import="org.apache.shiro.authc.UsernamePasswordToken"%>
<%@page import="org.apache.shiro.authc.AuthenticationToken"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
//用用户名和密码做对比  使用servlet的方法
 String user = request.getParameter("username");
 String pwd = request.getParameter("password");
 //判断用户名和密码是否是空值
if(user != null && pwd != null){
    //得到一个Subject对象 ，这个对象表示的是shiro对象 ，可以在里边存取信息
	 Subject sub = SecurityUtils.getSubject();
	 String context = request.getContextPath();
	 try{
	     //调用login方法 来进行shiro的存储
	     //使用用户的登录信息创建令牌
	 	sub.login(new UsernamePasswordToken(user.toUpperCase(),pwd));
	 	out.println("登录成功");
	 }catch(IncorrectCredentialsException e){
		 out.println("{success:false,msg:'用户名与密码不正确！'}");
	 }catch(UnknownAccountException e){
		 out.println("{success:false,msg:'用户名不存在！'}");
	 }
	 return;
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>系统登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<table width="100%" height="100%">
		<tr>
			<td><table width="962" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="235" background="images/login_03.jpg">&nbsp;</td>
					</tr>
					<tr>
						<td height="53"><table width="100%" border="0"
								cellspacing="0" cellpadding="0">
								<tr>
									<td width="394" height="53" background="images/login_05.gif">&nbsp;</td>
									<td width="206" background="images/login_06.gif"><table
											width="100%" border="0" cellspacing="0" cellpadding="0">
											<form method="post">
												<tr>
													<td width="20%" height="25"><div align="right">
															<span class="STYLE1">用户名</span>
														</div></td>
													<td width="57%" height="25"><div align="center">
															<input type="text" name="username"
																style="width: 105px; height: 17px; background-color: #292929; border: solid 1px #7dbad7; font-size: 12px; color: #6cd0ff;">
														</div></td>
													<td width="27%" height="25">&nbsp;</td>
												</tr>
												<tr>
													<td height="25"><div align="right">
															<span class="STYLE1">密码</span>
														</div></td>
													<td height="25"><div align="center">
															<input type="password" name="password"
																style="width: 105px; height: 17px; background-color: #292929; border: solid 1px #7dbad7; font-size: 12px; color: #6cd0ff;">
														</div></td>
													<td height="25"><input type="image"
														src="images/dl.gif" onclick="document.submit()" /></td>
												</tr>
											</form>
										</table></td>
									<td width="362" background="images/login_07.gif">&nbsp;</td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<td height="213" background="images/login_08.gif">&nbsp;</td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>
