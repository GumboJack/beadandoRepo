<%--
  Created by IntelliJ IDEA.
  User: lmarc
  Date: 2020. 12. 09.
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <title>Cart</title>
</head>
<body onload="initialize();">
<jsp:include page="../navbar.jsp" />


<section class="pt-5 pb-5">
    <div class="container">
        <div class="row w-100">
            <div class="col-lg-12 col-md-12 col-12">
                <h3 class="display-5 mb-2 text-center">Shopping Cart</h3>
                <p class="mb-5 text-center">
                    <i class="text-info font-weight-bold" id="q"></i> items in your cart</p>
                <table id="shoppingCart" class="table table-condensed table-responsive">
                    <thead>
                    <tr>
                        <th style="width:60%">Product</th>
                        <th style="width:12%">Price</th>
                        <th style="width:10%">Quantity</th>
                        <th style="width:16%"></th>
                    </tr>
                    </thead>
                    <tbody id="tb">

                    </tbody>
                </table>
                <div class="float-right text-right">
                    <h4>Subtotal:</h4>
                    <h1 id="total"></h1>
                </div>
            </div>
        </div>
        <div class="row mt-4 d-flex align-items-center">
            <div class="col-sm-6 order-md-2 text-right">
                <a class="btn btn-primary mb-4 btn-lg pl-5 pr-5" id="btn_checkout">Checkout</a>
            </div>
            <div class="col-sm-6 mb-3 mb-m-1 order-md-1 text-md-left">
                <a href="/items">
                    <i class="fas fa-arrow-left mr-2"></i> Continue Shopping</a>
            </div>
        </div>
    </div>
</section>

<script>
    var cart = $.parseJSON(Cookies.get('cart'));
    console.log(cart);

    function deleteItem(index) {
        cart.splice(index, 1);
        console.log(cart);
        Cookies.remove('cart');
        Cookies.set("cart", JSON.stringify(cart), { path: '/', expires: 1});
        location.reload();
    };

    function getTotalPrice(){
        var total = 0;
        $.each(cart, function (index, value) {
            total = total + value.price;
        });
        return total;
    };

    function getTotalQuantity() {
        var total = 0;
        $.each(cart, function (index, value) {
            total = total + value.quantity;
        });
        return total;
    }

    function initialize() {
        if (typeof cart === "undefined"){
            cart = [];
        }
        $('#q').text(getTotalQuantity());
        $('#total').text("$" + getTotalPrice());

        $.each(cart, function (index, value) {
            $('#tb').append('<tr>\n' +
                '                        <td data-th="Product">\n' +
                '                            <div class="row">\n' +
                '                                <div class="col-md-3 text-left">\n' +
                '                                    <img src="https://via.placeholder.com/250x250/5fa9f8/ffffff" alt="" class="img-fluid d-none d-md-block rounded mb-2 shadow ">\n' +
                '                                </div>\n' +
                '                                <div class="col-md-9 text-left mt-sm-2">\n' +
                '                                    <h4>' + value.name + '</h4>\n' +
                '                                    <p class="font-weight-light">'+ value.manufacturer + '</p>\n' +
                '                                </div>\n' +
                '                            </div>\n' +
                '                        </td>\n' +
                '                        <td data-th="Price">$' + value.quantity * value.price + '</td>\n' +
                '                        <td data-th="Quantity">\n' + value.quantity +
                '                        </td>\n' +
                '                        <td class="actions" data-th="">\n' +
                '                            <div class="text-right">\n' +
                '                                <button class="btn btn-white border-secondary bg-white btn-md mb-2" onclick="deleteItem(' + index + ');">\n' +
                '                                    <i class="fas fa-trash"></i>\n' +
                '                                </button>\n' +
                '                            </div>\n' +
                '                        </td>\n' +
                '                    </tr>');
        });
        
        $('#btn_checkout').off('click').on('click', function () {
            if (cart.length === 0) {
                showErrorModal('Your cart is empty!');
            } else {
                $.ajax({
                    method: 'POST',
                    url: 'http://localhost:8081/rest/checkout',
                    contentType: 'application/json;charset=UTF-8',
                    async: true,
                    success: function (data) {
                        console.log(data);

                        if (data.error === '0') {
                            cart = [];
                            Cookies.remove('cart');
                            Cookies.set("cart", JSON.stringify(cart), { path: '/', expires: 1});
                            showSuccessModal("Invoice created!");
                        } else {
                            showErrorModal(data.msg);
                        }
                    },
                    error: function (xhr) {
                        console.log(xhr.responseText);
                        showErrorModal("Unknown error!")
                    }
                });
            }
        });
    }
</script>

</body>
</html>
