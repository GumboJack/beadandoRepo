<%--
  Created by IntelliJ IDEA.
  User: lmarc
  Date: 2020. 12. 11.
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body onload="initialize();">
<jsp:include page="../navbar.jsp" />
<jsp:include page="../modal.jsp" />

<div class="container">
    <div class="list-group" id="dataList">
    </div>
</div>
<div class="container" id="inv_container">
    <div class="row">
        <div class="col-xs-12">
            <div class="text-xs-center">
                <i class="fa fa-search-plus float-xs-left icon"></i>
                <h2 id="inv_label"></h2>
            </div>
            <hr>
            <div class="row">
                <div class="col-xs-12 col-md-3 col-lg-3 float-xs-left">
                    <div class="card  height">
                        <div class="card-header">Billing Details</div>
                        <div class="card-block">
                            <strong id="b_name"></strong><br>
                            <a id="b_street"></a><br>
                            <a id="b_other"></a> <br>
                            <a id="b_city"></a><br>
                            <strong id="b_postcode"></strong><br>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-3 col-lg-3">
                    <div class="card  height">
                        <div class="card-header">Customer Information</div>
                        <div class="card-block">
                            <strong>E-mail: </strong><a id="email"></a><br>
                            <strong>Phone number: </strong><a id="phone"></a><br>
                            <strong>TAX ID: </strong><a id="tax"></a><br>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-3 col-lg-3 float-xs-right">
                    <div class="card  height">
                        <div class="card-header">Shipping Address</div>
                        <div class="card-block">
                            <strong id="s_name"></strong><br>
                            <a id="s_street"></a><br>
                            <a id="s_other"></a> <br>
                            <a id="s_city"></a><br>
                            <strong id="s_postcode"></strong><br>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="card ">
                <div class="card-header">
                    <h3 class="text-xs-center"><strong>Order summary</strong></h3>
                </div>
                <div class="card-block">
                    <div class="table-responsive">
                        <table class="table table-sm" id="itemList">
                            <thead>
                            <tr>
                                <td><strong>Item Name</strong></td>
                                <td class="text-xs-center"><strong>Item Price</strong></td>
                                <td class="text-xs-center"><strong>Item Quantity</strong></td>
                                <td class="text-xs-right"><strong>Total</strong></td>
                            </tr>
                            </thead>

                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var invoices = [];
    var showing = false;

    $(document).ready(function() {
        $('#inv_container').hide();
        $.ajax({
            method: 'GET',
            url: 'http://localhost:8081/rest/invoices',
            contentType: 'application/json;charset=UTF-8',
            async: true,
            success: function (data) {

                if (data.error === '0') {
                    invoices = data.invoices;

                    $('#email').text(data.customerInfo.email);
                    $('#phone').text(data.customerInfo.phone);
                    $('#tax').text(data.customerInfo.tax);

                    $('#s_name').text(data.shipping.name);
                    $('#s_city').text(data.shipping.city);
                    $('#s_street').text(data.shipping.street);
                    $('#s_other').text(data.shipping.other);
                    $('#s_postcode').text(data.shipping.postcode);

                    $('#b_name').text(data.billing.name);
                    $('#b_city').text(data.billing.city);
                    $('#b_street').text(data.billing.street);
                    $('#b_other').text(data.billing.other);
                    $('#b_postcode').text(data.billing.postcode);


                    console.log(invoices);
                    $.each(invoices, function (index, value) {
                        $('#dataList')
                            .append('<a id="invoice' + index + '" class="list-group-item list-group-item-action">' + value.date + '</a>\n');
                        $('#invoice' + index).off('click').on('click', function () {
                            if(showing){
                                $("tbody").remove();
                            }
                            $('#inv_container').show();
                            $('#inv_label').text('Invoice for purchase #' + value.id);
                            $('#itemList').append('<tbody></tbody>');
                            var items = value.itemList;
                            $.each(items, function (index2, value2) {
                                $('tbody').append('<tr>\n' +
                                    '                                <td>' + value2.manufacturer + ' ' + value2.name + '</td>\n' +
                                    '                                <td class="text-xs-center">$' + value2.unitPrice + '</td>\n' +
                                    '                                <td class="text-xs-center">' + value2.quantity + '</td>\n' +
                                    '                                <td class="text-xs-right">$' + value2.price + '</td>\n' +
                                    '                            </tr>');
                            });
                            var shipping = value.shipping === 0 ? "FREE" : "$" + value.shipping; console.log(shipping);
                            $('tbody').append('<tr>\n' +
                                '                                <td class="highrow"></td>\n' +
                                '                                <td class="highrow"></td>\n' +
                                '                                <td class="highrow text-xs-center"><strong>Subtotal</strong></td>\n' +
                                '                                <td class="highrow text-xs-right">$' + value.totalPrice + '</td>\n' +
                                '                            </tr>\n' +
                                '                            <tr>\n' +
                                '                                <td class="emptyrow"></td>\n' +
                                '                                <td class="emptyrow"></td>\n' +
                                '                                <td class="emptyrow text-xs-center"><strong>Shipping</strong></td>\n' +
                                '                                <td class="emptyrow text-xs-right">' + shipping + '</td>\n' +
                                '                            </tr>\n' +
                                '                            <tr>\n' +
                                '                                <td class="emptyrow"><i class="fa fa-barcode iconbig"></i></td>\n' +
                                '                                <td class="emptyrow"></td>\n' +
                                '                                <td class="emptyrow text-xs-center"><strong>Purchase date</strong></td>\n' +
                                '                                <td class="emptyrow text-xs-right">' + value.date + '</td>\n' +
                                '                            </tr>');
                            showing = true;
                        });
                    });
                } else {
                    showErrorModal(data.msg);
                }
            },
            error: function (xhr) {
                console.log(xhr.responseText);
                showErrorModal("Unknown error!");
            }
        });
    });

    function initialize() {

    }

</script>

<style>
    .height {
        min-height: 200px;
    }

    .icon {
        font-size: 47px;
        color: #5CB85C;
    }

    .iconbig {
        font-size: 77px;
        color: #5CB85C;
    }

    .table > tbody > tr > .emptyrow {
        border-top: none;
    }

    .table > thead > tr > .emptyrow {
        border-bottom: none;
    }

    .table > tbody > tr > .highrow {
        border-top: 3px solid;
    }
</style>

</body>
</html>
