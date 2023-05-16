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
	<div class="container" style="min-height: 1000px;">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<center><h2>Регистрация</h2></center>
<br>
		<form>
			<input type="text" class="form-control" placeholder="Введите логин"><br>
			<input type="password" class="form-control" placeholder="Введите пароль"><br>
			<input type="password" class="form-control" placeholder="Подтвердите пароль"><br>
			<input type="email" class="form-control" placeholder="Введите почту"><br>

			<center>
				<input type="button" class="btn btn-success" value="Регистрация">
			</center>
		</form>

			</div>
			<div class="col-md-4"></div>
		</div>
	</div>


	<?php require("add/footer.php"); ?>


	<script src="js/jquery-3.6.0.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>