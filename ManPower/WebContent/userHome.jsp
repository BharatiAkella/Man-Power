<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
         <html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
       
            .dropbtn {
                background-color: #3498DB;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            .dropbtn:hover,
            .dropbtn:focus {
                background-color: #2980B9;
            }

            .dropdown {
                position: relative;
                display: inline-block; //overflow: hidden;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 160px;
                overflow: auto;
                box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown a:hover {
                background-color: #ddd
            }

            .show {
                display: block;
            }

            body {
                font-family: Arial, Helvetica, sans-serif;
            }

            /* Full-width input fields */

            input[type=email],
            input[type=password] {
                width: 20%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }

            /* Set a style for all buttons */

            button {
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: auto;
            }

            button:hover {
                opacity: 0.8;
            }

            /* Extra styles for the cancel button */

            .cancelbtn {
                width: auto;
                padding: 10px 18px;
                background-color: #f44336;
            }

            /* Center the image and position the close button */

            .imgcontainer {
                text-align: center;
                margin: 24px 0 12px 0;
                position: relative;
            }

            img.avatar {
                width: 40%;
                border-radius: 50%;
            }

            .container {
                padding: 16px;
            }

            span.psw {
                float: right;
                padding-top: 16px;
            }

            /* The Modal (background) */

            .modal {
                display: none;
                /* Hidden by default */
                position: fixed;
                /* Stay in place */
                z-index: 1;
                /* Sit on top */
                left: 0;
                top: 0;
                width: 100%;
                /* Full width */
                height: 100%;
                /* Full height */
                overflow: auto;
                /* Enable scroll if needed */
                background-color: rgb(0, 0, 0);
                /* Fallback color */
                background-color: rgba(0, 0, 0, 0.4);
                /* Black w/ opacity */
                padding-top: 60px;
            }

            /* Modal Content/Box */

            .modal-content {
                background-color: #fefefe;
                margin: 5% auto 15% auto;
                /* 5% from the top, 15% from the bottom and centered */
                border: 1px solid #888;
                width: 50%;
                /* Could be more or less, depending on screen size */
            }

            /* The Close Button (x) */

            .close {
                position: absolute;
                right: 25px;
                top: 0;
                color: #000;
                font-size: 35px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: red;
                cursor: pointer;
            }

            /* Add Zoom Animation */

            .animate {
                -webkit-animation: animatezoom 0.6s;
                animation: animatezoom 0.6s
            }

            @-webkit-keyframes animatezoom {
                from {
                    -webkit-transform: scale(0)
                }
                to {
                    -webkit-transform: scale(1)
                }
            }

            @keyframes animatezoom {
                from {
                    transform: scale(0)
                }
                to {
                    transform: scale(1)
                }
            }

            /* Change styles for span and cancel button on extra small screens */

            @media screen and (max-width: 300px) {
                span.psw {
                    display: block;
                    float: none;
                }
                .cancelbtn {
                    width: 100%;
                }
        }
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
                body {
    				background-image: 
				}		
            </style>
        </head>
        
        <body >
      
            <button onclick="document.getElementById('postWork').style.display='block'" style="width:auto;">PostWork</button><br>
            <div id="postWork" class=" modal">
                <form class="modal-content animate" action="./ManPowerController" method="POST">
                    <h2> Work Form</h2>
                    <div class="imgcontainer">
                        <span onclick="document.getElementById('postWork').style.display='none'" class="close" title="Close Modal">&times;</span>
                    </div>
                    <div class="container">
                        Service Types :
                        <select name="subserviceType">
     						<c:forEach items="${serviceTypesList}" var="serviceType">
   							<c:forEach items = "${serviceType.subServiceType}" var="subserviceType"><br>
    							<option value=${subserviceType.subServiceTypeId}>${serviceType.name}-${subserviceType.name}</option>
    						</c:forEach>
    						</c:forEach>
					</select>
                        <br> Description : <textarea name="description" rows="6" cols="50"></textarea><br>
                        <input type="hidden" name="action" value="postWork">
                        <input type="submit" value="Post Work">
                    </div>
                </form>
            </div>
            <div class = "logout"><a href="./ManPowerController?action=logout"><button>Logout</button></a></div>
            
<table border="1" align="center" width=50%>
                    <tr>
                        <th>Service Type Name</th>
                        <th>Description</th>
                        <th>Date</th>
						<th>BID</th>
                        <c:forEach items="${workList}" var="work">
                            <tr>
                            <td>${work.subServiceType.name }</td>
                            <td>${work.description}</td>
                            <td>${work.date}</td>
                            <td><button onclick="document.getElementById('bid').style.display='block'" style="width:auto;">View Bid</button><br>
                            
                            <div id="bid" class=" modal">
                                 <form class="modal-content animate" action="./ManPowerController" method="POST">
                                  
                                      <div class="imgcontainer">
                                           <span onclick="document.getElementById('bid').style.display='none'" class="close" title="Close Modal">&times;</span>
                                      </div>
                                      <div class="container">
                                   		<h2>Bid</h2>
                                      <table border=1 width=70%>
                                      <tr>
                                      <th>ForceName</th>
                                      <th>BidAmount</th>
                                      </tr>
                                      <c:forEach items="${work.bid}" var="bid">
										<tr>
										<td>${bid.user.firstName}</td>
										<td>${bid.bidAmount }</td>
										<c:set var="accepted" value="${work.open}"/>
										<td>
											<div class="div_tdata">
												<c:if test="${accepted eq 'open'}">
													<input type = "hidden" name = "action1" value = "${work.workId}">
													<input type = "hidden" name = "action2" value = "${bid.workId }">
													<input type = "hidden" name = "action" value = "acceptBid"/>
													<input  type = "submit" value = "AcceptBid">
												</c:if>
												<c:if test="${accepted ne 'open'}"></c:if>
											</div>
										</td>
										<td>
											<div class="div_tdata">
												<div class="div_tdata"><a href="ManforceController?action=reject"><button>Reject</button></a></div>
											</div>
										</td>
									</c:forEach>
                                </table>

            </form>
            </div>
            </td>

            </tr>
            </c:forEach>
			</tr>


            </table>
            
           
            <script>
                var modal = document.getElementById('postWork');
                var modal = document.getElementById('bid');
               // var modal1 = document.getElementById('viewBid');
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