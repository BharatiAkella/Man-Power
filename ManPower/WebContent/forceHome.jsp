<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <html>
        <style>
        	  button {
                background-color: grey;
                color:azure;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: auto;
            }

            button:hover {
                opacity: 0.8;
            }
        	 
            table {
                width: 60%;
            }

            table,
            th,
            td {
                border: 2px solid black;
                border-collapse: collapse;
                background:aliceblue;
            }

            th,
            td {
                padding: 10px;
                text-align: center;
                text-color: white;
            }

            table tr:nth-child {
                background-color: #fff;
            }

            table th {
                background-color:darkslategray;
                color:azure;
            }
            #popup {
                width: 100%;
                height: 200px;
                float: left;
            }
            body{
        		background-image: url(http://hdcoolwallpapers.com/wp-content/uploads/2015/01/HD-3D-Abstract-Wallpapers-606.jpg);
               	background-repeat: no-repeat;
            }
        </style>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

            <title>force page</title>
        </head>

        <body >
		<header>
		
            <h1 style= "text-align: center; color:#003300; background-color:silver; font-size:300%;">Force Home</h1>
            <div class = "logout"><a href="./ManPowerController?action=logout"><button style= "float: right">Logout</button></a></div>
            </header>
            <div id = "table" >
            <form class="modal-content animate" action="./ManPowerController" method="POST" 
          >
                <div class="imgcontainer">
                <div id="bid" class="modal">
                    <table border="1" align="center" width=50%>
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
                                <td><input type="text" placeholder="enter amount" name="amount"></td>
                                <td><input type="submit" name="bid" value="Bid"></td>

                                <form action="./ManPowerController" method="post">
                                    <div>

                                        <input type="hidden" name="action1" value="${forceWorkList.workId }">
                                        <input type="hidden" name="action" value="Bid">

                                    </div>
                                </form>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
            </form>
            </div>
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