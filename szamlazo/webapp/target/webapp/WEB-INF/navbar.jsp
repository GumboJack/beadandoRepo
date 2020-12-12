<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script><script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>

<jsp:include page="modal.jsp" />
<nav class="navbar navbar-expand-lg navbar-fixed-top navbar-light bg-light">
        <img src="https://upload.wikimedia.org/wikipedia/commons/b/b2/Bootstrap_logo.svg" width="30" height="30" alt="">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Menu
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="items">Items</a>
                    <a class="dropdown-item" href="login">Login</a>
                    <a class="dropdown-item" href="register">Regsiter</a>
                    <a class="dropdown-item" href="cart">Shopping Cart</a>
                </div>
            </li>
        </ul>
        <a class="navbar-brand" href="user" id="loginName"></a>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
    <script>

        $(document).ready(function(){
            var username = Cookies.get('user');
            console.log(username);

            $.ajax({
                method: 'GET',
                url: 'http://localhost:8081/rest/authorize',
                contentType: 'application/json;charset=UTF-8',
                async: true,
                success: function (data){
                    console.log(data);

                    if (data.error !== '0'){
                        username = "";
                        Cookies.remove('user');
                    }
                },
                error: function (xhr) {
                    console.log(xhr.responseText);
                    showErrorModal("Service not available!")
                }
            });

            if (typeof username !== "undefined"){
                $('#loginName').text(username);
            }

        });
    </script>

</nav>
