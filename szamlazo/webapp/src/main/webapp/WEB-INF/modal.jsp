<%--
  Created by IntelliJ IDEA.
  User: lmarc
  Date: 2020. 12. 01.
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p id="modal_message"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="btn_modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    function showErrorModal(message){
        $('#exampleModalLabel').text("Error");
        $('#modal_message').text(message);
        $('#exampleModal').modal('show');
    }

    function showSuccessModal(message) {
        $('#exampleModalLabel').text("Success");
        $('#modal_message').text(message);
        $('#exampleModal').modal('show');
        $('#btn_modal').off('click').on('click', function () {
            window.location.replace("http://localhost:8081/")
        });
    }

    function showItemAddedModal(itemname){
        $('#exampleModalLabel').text("Cart");
        $('#modal_message').text("Item added to your cart: " + itemname);
        $('#exampleModal').modal('show');
    }
</script>

</body>
</html>
