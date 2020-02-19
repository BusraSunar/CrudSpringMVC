<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.sql.*"%>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Sign In</title>

    
    <spring:url value="/resources/style.css" var="mainCss2" />
	<link href="${mainCss2}" rel="stylesheet" />
   
  </head>
  <body>
    <header>
      <h1>Sign In</h1>
    </header>
    <section class="container">
      <form id="my-form" action="signIn" >
        <h1>Sign in</h1>
        <div>
          <label for="name">Name:</label>
          <input type="text" id="name" name="name">
        </div>
        <div>
          <label for="email">Email:</label>
          <input type="text" id="email" name="email">
        </div>
        <input class="btn" type="submit" value="Sign in"></input>
      </form>
    </section>
  </body>
</html>
    