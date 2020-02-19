<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.sql.*"  import = "java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	  <!DOCTYPE html>
	<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    
	    <spring:url value="/resources/rehberStyle.css" var="rehberStyle" />
		<link href="${rehberStyle}" rel="stylesheet" />
		
		<script src="<c:url value="/resources/core/jquery.1.10.2.min.js" />"></script>
		<script src="<c:url value="/resources/core/jquery.autocomplete.min.js" />"></script>
		
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
		
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	    <spring:url value="/resources/rehberStyle.css" var="rehber" />
		<link href="${rehber}" rel="stylesheet" />
 		
	    <title>Rehber</title>
	</head>
	<body >
	    <header >
	        <h1 >Rehber</h1>
	    </header>
	    
	    <div class="search-box">
    		<form action="display" method="post" class="form" onsubmit="">
		        <input class="search-txt" id="barName" name="searchBarName" placeholder="Name" formaction = "">
		        <a href="#" class="search-btn" style="text-decoration: none;">
		            <i class="fas fa-search"></i>
		        </a>
	        </form>
	    </div>
	    	<form action="addTransfer">
	    		<button type="submit" class="btn2" style="text-decoration: none;">Add New Data</button>
	    	</form>
		    
		    <form action="logout" method="post">
		   		<button  class="btn3" type="submit" >Logout</button>
	    	</form>
    	<div class="divv">
			<form action="update" method="post">
					<label class ="labels">Name</label>
					<input  type="text" class="textBox" name="textName" id="textName" value="${nameText}" >
					<label class ="labels">Email</label>
					<input  type="text" class="textBox" name="textEmail" id = "textEmail" value="${emailText}" >
					<button type="submit"  class="btn4" >Update</button>
			</form>	
        </div>
	        <table id="rehber" align="center"  >
		        <thead>
		            <tr bgcolor="#333">
		                <th  style="width: 0%;"><font color="#fff">ID</font></th>
		                <th  style="width: 0%;"><font color="#fff">NAME</font></th>
		                <th  style="width: 0%;"><font color="#fff">EMAIL</font></th>
		                <th  style="width: 100%;"><font color="#fff">ACTION</font></th>    
		            </tr>
		        </thead>
		        <TBody>
					<c:forEach items="${data}" var="list">
					<tr>
						<td><input readonly name="id" id="id" value="<c:out value="${list.id}"/>"></td>
						<td><input readonly name="name" id="name" value="<c:out value="${list.name}"/>"></td>
						<td><input readonly name="email" id="email" value="<c:out value="${list.email}"/>"></td>
						<td>
							<form action="edit/${list.id}" method="post">
								<input type="submit" style="text-decoration: none; background:#333;" class="edit" value ="Edit">
							</form>
							<form action="delete/${list.id}" method="post">
    							<input type="submit" style="text-decoration: none; background: rgb(163, 2, 2);" class="edit"value="Delete">
							</form>
						</td>
					</tr>
					</c:forEach>
		        </TBody>
	    	</table>
   	 	<!-- </form>  -->	
   	 	<script>
 		$( document ).ready(function() {

 			var nameArray = new Array();
 			
 			<c:forEach var="row" items="${dataName}">
 				nameArray.push('${row}');
 			</c:forEach>
			$( function() {
			   
			    $( "#barName" ).autocomplete({
			      source: nameArray,
			      messages: {
			          noResults: '',
			          results: function(amount) {
			              return  ''
			          }
			      } 
			    });
			  } );
			
 		});		
 		</script>
	</body>
	</html>
		
		
