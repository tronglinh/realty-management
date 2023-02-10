
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customer"/>
<c:url var="customerEditURL" value="/admin/customer-edit"/>

<html>
<head>
    <title>chỉnh sửa người dùng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Chỉnh sửa người dùng</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Thông tin khách hàng
                </h1>
            </div><!-- /.page-header -->
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <form:form class="form-horizontal" role="form" commandName="customerModel"
                               action="${customerEditURL}" id="formEdit"
                               method="GET">
                        <!--<form class="form-horizontal" role="form" id="formEdit">-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="fullName"> Tên đầy đủ </label>

                            <div class="col-sm-9">
                                <input type="text" id="fullName" class="form-control" name="fullName"
                                       value="${customerModel.fullName}"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="phone"> Số điện thoại </label>

                            <div class="col-sm-9">
                                <input type="text" id="phone" class="form-control" name="phone"
                                       value="${customerModel.phone}"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="email"> Email </label>

                            <div class="col-sm-9">
                                <input type="text" id="email" class="form-control" name="email"
                                       value="${customerModel.email}"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="company"> Tên công ty </label>

                            <div class="col-sm-9">
                                <input type="text" id="company" class="form-control" name="company"
                                       value="${customerModel.company}"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="demand"> Nhu cầu </label>

                            <div class="col-sm-9">
                                <input type="text" id="demand" class="form-control" name="name"
                                       value="${customerModel.demand}"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="note"> Ghi chú </label>

                            <div class="col-sm-9">
                                <input type="text" id="note" class="form-control" name="note"
                                       value="${customerModel.note}"/>
                            </div>

                        </div>


                        <div class="space-4"></div>

                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">

                                <button class="btn btn-info" type="button" id="btnAddCustomer">
                                    <input type="hidden" id="customerId" name="customerId" value="${customerModel.id}">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    Cập nhật khách hàng
                                </button>
                            </div>
                        </div>
                        <c:forEach var="items" items="${transactions}">
                            <label class="control-label no-padding-right" > ${items.code} </label>
                            <input type="hidden" id="code" name="code" value="">
                            <button class="btn btn-white btn-info btn-bold" data-toggle="tooltip" type="button" onclick="addNotes('${items.code}');">
                                <i class="fa fa-plus-circle" aria-hidden="true"></i>
                            </button>
                            <table id="buildingList" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <div class="form-group">
                                        <th class="col-sm-2">Ngày tạo</th>
                                        <th class="col-sm-10">Ghi chú</th>
                                    </div>

                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach var="item" items="${items.transactionDetailDTOList}">
                                    <tr>
                                        <td>${item.createdDate}</td>
                                        <td>
                                            <p>${item.note}</p>
                                        </td>
                                    </tr>


                                </c:forEach>
                                <tr>
                                    <td>thêm ghi chú</td>
                                    <td>

                                        <input id= "addedNote" name="addedNote" type="text" class="form-control">
                                    </td>
                                </tr>

                                </tbody>

                            </table>

                            </br>
                        </c:forEach>

                    </form:form>
                </div>


            </div>

        </div><!-- /.row -->
        </br>

    </div><!-- /.page-content -->

</div><!-- /.main-content -->

<script>
    $('#btnAddCustomer').click(function (e) {
        e.preventDefault();

        var data = {};
        var formData = $('#formEdit').serializeArray();
        $.each(formData, function (index, v) {

            data["" + v.name + ""] = v.value;


        });
        var s = $('#customerId').val();

        data["id"] = s;

        $.ajax({
            type: 'PUT',
            url: '${customerAPI}',
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log('success');
            },
            error: function (response) {
                console.log('failed');
                console.log(response);
            }
        });

    });

    function addNotes(code) {
        //e.preventDefault();
        var data = {};
        $('#code').val(code);

        //data["note"] = $('#addedNote').val();
        var note = document.querySelectorAll("#addedNote");
        if(code == "Quá trình cskh"){
            data["note"] = note[0].value;
        }else  if(code == "Dẫn đi xem"){
            data["note"] = note[1].value;
        }

        data["code"] =  $('#code').val();

        data["customerId"] = $('#customerId').val();
        data["id"] = $('#customerId').val();
        $.ajax({
            type: 'POST',
            url: "${customerAPI}/transaction",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log('success');
            },
            error: function (response) {
                console.log('failed');
                console.log(response);
            }
        });
        location.reload();

    }

</script>
</body>
</html>
