<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Enter Post</title>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="Home.html">Welcome</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
      <li><a href="home.html">Welcome</a></li>
      <li><a href="GetPost.jsp">Post Stream</a></li> 
         
      </ul>
    </div>
  </div>
</nav>
${message}
<form action="Insert" method="post">
<input type=hidden value="EnterPost" name="currentpage">
<label >Enter your Post: </label>
<input  type="text" name="post" required ><br>
<input type="submit" value="Enter" id="submit">
</form>
</body>
</html>