
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customer"/>
<c:url var="customerEditURL" value="/admin/customer-add"/>

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

                                <button class="btn btn-info" type="button" id="btnAddBuilding">
                                    <input type="hidden" id="buildingId" name="buildingId" value="${customerModel.id}">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    Cập nhật khách hàng
                                </button>
                            </div>
                        </div>


                        <!--</form> -->
                    </form:form>
                </div>


            </div><!-- /.col -->
        </div><!-- /.row -->
        </br>

    </div><!-- /.page-content -->
</div>
</div><!-- /.main-content -->

<script>
    $('#btnAddBuilding').click(function (e) {
        e.preventDefault();

        var data = {};
        var formData = $('#formEdit').serializeArray();
        $.each(formData, function (index, v) {

            data["" + v.name + ""] = v.value;


        });

        $.ajax({
            type: 'POST',
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
</script>
</body>
</html>
