<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<style>

              table {
    width:60%;
}
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 8px;
    text-align: center;
}
table tr:nth-child {
   background-color: #fff;
}
table th {
    background-color: black;
    color: white;
}
                #popup {
                    width: 100%;
                    height: 200px;
                    float: left;
                }
            </style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<title>force page</title>
</head>
<body>

Force Home
<form class="modal-content animate" action="./ManPowerController" method="POST">
	<div id = "bid" class= "modal">
	<table border="1" align ="center" width = 50%>
            	<tr>
                	<th>Name</th>
                	<th>Address</th>
                	<th>Description</th>
                	<th>Bid Amount</th>
            	</tr>
            	
            	<c:forEach items="${forceWorkList}" var="forceWorkList">
            	<tr>
                  <td>${forceWorkList.user.firstName}</td>
                  <td>${forceWorkList.address.state}</td>
                  <td>${forceWorkList.description}</td>
                  <td><input type = "text" placeholder = "enter amount" name = "amount" ></td>
                  <td><input type = "submit"  name = "bid" value = "Bid"></td>
             
              <form action = "./ManPowerController" method = "post">
         		<div>
         			
         			<input  type = "hidden" name = "action1" value= "${forceWorkList.workId }">
         			<input type="hidden" name="action" value="Bid">
         			
         		</div>
              </form> </tr>
            </c:forEach>  
             
              </table>
              </div></form>
              	<script>
        	 
                    var modal = document.getElementById('Bid');
                    window.onclick = function(event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                        if (event.target == modal1) {
                            modal.style.display = "none";
                        }
                    }
                    </script>
</body>
</html>
                 