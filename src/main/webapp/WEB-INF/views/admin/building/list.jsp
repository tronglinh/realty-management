
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="loadStaffAPI" value="/api/building"/>
<c:url var="buildingListURL" value="/admin/building-list"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
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
                <li class="active">Danh sách tòa nhà</li>
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
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm Kiếm</h4>
                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form commandName="modelSearch" action="${buildingListURL}" id="listForm"
                                           method="GET">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div>
                                                <label for="name">Tên tòa nhà</label>

                                                    <%--<input type="text" class="form-control" id="name" name="name" value="${modelSearch.name}"></input>--%>
                                                <form:input path="name" cssClass="form-control"/>
                                            </div>

                                        </div>
                                        <div class="col-sm-6">
                                            <div>
                                                <label for="floorArea">Diện tích sàn</label>
                                                <input type="number" class="form-control" id="floorArea"
                                                       name="floorArea" value="${modelSearch.floorArea}"></input>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <br>
                                        <div class="col-sm-2">
                                            <div>
                                                <label for="district">Quận hiện có</label>

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
                                        <div class="col-sm-5">
                                            <div>
                                                <label for="ward">Phường</label>
                                                <form:input path="ward" cssClass="form-control"/>
                                                    <%--<input type="text" class="form-control" id="ward"></--input>--%>
                                            </div>

                                        </div>
                                        <div class="col-sm-5">
                                            <div>
                                                <label for="street">Đường</label>
                                                <form:input path="street" cssClass="form-control"/>
                                                    <%--<input type="text" class="form-control" id="street"></input>--%>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div>
                                                <label for="numberOfBasement">Số tầng hầm</label>

                                                <input type="number" class="form-control" id="numberOfBasement"
                                                       name="numberOfBasement" value="${modelSearch.numberOfBasement}"></input>
                                            </div>

                                        </div>
                                        <div class="col-sm-4">
                                            <div>
                                                <label for="direction"> Hướng</label>

                                                <form:input path="direction" cssClass="form-control"/>
                                            </div>

                                        </div>
                                        <div class="col-sm-4">
                                            <div>
                                                <label for="level">Hạng</label>

                                                <form:input path="level" cssClass="form-control"/>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <div>
                                                <label for="rentAreaFrom">Diện tích từ</label>

                                                <input type="number" class="form-control" id="rentAreaFrom"
                                                       name="rentAreaFrom" value="${modelSearch.rentAreaFrom}"></input>
                                            </div>

                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label for="rentAreaTo">Diện tích đến</label>

                                                <input type="number" class="form-control" id="rentAreaTo"
                                                       name="rentAreaTo" value="${modelSearch.rentAreaTo}"></input>
                                            </div>

                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label for="rentCostFrom">Giá thuê từ</label>

                                                <input type="number" class="form-control" id="rentCostFrom"
                                                       name="rentCostFrom" value="${modelSearch.rentCostFrom}"></input>
                                            </div>

                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label for="rentCostTo">Giá thuê đến</label>

                                                <input type="number" class="form-control" id="rentCostTo"
                                                       name="rentCostTo" value="${modelSearch.rentCostTo}"></input>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <div>
                                                <label for="managerName">Tên quản lý</label>

                                                <form:input path="managerName" cssClass="form-control"/>
                                            </div>

                                        </div>
                                        <div class="col-sm-5">
                                            <div>
                                                <label for="managerPhone">Điện thoại quản lý</label>

                                                <form:input path="managerPhone" cssClass="form-control"/>
                                            </div>

                                        </div>
                                        <security:authorize access="hasRole('MANAGER')">
                                            <div class="col-sm-2">
                                                <div>

                                                    <label for="staffId">Chọn nhân viên phụ trách</label>

                                                        <%--<select class="form-control" id="staffId">
                                                            <option value="">---Chọn nhân viên phụ trách--</option>
                                                            <option value="nguyenVanA">Nguyễn Văn A</option>
                                                            <option value="nguyenVanB">Nguyễn Văn B</option>
                                                            <option value="nguyenVanC">Nguyễn Văn C</option>
                                                        </select>--%>
                                                    <form:select path="staffId">
                                                        <form:option value="" label="--- Chọn nhân viên --- "/>
                                                        <form:options items="${staffmaps}"/>
                                                    </form:select>

                                                </div>
                                            </div>
                                        </security:authorize>

                                    </div>
                                    <div class="row">
                                        <div class="col-sm-9">
                                            <form:checkboxes path="buildingTypes" items="${typemaps}"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <button type="button" class="btn btn-primary" id="btnSearch">Tìm kiếm
                                            </button>

                                        </div>

                                    </div>
                                </form:form>

                            </div>
                        </div>
                    </div>
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->


                <div class="pull-right">

                        <a class="btn btn-white btn-info btn-bold" data-toggle="tooltip" title="Thêm tòa nhà" href="/admin/building-edit">
                            <i class="fa fa-plus-circle" aria-hidden="true"></i>
                        </a>

                    <button class="btn btn-white btn-info btn-bold" data-toggle="tooltip" title="Xóa tòa nhà"
                            id="btnDeleteBuilding">
                        <i class="fa fa-trash" aria-hidden="true"></i>
                    </button>


                </div>
            </div><!-- /.row -->
            </br>
            <div class="row">
                <div class="col-xs-12">
                    <table id="buildingList" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Tên sản phẩm</th>
                            <th>Số tầng hầm</th>
                            <th>Địa chỉ</th>
                            <th>Tên quản lý</th>
                            <th>Số điện thoại</th>
                            <th>Diện tích sàn</th>
                            <th>Giá thuê</th>
                            <th>Phí dịch vụ</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="item" items="${buildings}">
                            <tr>
                                <td class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" class="ace" value="${item.id}" id="checkbox_2"/>
                                        <span class="lbl"></span>
                                    </label>
                                </td>
                                <td>${item.name}</td>
                                <td>${item.numberOfBasement}</td>
                                <td>${item.address}</td>
                                <td>${item.managerName}</td>
                                <td>${item.managerPhone}</td>
                                <td>${item.floorArea}</td>
                                <td>${item.rentPrice}</td>
                                <td>${item.serviceFee}</td>
                                <td class="center">
                                    <button class="btn btn-sx btn-info" data-toggle="tooltip" title="">
                                        <i class="fa fa-eye" aria-hidden="true"></i>
                                    </button>
                                    <button class="btn btn-sx btn-info" data-toggle="tooltip" title="Giao tòa nhà"
                                            onclick="assignmentBuiding(${item.id})">
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                    </button>

                                        <a class="btn btn-sx btn-info" data-toggle="tooltip" title="Chỉnh sửa thông tin" href="/admin/building-edit-${item.id}">
                                            <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                        </a>

                                    <button class="btn btn-sx btn-info" data-toggle="tooltip" title="">
                                        <i class="fa fa-plus-square" aria-hidden="true"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<div id="assignmentBuidingModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Modal Header</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="staffList">
                    <thead>
                    <tr>
                        <th>Chọn nhân viên</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--<tr>
                        <td><input type="checkbox" value="2" id="checkbox_1"></td>
                        <td>Nguyễn Văn B</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" value="3" id="checkbox_3"></td>
                        <td>Nguyễn Văn C</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" value="4" id="checkbox_4"></td>
                        <td>Nguyễn Văn D</td>
                    </tr>--%>
                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" id="btnClose" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>

<script>

    function assignmentBuiding(buildingId) {
        openModalAssignmentBuilding();

        $('#buildingId').val(buildingId);

        loadStaff(buildingId);
        console.log($('#buildingId').val());
    }

    function loadStaff(buildingId) {
        $.ajax({
            type: "GET",
            //url: "${loadStaffAPI}/'+buildingId+'/staffs",
            url: '${loadStaffAPI}/'+buildingId+'/staffs',
            //data: JSON.stringify(buildingId),
            dataType: "json",
            //contentType: "application/json",
            success: function (response) {
                console.log('success');
                var row = '';
                $.each(response.data, function(index, item) {
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value=' + item.id + ' id="checkbox_' + item.id + '" class="check-box-element"' + item.checked + '/></td>';
                    row += '<td class="text-center">' + item.fullName + '</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
            },
            error: function (response) {
                console.log('failed');
                console.log(response);
            }
        });
    }

    function openModalAssignmentBuilding() {
        $('#assignmentBuidingModal').modal();
    }

    $('#btnAssignBuilding').click(function (e) {
        e.preventDefault();
        //call api
        var data = {};

        data['buildingId'] = $('#buildingId').val();
        //$('#staffList').find('tbody input[type=checkbox]')
        var staffIds = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffIds'] = staffIds;
        assignStaff(data);
    });

    function assignStaff(data) {
        $.ajax({
            type: "POST",
            url: "${loadStaffAPI}/assignment",
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
    }

    $('#btnDeleteBuilding').click(function (e) {
        e.preventDefault();
        var data = {};
        var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['buildingIds'] = buildingIds;
        deleteBuilding(data);
        //window.location.reload();
    });

    function deleteBuilding(data) {
        $.ajax({
            type: "DELETE",
            url: "${loadStaffAPI}",
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

    }

    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    });

    $('#btnClose').click(function (e) {
        e.preventDefault();
        window.location.reload();
    });
</script>
</body>
</html>
