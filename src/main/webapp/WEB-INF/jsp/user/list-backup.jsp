<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>USER LIST</title>
<%@ include file="../include.jsp"%>
<script src="//raw.github.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>
<style>
.table th,.table td {
	text-align: center;
}

.table thead {
	font-weight: bold;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
    $(':checkbox.stc').each(function() {
        if ($(this).val() == '1') $(this).bootstrapToggle('on');
    });
    
    $('#all').click(function() {
    	var state = $('#all').is(':checked');
   		$(':checkbox.idc').each(function() {
       		$(this).prop('checked',state);
       	});
    });
});

function selectRow(element) {
	var checkbox = $(element).children('td').eq(0).find('input');
	$(checkbox).prop('checked',!$(checkbox).is(':checked'));
	checkAll();
}

function checkAll() {
	var state = true;
	$(':checkbox.idc').each(function(){
		if(!$(this).is(':checked')) state = false;
	});
	$('#all').prop("checked",state);
}

$(document).on('click.bs.toggle', 'div.testtoggle',
function(e) {
    e.stopImmediatePropagation();
    var $checkbox = $(this).find(':checkbox.stc');
    BootstrapDialog.show({
        title: '提示信息',
        message: '您确定要改变该用户的可用状态吗？',
        buttons: [{
            label: '确认',
            cssClass: 'btn-primary',
            autospin: true,
            hotkey: 32,
            action: function(dialog) {
                $.ajax({
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    type: 'POST',
                    url: 'updateStatus',
                    data: "{\"id\":" + $checkbox.attr('id') + ",\"status\":" + $checkbox.attr('value') + "}",
                    dataType: 'json',
                    success: function(data) {
                        $checkbox.attr('value', data.status);
                        dialog.setMessage('更新用户可用状态成功!');
                        dialog.enableButtons(false);
                        setTimeout(function() {
                            dialog.close();
                        },
                        1000);
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        //XMLHttpRequest, textStatus, errorThrown
                        $checkbox.bootstrapToggle('toggle');
                        dialog.setMessage(XMLHttpRequest.responseText);
                        dialog.enableButtons(false);
                        setTimeout(function() {
                            dialog.close();
                        },
                        5000);
                    }
                });
            }
        },
        {
            label: '取消',
            action: function(dialogItself) {
                $checkbox.bootstrapToggle('toggle');
                dialogItself.close();
            }
        }]
    });
});
</script>
</head>
<body class="container">
	<h1>LIST</h1>
	<hr>
	<c:set var="userList" value="${userList}" />
	<div class="table-responsive">
		<div class="btn-group col-md-4">
			<button type="button" class="btn btn-default btn-sm">
			<span class="glyphicon glyphicon-check"></span>
			反选</button>
			<button type="button" class="btn btn-default btn-sm">
			<span class="glyphicon glyphicon-plus"></span>
			添加</button>
			<button type="button" class="btn btn-default btn-sm">
			<span class="glyphicon glyphicon-edit"></span>
			修改</button>
			<button type="button" class="btn btn-default btn-sm">
			<span class="glyphicon glyphicon-remove"></span>
			删除</button>
		</div>
		
		<div class="form-group has-feedback col-md-offset-10" id="searchInput">
			<input type="text" placeholder="过滤" class="form-control"></input>
			<span class="glyphicon glyphicon-search form-control-feedback"></span>
		</div>
		<table id="dataTable" class="table table-bordered table-hover">
			<thead>
  				<tr>
  					<th><input type="checkbox" id="all"></th>
  					<th>编号</th>
  					<th>用户名</th>
  					<th>密码</th>
  					<th>性别</th>
  					<th>地址</th>
  					<th>状态</th>
  				</tr>
  			</thead>
  			<tbody>
  				<c:choose>
  					<c:when test="${not empty userList}">
  						<c:forEach items="${userList}" var="user" varStatus="status">
  							<tr onclick="selectRow(this)">
  								<td><input type="checkbox" class="idc" id="${user.id }"></td>
	  							<td>${user.id }</td>
	  							<td>${user.username }</td>
	  							<td>${user.password }</td>
	  							<td>
	  							<c:choose>
	  								<c:when test="${user.sex}">男</c:when>
	  								<c:otherwise>女</c:otherwise>
	  							</c:choose>
	  							</td>
	  							<td>${user.address }</td>
	  							<td>
	  							<input type="checkbox" id="${user.id }" value="${user.status }" class="stc"
	  								data-toggle="toggle" data-on="可用" data-off="不可用" data-size="mini" data-style="testtoggle">
  							</tr>
  						</c:forEach>
  					</c:when>
  					<c:otherwise>
  						<tr><td colspan="5">未查询到用户记录...</td></tr>
  					</c:otherwise>
  				</c:choose>
  				<tr></tr>
  			</tbody>
		</table>
		<ul class="pagination">
			<li class="disabled"><a href="#">&laquo;</a></li>
			<li class="active"><a href="#">1<span class="sr-only"></span></a></li>
			<li ><a href="#">2<span class="sr-only"></span></a></li>
			<li ><a href="#">3<span class="sr-only"></span></a></li>
			<li ><a href="#">4<span class="sr-only"></span></a></li>
			<li class="disabled"><a href="#">&raquo;</a></li>
		</ul>
	</div>
</body>
</html>
