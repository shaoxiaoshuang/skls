<%--
  Created by IntelliJ IDEA.
  User: S
  Date: 2020/11/30
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课间小憩网</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script>
        function setFlag(x) {
            document.forms[0].transFlag.value = x;
            return;
        }
    </script>
</head>
<body bgcolor="#CCCCFF" text="#000000">
<table width="490" border="0" align="center">
    <tr>
        <td align="center"><img src="image/header.gif" width="468" height="60"></td>
    </tr>
    <tr>
        <td align="center"> 【<a href="all.do">用户管理</a>】【<a href="listNews.html">新闻管理</a>】【商铺管理】【论坛管理】【<a
                href="welcome.html">网站首页</a>】
        </td>
    </tr>
</table>
<br>
<table width="350" border="0" align="center">
    <tr>
        <td align="center" height="54"><font size="7"><b><font face="华文彩云" size="6"
                                                               color="#666699">用户信息更改</font></b></font></td>
    </tr>
    <tr>
        <td>
            <table width="350" border="1" cellpadding="0" cellspacing="1" bordercolor="#660033" bgcolor="#99CCCC">
                <form action="update.do" method="post">
                    <tr>
                        <td align="right" width="35%" height="35">编 号：</td>
                        <td height="35" width="65%">
                            <input type="text" name="userId" value="<%=request.getParameter("id")%>" readonly>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="35%" height="35">用 户：</td>
                        <td height="35" width="65%">
                            <input type="text" name="userName" value="<%=request.getParameter("uname")%>">
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="35%" height="35">密 码：</td>
                        <td width="65%" height="35">
                            <input type="password" name="password" value="<%=request.getParameter("upwd")%>">
                        </td>
                    </tr>
                    <tr align="center">
                        <td colspan="2" height="35">
                            <input type="hidden" name="transFlag">
                            <input type="submit" name="Submit" value="修改" style="width:80">
                            <input type="submit" name="Submit" value="删除" style="width:80">
                            <input type="reset" name="Submit2" value="复位" style="width:80">
                        </td>
                    </tr>
                </form>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
