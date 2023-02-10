
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="loadStaffAPI" value="/api/customer"/>
<c:url var="customerListURL" value="/admin/customer-list"/>
<html>
<head>
    <title>Danh sách khách hàng</title>
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
                <li class="active">Danh sách khách hàng</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

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
                                <form:form commandName="modelSearch" action="${customerListURL}" id="listForm"
                                           method="GET">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div>
                                                <label for="fullName">Tên khách hàng</label>
                                                <form:input path="fullName" cssClass="form-control"/>
                                            </div>

                                        </div>
                                        <div class="col-sm-4">
                                            <div>
                                                <label for="phone">Di động</label>
                                                <form:input path="phone" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div>
                                                <label for="email">email</label>
                                                <form:input path="email" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">

                                        <security:authorize access="hasRole('MANAGER')">
                                            <div class="col-sm-2">
                                                <div>

                                                    <label for="staffId">Chọn nhân viên phụ trách</label>

                                                    <form:select path="staffId">
                                                        <form:option value="" label="--- Chọn nhân viên --- "/>
                                                        <form:options items="${staffmaps}"/>
                                                    </form:select>

                                                </div>
                                            </div>
                                        </security:authorize>

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

                        <a class="btn btn-white btn-info btn-bold" data-toggle="tooltip" title="Thêm khách hàng" href="/admin/customer-add">
                            <i class="fa fa-plus-circle" aria-hidden="true"></i>
                        </a>

                    <button class="btn btn-white btn-info btn-bold" data-toggle="tooltip" title="Xóa khách hàng"
                            id="btnDeleteCustomer">
                        <i class="fa fa-trash" aria-hidden="true"></i>
                    </button>


                </div>
            </div><!-- /.row -->
            </br>
            <div class="row">
                <div class="col-xs-12">
                    <table id="customerList" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Tên </th>
                            <th>Nhân viên quản lý</th>
                            <th>Di động</th>
                            <th>Email</th>
                            <th>Nhu cầu</th>
                            <th>Người nhập</th>
                            <th>Ngày nhập</th>
                            <th>Tình trạng</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="item" items="${customers}">
                            <tr>
                                <td class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" class="ace" value="${item.id}" id="checkbox_2"/>
                                        <span class="lbl"></span>
                                    </label>
                                </td>
                                <td>${item.fullName}</td>
                                <td>${item.staffName}</td>
                                <td>${item.phone}</td>
                                <td>${item.email}</td>
                                <td>${item.demand}</td>
                                <td>${item.createdBy}</td>
                                <td>${item.createdDate}</td>
                                <td></td>
                                <td class="center">
                                    <button class="btn btn-sx btn-info" data-toggle="tooltip" title="">
                                        <i class="fa fa-eye" aria-hidden="true"></i>
                                    </button>
                                    <button class="btn btn-sx btn-info" data-toggle="tooltip" title="Giao khách hàng"
                                            onclick="assignmentCustomer(${item.id})">
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                    </button>

                                        <a class="btn btn-sx btn-info" data-toggle="tooltip" title="Chỉnh sửa thông tin" href="/admin/customer-edit-${item.id}">
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

<div id="assignmentCustomerModal" class="modal fade" role="dialog">
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
                <input type="hidden" id="customerId" name="customerId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignCustomer">Giao khách hàng</button>
                <button type="button" class="btn btn-default" id="btnClose" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>

<script>
    function assignmentCustomer(customerId) {
        openModalAssignmentCustomer();

        $('#customerId').val(customerId);

        loadStaff(customerId);
        console.log($('#customerId').val());
    }

    function loadStaff(customerId) {
        $.ajax({
            type: "GET",
            url: '${loadStaffAPI}/'+customerId+'/staffs',
            //data: JSON.stringify(customerId),
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

    function openModalAssignmentCustomer() {
        $('#assignmentCustomerModal').modal();
    }

    $('#btnAssignCustomer').click(function (e) {
        e.preventDefault();
        //call api
        var data = {};

        data['customerId'] = $('#customerId').val();
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

    $('#btnDeleteCustomer').click(function (e) {
        e.preventDefault();
        var data = {};
        var customerIds = $('#customerList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['customerIds'] = customerIds;
        deleteCustomer(data);
    });

    function deleteCustomer(data) {
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
        window.location.reload();
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
