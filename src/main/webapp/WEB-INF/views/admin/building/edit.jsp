<%--
  Created by IntelliJ IDEA.
  User: CNL
  Date: 6/30/2021
  Time: 8:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<c:url var="buildingEditURL" value="/admin/building-edit"/>

<html>
<head>
    <title>Chỉnh sửa tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Chỉnh sửa tòa nhà</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Dashboard
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <form:form class="form-horizontal" role="form" commandName="buildingModel" action="${buildingEditURL}" id="formEdit"
                                                   method="GET">
                    <!--<form class="form-horizontal" role="form" id="formEdit">-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="name"> Tên sản phẩm </label>

                            <div class="col-sm-9">
                                <input type="text" id="name" class="form-control" name = "name" value="${buildingModel.name}"/>
                            </div>

                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="district"> Quận </label>

                            <div class="col-sm-9">
                                <%--<select class="form-control" id="district">
                                    <option value="">---Chọn quận--</option>
                                    <option value="Q1">Quận 1</option>
                                    <option value="Q2">Quận 2</option>
                                    <option value="Q3">Quận 3</option>
                                </select>--%>
                                    <form:select path="district">
                                        <form:option value="" label="--- Chọn quận --- "/>
                                        <form:options items="${districtmaps}"/>
                                    </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="ward"> Phường </label>

                            <div class="col-sm-9">
                                <input type="text" id="ward" class="form-control" name="ward" value="${buildingModel.ward}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="street"> Đường </label>

                            <div class="col-sm-9">
                                <input type="text" id="street" class="form-control" name="street" value="${buildingModel.street}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="structure"> Kết cấu </label>

                            <div class="col-sm-9">
                                <input type="text" id="structure" class="form-control" name="structure" value="${buildingModel.structure}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="numberOfBasement"> Số tầng hầm </label>

                            <div class="col-sm-9">
                                <input type="number" id="numberOfBasement" class="form-control" name="numberOfBasement" value="${buildingModel.numberOfBasement}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="floorArea"> Diện tích sàn </label>

                            <div class="col-sm-9">
                                <input type="text" id="floorArea" class="form-control" name="floorArea" value="${buildingModel.floorArea}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="direction"> Hướng </label>

                            <div class="col-sm-9">
                                <input type="text" id="direction" class="form-control" name="direction" value="${buildingModel.direction}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="level"> Hạng </label>

                            <div class="col-sm-9">
                                <input type="text" id="level" class="form-control"  name="level" value="${buildingModel.level}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="rentArea"> Diện tích thuê </label>

                            <div class="col-sm-9">
                                <input type="text" id="rentArea" class="form-control" name="rentArea" value="${buildingModel.rentArea}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="rentAreaDescription"> Mô tả diện tích </label>

                            <div class="col-sm-9">
                                <input type="text" id="rentAreaDescription" class="form-control" name="rentAreaDescription" value="" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="rentPrice"> Giá thuê </label>

                            <div class="col-sm-9">
                                <input type="text" id="rentPrice" class="form-control" name="rentPrice" value="${buildingModel.rentPrice}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="rentPriceDescription"> Mô tả giá </label>

                            <div class="col-sm-9">
                                <input type="text" id="rentPriceDescription" class="form-control" name="rentPriceDescription" value="${buildingModel.rentPriceDescription}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="serviceFee"> Phí  dịch vụ </label>

                            <div class="col-sm-9">
                                <input type="text" id="serviceFee" class="form-control" name="serviceFee" value="${buildingModel.serviceFee}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="carFee"> Phí ô tô </label>

                            <div class="col-sm-9">
                                <input type="text" id="carFee" class="form-control" name="carFee" value="${buildingModel.carFee}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="motoFee"> Phí mô tô </label>

                            <div class="col-sm-9">
                                <input type="text" id="motoFee" class="form-control" name="motoFee" value="${buildingModel.motoFee}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="overTimeFee"> Phí ngoài giờ </label>

                            <div class="col-sm-9">
                                <input type="text" id="overTimeFee" class="form-control" name="overTimeFee" value="${buildingModel.overTimeFee}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="electricityFee"> Tiền điện </label>

                            <div class="col-sm-9">
                                <input type="text" id="electricityFee" class="form-control" name="electricityFee" value="${buildingModel.electricityFee}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="deposit"> Đặt cọc </label>

                            <div class="col-sm-9">
                                <input type="text" id="deposit" class="form-control" name="deposit" value="${buildingModel.deposit}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="payment"> Thanh toán </label>

                            <div class="col-sm-9">
                                <input type="text" id="payment" class="form-control" name="payment" value="${buildingModel.payment}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="rentTime"> Thời hạn thuê </label>

                            <div class="col-sm-9">
                                <input type="text" id="rentTime" class="form-control" name="rentTime" value="${buildingModel.rentTime}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="decorationTime"> Thời gian trang trí </label>

                            <div class="col-sm-9">
                                <input type="text" id="decorationTime" class="form-control" name="decorationTime" value="${buildingModel.decorationTime}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="managerName"> Tên quản lý </label>

                            <div class="col-sm-9">
                                <input type="text" id="managerName" class="form-control" name="managerName" value="${buildingModel.managerName}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="managerPhone"> Sdt quản lý </label>

                            <div class="col-sm-9">
                                <input type="number" id="managerPhone" class="form-control" name="managerPhone" value="${buildingModel.managerPhone}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="brokerageFee"> Phí môi giới </label>

                            <div class="col-sm-9">
                                <input type="text" id="brokerageFee" class="form-control" name="brokerageFee" value="${buildingModel.brokerageFee}" />
                            </div>
                        </div>
                        <div class="form-group">

                            <label class="col-sm-3 control-label no-padding-right"> Loại sản phẩm </label>

                            <div class="col-sm-9">

                            <form:checkboxes items="${typemaps}" path="buildingTypes"/>


                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="note"> Ghi chú </label>

                            <div class="col-sm-9">
                                <input type="text" id="note" class="form-control" name="note" value="${buildingModel.note}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="linkOfBuilding"> Link sản phẩm </label>

                            <div class="col-sm-9">
                                <input type="text" id="linkOfBuilding" class="form-control" name="linkOfBuilding" value="${buildingModel.linkOfBuilding}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="map"> Bản đồ </label>

                            <div class="col-sm-9">
                                <input type="text" id="map" class="form-control" name="map" value="${buildingModel.map}" />
                            </div>
                        </div>

                        <div class="space-4"></div>

                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">

                                <button class="btn btn-info" type="button" id="btnAddBuilding" >
                                    <input type="hidden" id="buildingId" name="buildingId" value="${buildingModel.id}">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    Thêm sản phẩm
                                </button>

                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="button">
                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                    Hủy
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
    $('#btnAddBuilding').click(function(e){
        e.preventDefault();

        var data = {};
        var buildingTypes =[];
        var formData = $('#formEdit').serializeArray();
        $.each(formData, function(index, v){
            if(v.name == 'buildingTypes'){
                buildingTypes.push(v.value);
            }else{
                data[""+v.name+""] = v.value;
            }

        });

        data['buildingTypes'] = buildingTypes;
        var s =  $('#buildingId').val();
        if(s == null){
        $.ajax({
            type: 'POST',
            url: '${buildingAPI}',
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function(response) {
                console.log('success');
            },
            error: function(response){
                console.log('failed');
                console.log(response);
            }
        });
        }else{
            data["id"] = s;
            $.ajax({
                type: 'PUT',
                url: '${buildingAPI}',
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                success: function(response) {
                    console.log('success');
                },
                error: function(response){
                    console.log('failed');
                    console.log(response);
                }
            });
        }
    });
</script>
</body>
</html>
