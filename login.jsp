<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
    <!--favicon -->
    <link href="images/faviconn.PNG" rel="icon">
    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="fonts/fontawesome-5.15.4/css/all.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style-form.css">
    <link rel="stylesheet" href="css/edit-style.css">
</head>
<body>
<div class="main">
<!-- Sing in  Form -->
    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <figure><img src="images/signin-image.jpg" alt="sing up image"></figure>
                    <a href="#" class="signup-image-link">Create an account</a>
<%--                   1,2.  Chuyển đén trang forgotpassword khi người dùng bấm vào nút Forgot Password--%>
                    <a href="forgotpassword" class="signup-image-link">Forgot Password</a>
                </div>

                <div class="signin-form">
                    <h2 class="form-title">Sign up</h2>

                    <h4 class="text-danger"></h4>
                    <form method="post" class="login-form" id="login-form" >
                        <div class="input-line">
                            <div class="form-group">
                                <label ><i class="fas fa-user material-icons-name"></i></label>
                                <input type="text" value="" name="Username" id="username" class="styled-input" placeholder="Your Username"/>
                            </div>
                            <span class="form-message"></span>
                        </div>
                        <div class="input-line">
                            <div class="form-group">
                                <label ><i class="fas fa-lock"></i></label>
                                <input type="password" value="" name="Password" id="password" class="styled-input" placeholder="Your Password"/>
                            </div>
                            <span class="form-message"></span>
                        </div>

                        <div class="form-group">
                            <input style="width: auto; display: inline-block" type="checkbox" value="1" name="remember" id="remember-me" class="agree-term" />
                            <label for="remember-me" class="label-agree-term">Remember me</label>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signin" id="signin" class="form-submit" value="Login"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>

<%--Validate Form--%>
<script src="js/validate.js"></script>
<%--login--%>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        Validator({
            form: '#login-form',
            formGroupSelector: '.input-line',
            errorSelector: '.form-message',
            rules: [
                Validator.isRequired('#username', 'Please enter your username'),
                Validator.minLength('#password', 3)
            ]
        });

    });
</script>

</body>
</html>
