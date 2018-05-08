<!DOCTYPE html>
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
                width: 10%;
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
        </style>
    </head>

    <body>
		<h2>Man Power</h2>

        <button onclick="document.getElementById('login').style.display='block'" style="width:10%">Login</button>
        <button onclick="myFunction()" class="dropbtn">Register</button>

        <div id="login" class="modal">

            <form class="modal-content animate" action="./ManPowerController" method="POST">
              
                <div class="imgcontainer">
                    <span onclick="document.getElementById('login').style.display='none'" class="close" title="Close Modal">&times;</span>
				<h2> Login Form</h2>
                </div>		
                <div class="container">
                    <label for="email"><b>Email</b></label>
                    <input type="email" placeholder="Enter Email" name="email" required>
                    <br>
                    <label for="password"><b>Password</b></label>
                    <input type="password" placeholder="Enter Password" name="password" required>
                    <br>
                    <input type="radio" name="role" value="user"> User<br>
                    <input type="radio" name="role" value="force"> Force<br>
                    <input type="submit" name="login" value="Login ">
                    <input type="hidden" name="action" value="login">
                </div>

                <div class="container" style="background-color:#f1f1f1">
                    <button type="button" onclick="document.getElementById('login').style.display='none'" class="cancelbtn">Cancel</button>
                    <span class="psw"><a href="./home.jsp">Register?</a></span>
                </div>
            </form>
            <br>

        </div>
        <div id="userregister" class="modal">

            <form name = form class="modal-content animate" action="./ManPowerController" method="POST" onsubmit = "validateForm()">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('userregister').style.display='none'" class="close" title="Close Modal">&times;</span>

                </div>

                <div class="container">
                    <h2> User Registration</h2>
                    <label for="firstName"><b>First Name : </b></label>
                    <input type="text" name="firstName" placeholder="First Name" required><br>
                    <label for="lastName"><b>Last Name : </b></label>
                    <input type="text" name="lastName" placeholder="Last Name" required>
                    <br>
                    <label for="email"><b>Email : </b></label>
                    <input type="email" placeholder="Enter Email" name="email" required>
                    <br>
                    <label for="password"><b>Password : </b></label>
                    <input type="password" placeholder="Enter Password" name="password" required>
                    <br>
                    <label for="phoneNumber"><b>Phone Number : </b></label>
                    <input type="text" name="phoneNumber" placeholder="Phone Number" required>
                    <br>
                    <label for="address"><b>Address :<br> </b></label>
                    <label for="doorNumber"></label>
                    <input type="text" name="doorNumber" placeholder="Door Number" required> <br>
                    <label for="street"></label>
                    <input type="text" name="street" placeholder="Street" required>
                    <br>
                    <label for="city"></label>
                    <input type="text" name="city" placeholder="City" required> <br>
                    <label for="state"></label>
                    <input type="text" name="state" placeholder="State" required> <br>
                    <label for="postalCode"></label>
                    <input type="text" name="postalCode" placeholder="Postal Code" required><br>
                    <label for="country"></label>
                    <input type="text" name="country" placeholder="Country" required> <br>
                    <br>
                    <input type="submit" name="userregister" value="Register">
                    <input type="hidden" name="action" value="userregister">
                </div>

                <div class="container" style="background-color:#f1f1f1">
                    <button type="button" onclick="document.getElementById('userregister').style.display='none'" class="cancelbtn">Cancel</button>
                </div>
            </form>
            <br>

        </div>
        <div id="forceregister" class="modal">

           <form name = form1 class="modal-content animate" action="./ManPowerController" method="POST" onsubmit ="return validateForm1()">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('forceregister').style.display='none'" class="close" title="Close Modal">&times;</span>

                </div>

                <div class="container">
                    <h2> Force Registration</h2>
                    <label for="firstName"><b>First Name : </b></label>
                    <input type="text" name="firstName" placeholder="First Name" required><br>
                    <label for="lastName"><b>Last Name : </b></label>
                    <input type="text" name="lastName" placeholder="Last Name" required>
                    <br>
                    <label for="email"><b>Email : </b></label>
                    <input type="email" placeholder="Enter Email" name="email" required>
                    <br>
                    <label for="password"><b>Password : </b></label>
                    <input type="password" placeholder="Enter Password" name="password" required>
                    <br>
                    <label for="phoneNumber"><b>Phone Number : </b></label>
                    <input type="text" name="phoneNumber" placeholder="Phone Number" required>
                    <br><br>
                    <select name="subserviceType">
     						<c:forEach items="${serviceTypesList}" var="serviceType">
   							<c:forEach items = "${serviceType.subServiceType}" var="subserviceType"><br>
    							<option value=${subserviceType.subServiceTypeId}>${serviceType.name}-${subserviceType.name}</option>
    						</c:forEach>
    						</c:forEach>
					</select>
                    <br><br>
                    <label for="experience"><b>Experience : </b></label>
                    <input type="text" name="experience" placeholder="Experience" required>
                    <br>
                    <label for="costPerHour"><b>Cost Per Hour: </b></label>
                    <input type="text" name="costPerHour" placeholder="Cost Per Hour" required>
                    <br>
                    <label for="address"><b>Address :<br> </b></label>
                    <label for="doorNumber"></label>
                    <input type="text" name="doorNumber" placeholder="Door Number" required> <br>
                    <label for="street"></label>
                    <input type="text" name="street" placeholder="Street" required>
                    <br>
                    <label for="city"></label>
                    <input type="text" name="city" placeholder="City" required> <br>
                    <label for="state"></label>
                    <input type="text" name="state" placeholder="State" required> <br>
                    <label for="postalCode"></label>
                    <input type="text" name="postalCode" placeholder="Postal Code" required><br>
                    <label for="country"></label>
                    <input type="text" name="country" placeholder="Country" required> <br>

                    <br>
                    <input type="submit" name="forceregister" value="Register">
                    <input type="hidden" name="action" value="forceregister">
                </div>

                <div class="container" style="background-color:#f1f1f1">
                    <button type="button" onclick="document.getElementById('forceregister').style.display='none'" class="cancelbtn">Cancel</button>
                </div>
            </form>
            <p> Hello </p>
             <br>

        </div>
        <div class="dropdown">
            <div id="myDropdown" class="dropdown-content">
                <a button onclick="document.getElementById('userregister').style.display='block'" style="width:auto;">User </a>
                <a button onclick="document.getElementById('forceregister').style.display='block'" style="width:auto;">Force</a></button>

            </div>

        </div>
<p align = "center">
			<img id = "" src = "http://1.bp.blogspot.com/-3KguoHFoO9s/TgGD83Z_n4I/AAAAAAAAAOQ/kh6V07q0QTs/s1600/zoozoo_wallpaper_11_1024x768.jpg" alt = "images"></p>
        

        <script>
            // Get the modal
            var modal = document.getElementById('login');
            var modal = document.getElementById('userregister');
            var modal = document.getElementById('forceregister');
            ///When the user clicks anywhere outside of the modal, close it
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }

            function myFunction() {
                document.getElementById("myDropdown").classList.toggle("show");
            }

            window.onclick = function(event) {
                if (!event.target.matches('.dropbtn')) {

                    var dropdowns = document.getElementsByClassName("dropdown-content");
                    var i;
                    for (i = 0; i < dropdowns.length; i++) {
                        var openDropdown = dropdowns[i];
                        if (openDropdown.classList.contains('show')) {
                            openDropdown.classList.remove('show');
                        }
                    }
                }
            }
            function validateForm(){
            	var password = form.password.value;
            	if(password.length < 8)
            		alert("Password must be atleast 8 characters");
            	return false;
            }
            function validateForm1(){
            	var password = form1.password.value;
            	if(password.length < 8)
            		alert("Password must be atleast 8 characters");
            	return false;
            }
        </script>

    </body>

    </html>