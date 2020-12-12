<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lmarc
  Date: 2020. 11. 29.
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta charset="utf-8" />
</head>
<body>
<jsp:include page="../navbar.jsp" />
<jsp:include page="../modal.jsp" />

    <div class="container">
        <th:form action="/register" modelAttribute="customer" method="post">
            <div class="form-group">
                <label for="email">Email address*</label>
                <th:input type="email" class="form-control" path="email" />
            </div>

            <div class="form-group">
                <label for="email_confirm">Email address confirm*</label>
                <input type="email" class="form-control" id="email_confirm" aria-describedby="emailConfirmHelp">
                <small id="emailConfirmHelp" class="form-text text-muted">Please enter your e-mail address again!</small>
            </div>

            <div class="form-group">
                <label for="password">Password*</label>
                <th:input type="password" class="form-control" id="password" path="password" />
            </div>

            <div class="form-group">
                <label for="password_confirm">Password Confirm*</label>
                <input type="password" class="form-control" id="password_confirm" aria-describedby="passwordConfirmHelp">
                <small id="passwordConfirmHelp" class="form-text text-muted">Please enter your password address again!</small>
            </div>

            <div class="form-group form-check">
                <th:checkbox class="form-check-company-input" id="isCompany" path="company" />
                <label class="form-check-label" for="isCompany">Register as an Organization</label>
            </div>
            <div class="form-group">
                <label for="name">Name*</label>
                <th:input type="text" class="form-control" id="name" aria-describedby="nameHelp" path="name"/>
                <small id="nameHelp" class="form-text text-muted">Your full name or the name of the organization.</small>
            </div>
            <div class="form-group">
                <label for="taxId">TAX ID</label>
                <th:input type="text" class="form-control" id="taxId" aria-describedby="taxHelp" path="taxID" />
                <small id="taxHelp" class="form-text text-muted">Requires for organization!</small>
            </div>
            <div class="form-group">
                <label for="phone">Phone number</label>
                <th:input type="text" class="form-control" id="phone" path="phoneNumber" />
            </div>
            <h2>Billing address</h2>
            <div class="from-group">
                <select class="selectpicker" data-live-search="true" id="invoice_address_country" disabled="true"></select>
            </div>
            <div class="form-group">
                <label for="invoice_address_city">City*</label>
                <th:input type="text" class="form-control" id="invoice_address_city" path="invoicing_address_city" />
            </div>
            <div class="form-group">
                <label for="invoice_address_postcode">Postcode*</label>
                <th:input type="text" class="form-control" id="invoice_address_postcode" path="invoicing_address_postcode" />
            </div>
            <div class="form-group">
                <label for="invoice_address_street">Address*</label>
                <th:input type="text" class="form-control" id="invoice_address_street" path="invoicing_address_street" />
            </div>
            <div class="form-group">
                <label for="invoice_address_other">Address 2</label>
                <th:input type="text" class="form-control" id="invoice_address_other" aria-describedby="otherHelp" path="invoicing_address_other" />
                <small id="otherHelp" class="form-text text-muted">Apt, Suite, Unit, etc. (Optional)</small>
            </div>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-company-input" id="invoiceShippingSame">
                <label class="form-check-label" for="invoiceShippingSame">Billing and shipping address is the same</label>
            </div>
            <h2>Shipping address</h2>
            <div class="from-group">
                <select class="selectpicker" data-live-search="true" id="shipping_address_country" disabled="true"></select>
            </div>
            <div class="form-group">
                <label for="shipping_address_city">City*</label>
                <th:input type="text" class="form-control" id="shipping_address_city" path="shipping_address_city" />
            </div>
            <div class="form-group">
                <label for="shipping_address_postcode">Postcode*</label>
                <th:input type="text" class="form-control" id="shipping_address_postcode" path="shipping_address_postcode" />
            </div>
            <div class="form-group">
                <label for="shipping_address_street">Address*</label>
                <th:input type="text" class="form-control" id="shipping_address_street" path="shipping_address_street" />
            </div>
            <div class="form-group">
                <label for="shipping_address_other">Address 2</label>
                <th:input type="text" class="form-control" id="shipping_address_other" aria-describedby="otherHelp2" path="shipping_address_other" />
                <small id="otherHelp2" class="form-text text-muted">Apt, Suite, Unit, etc. (Optional)</small>
            </div>
            <th:button type="submit" class="btn btn-primary" id="btn_submit" disabled="true"></th:button>
            <button type="button" class="btn btn-primary" id="btnSubmit">Submit</button>
        </th:form>
    </div>

<script>
    $(document).ready(function(){

        $.getJSON( "countries.json", function( data ) {
            $.each( data, function( key, val ) {
                $('#invoice_address_country').append('<option value="' + key + '">' + val + '</option>');
                $('#shipping_address_country').append('<option value="' + key + '">' + val + '</option>');
            });
            $('#invoice_address_country').selectpicker('refresh');
            $('#shipping_address_country').selectpicker('refresh');
        });


        $('#invoiceShippingSame').click(function(){
            if($(this).prop("checked") == true){
                $('#shipping_address_city').val($('#invoice_address_city').val());
                $('#shipping_address_postcode').val($('#invoice_address_postcode').val());
                $('#shipping_address_street').val($('#invoice_address_street').val());
                $('#shipping_address_other').val($('#invoice_address_other').val());
                $('#shipping_address_country').val($('#invoice_address_country').val());
            }
            else if($(this).prop("checked") == false){
                $('#shipping_address_city').val("").prop('disabled', false);
                $('#shipping_address_postcode').val("").prop('disabled', false);
                $('#shipping_address_street').val("").prop('disabled', false);
                $('#shipping_address_other').val("").prop('disabled', false);
                $('#shipping_address_country').prop('disabled', false);
            }
        });

        $('#btnSubmit').off('click').on('click', function () {

            var email = $('#email').val();
            var email_confirm = $('#email_confirm').val();
            var password = $('#password').val();
            var password_confirm = $('#password_confirm').val();

            if (email !== email_confirm){
                showErrorModal("E-mail address is not confirmed!")
            } else if (password !== password_confirm) {
                showErrorModal("Password is not confirmed!")
            }else if (password.length < 8) {
                showErrorModal("Password must be at least 8 character long!");
            } else {
                var obj = {};

                obj.shipping_address_city = $('#shipping_address_city').val();
                obj.shipping_address_postcode = $('#shipping_address_postcode').val();
                obj.shipping_address_street = $('#shipping_address_street').val();
                obj.shipping_address_other = $('#shipping_address_other').val();
                obj.shipping_address_country = $('#shipping_address_country').val();
                obj.invoicing_address_city = $('#invoice_address_city').val();
                obj.invoicing_address_postcode = $('#invoice_address_postcode').val();
                obj.invoicing_address_street = $('#invoice_address_street').val();
                obj.invoicing_address_other = $('#invoice_address_other').val();
                obj.invoicing_address_country = $('#invoice_address_country').val();
                obj.phoneNumber = $('#phone').val();
                obj.taxID = $('#taxId').val();
                obj.name= $('#name').val();
                obj.company = $('#isCompany').is(":checked");
                obj.email = email;
                obj.password = password;

                console.log(obj);

                $.ajax({
                    method: 'POST',
                    url: 'http://localhost:8081/rest/register',
                    contentType: 'application/json;charset=UTF-8',
                    data: JSON.stringify(obj),
                    async: true,
                    success: function (data){
                        console.log(data);

                        if (data.error === '0'){
                            showSuccessModal(data.msg);
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

    })
</script>

</body>
</html>
