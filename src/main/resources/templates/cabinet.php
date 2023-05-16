<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrap-grid.css">
	<link rel="stylesheet" href="css/bootstrap-utilities.css">
	<link rel="stylesheet" href="css/bootstrap-reboot.css">
	<?php require("css/css.php"); ?>
</head>
<body>

	<?php require("add/menu.php"); ?>

<br><br>
	<div class="container border_news" style="min-height: 1000px;">
		<div class="row">
			<div class="col-md-12">
				<h2>Кабинет: </h2>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">
				<img src="image/user.png" class="img-fluid">
				<br><br>
				<form>
					<input type="file" class="form-control">
					<input type="button" value="Поменять" class="btn btn-success">
				</form>
			</div>
			<div class="col-md-9">
				<h2>Добро пожаловать: USERNAME</h2> <br>
		<form>
			<input type="text" class="form-control" placeholder="Введите новый никнейм"><br>
			<input type="email" class="form-control" placeholder="Введите новую почту"><br>
			<input type="button" value="Поменять" class="btn btn-success">
		</form>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-12">
				<h2>Добавить новость:</h2>
<form>
	<input type="text" class="form-control" placeholder="Введите заголовок"><br>
	<textarea class="form-control" placeholder="Введите новость" rows="10"></textarea><br>
	<input type="file" class="form-control"><br>
	<input type="button" class="btn btn-primary" value="Добавить новость">
</form>
			</div>
		</div>	
	</div>


	<?php require("add/footer.php"); ?>


	<script src="js/jquery-3.6.0.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>