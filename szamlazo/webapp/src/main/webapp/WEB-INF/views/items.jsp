<%--
  Created by IntelliJ IDEA.
  User: lmarc
  Date: 2020. 11. 26.
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Items</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<jsp:include page="../modal.jsp" />

<div class="container">

    <div class="row">

        <div class="col-lg-3">

            <h1 class="my-4">Items</h1>
            <label for="lg1"><h3>Categories</h3></label>
            <div class="list-group" id="lg1">
            </div>
            <label for="lg2"><h3>Manufacturers</h3></label>
            <div class="list-group" id="lg2">
            </div>

        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

            <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#" data-slide-to="0" class="active"></li>
                    <li data-target="#" data-slide-to="1"></li>
                    <li data-target="#" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="carousel-item active">
                        <img class="d-block img-fluid" src="https://papers.co/wallpaper/papers.co-av16-apple-dark-paint-macbook-illustration-art-red-32-wallpaper.jpg" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block img-fluid" src="https://images.firstpost.com/wp-content/uploads/2019/03/1547532915_iPad-Pro-cover-1024.jpg" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block img-fluid" src="https://iphonekozosseg.hu/wp-content/uploads/2015/03/Apple-Watch-Wallpaper-iPad-1024x768.jpg" alt="Third slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

            <div class="row" id="datarow">

            </div>
            <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<script>

    var items = {};
    var itemtypes = {};
    var manufacturers = {};

    $(document).ready(function() {

        $.ajax({
            method: 'GET',
            url: 'http://localhost:8081/rest/items',
            contentType: 'application/json;charset=UTF-8',
            async: true,
            success: function (data) {

                if (data.error === '0') {
                    items = data.items;
                    console.log(items);
                    $.each(items, function (index, value) {
                        $('#datarow').append('<div class="col-lg-4 col-md-6 mb-4">\n' +
                            '                    <div class="card h-100">\n' +
                            '                        <a href="#"><img class="card-img-top" src="-" alt="" onerror="this.onerror=null; this.src = \'https://icuccok.hu/wp-content/uploads/2018/04/Apple_logo_szivarvany.png\'"></a>\n' +
                            '                        <div class="card-body">\n' +
                            '                            <h4 class="card-title">\n' +
                            '                                <a class="a2" href="#">' + value.manufacturer +' </a>' +
                            '                                <a>' + value.name + '</a>\n' +
                            '                            </h4>\n' +
                            '                            <h5>$' + value.price + '</h5>\n' +
                            '                            <p class="card-text">' + value.description + '</p>\n' +
                            '                        </div>\n' +
                            '                        <div class="card-footer">\n' +
                            '                            <button type="button" class="btn btn-dark" onclick="addToCart(' +index+');">Add to cart</button>\n' +
                            '                        </div>\n' +
                            '                    </div>\n' +
                            '                </div>')
                    });
                } else {
                    showErrorModal(data.msg);
                }
            },
            error: function (xhr) {
                console.log(xhr.responseText);
                showErrorModal("Unknown error!")
            }
        });

        $.ajax({
            method: 'GET',
            url: 'http://localhost:8081/rest/itemtypes',
            contentType: 'application/json;charset=UTF-8',
            async: true,
            success: function (data) {

                if (data.error === '0') {
                    itemtypes = data.itemTypes;
                    console.log(itemtypes);
                    $.each(itemtypes, function (index, value) {
                        $('#lg1').append(
                            '<a class="a3" href="#" class="list-group-item">' + value.toLowerCase() + '</a>'
                        );
                    });
                }
            },
            error: function (xhr) {
                console.log(xhr.responseText);
                showErrorModal("Unknown error!")
            }
        });

        $.ajax({
            method: 'GET',
            url: 'http://localhost:8081/rest/manufacturers',
            contentType: 'application/json;charset=UTF-8',
            async: true,
            success: function (data) {

                if (data.error === '0') {
                    manufacturers = data.manufacturers;
                    console.log(manufacturers);
                    $.each(manufacturers, function (index, value) {
                        $('#lg2').append(
                            '<a class="a3" href="#" class="list-group-item">' + value + '</a>'
                        );
                    });
                }
            },
            error: function (xhr) {
                console.log(xhr.responseText);
                showErrorModal("Unknown error!")
            }
        });
    });

    function addToCart(index) {
        var cookieValue = Cookies.get("cart");
        var cart;

        if (typeof cookieValue === "undefined"){
            cart = [];
        } else {
            cart = $.parseJSON(cookieValue);
        }

        console.log(cart);

        var item = items[index];

        var i = 0;
        while (i < cart.length && cart[i].barcode !== item.barcode){
            i = i+1;
        }

        if (i < cart.length){
            cart[i].quantity = cart[i].quantity + 1;
            showItemAddedModal(cart[i].manufacturer + " " + cart[i].name);
        } else {
            var cartitem = {};
            cartitem.barcode = item.barcode;
            cartitem.name = item.name;
            cartitem.manufacturer = item.manufacturer;
            cartitem.price = item.price;
            cartitem.quantity = 1;
            console.log(cartitem);
            cart.push(cartitem);
            showItemAddedModal(cartitem.manufacturer + " " + cartitem.name);
        }

        Cookies.remove('cart');
        Cookies.set("cart", JSON.stringify(cart), { path: '/', expires: 1});

    };

</script>

<style>
    .carousel {
        width:900px;
        height:770px;
    }

    .a2 {
        color: darkslategray;
    }

    .a3 {
        text-transform: capitalize;
    }

</style>

</body>
</html>
