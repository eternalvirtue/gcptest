<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
<title>住所検索</title>
</head>

<body>
	<h1>検索条件入力</h1>
	<form class="form-horizontal" method="POST" action="/searchAddress">
		<fieldset class="form-group">
			<label for="searchAddress" class="control-label col-sm-2">住所</label>
			<div class="col-sm-3">
				<input class="form-control" id="searchAddress" name="searchAddress"
					placeholder="例：東京都墨田区隅田" type="text">
			</div>
		</fieldset>
		<fieldset class="form-group">
			<label for="submit" class="control-label col-sm-2"></label>
			<div class="col-sm-6">
				<button type="submit" class="btn btn-primary">確認</button>
			</div>
		</fieldset>
	</form>
</body>
</html>
