<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.api.services.bigquery.model.TableRow" %>
<%@ page import="com.google.api.services.bigquery.model.TableCell" %>
<%@ page import="java.util.List" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<title>住所リスト</title>
</head>

<body>
	<h1>住所リスト</h1>
<%
	List<String> rows = (List<String>)session.getAttribute("ZIPDATA");
	if (rows == null) {
%>
<p>顧客情報なし</p>
<%
	} else {
%>
<table class="table table-hover">
  <thead>
    <tr>
      <th>#</th>
      <th>住所データ</th>
    </tr>
  </thead>
  <tbody>
<%
    	for (int i=0; i<rows.size(); i++ ) {
%>
           <tr>
              <th scope="row"><%=i+1%></th>
              <td><%=rows.get(i)%></td>
            </tr>
<%
    	}
    }
%>
</tbody>
</table>

</body>
</html>
