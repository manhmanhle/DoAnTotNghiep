
<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <c:url var="config" value="/trang-chu/config">
	<c:param name="tenfile" value="test_mysql" />
</c:url> --%>
<div id="wrapper">
	<form id="formSubmit">

		<div id="content">

			<div style="position: relative;">
				<%-- <a href="${config}">
				<button type="button" class="btn btn-info"
					style="position: absolute;right: 0px">Cấu
					hình
				</button>
                 </a> --%>
				<h2>Thống kê cán bộ giảng viên</h2>

				<div id="form"
					style="display: flex; flex-direction: row; align-items: center; justify-content: space-around;">

					<div
						style="display: flex; flex-direction: row; align-items: center;">
						<label style="margin-right: 10px" for="">Tên Đơn Vị </label> <select
							name="iddv" id="iddv">
							<option value="0">Chọn đơn vị</option>
							<c:forEach var="item" items="${list.listResult}">
							  
								<option value="${item.iddv}">${item.tendv}</option>
							</c:forEach>

						</select>
					</div>
					<div
						style="display: flex; flex-direction: row; align-items: center;">
						<label style="margin-right: 10px" for="">Mã cán bộ: </label> 
						<input name="macb" style=" width: 100% ; height: 30px; padding-left: 10px;padding-right: 10px;border-width: 1px;border-color: #707070" id="macb" cssClass="col-xs-10 col-sm-5"/>
					</div>
					<div id="abc">

						<div>
							<button id="xembaocao">Xem Báo Cáo</button>
						</div>
					</div>
				</div>
			</div>

		</div>
		<input type="hidden" value="thong_ke_canbo_gv" id="tenfile" name="tenfile" />
		<input type="hidden" value="pdf" id="type" name="type" />
	</form>
	<div style="display: flex; flex-direction: column;">
		<div id="filePdf"
			style="height: 600px; width: 100%; background-color: #707070AA"></div>
		<div
			style="width: 100%; margin: 10px; display: flex; flex-direction: row; align-items: center; justify-content: center">
			<div style="display: flex; flex-direction: row; align-items: center;">
				<label style="margin-right: 30px"><input type="radio"
					id="Pdf" name="dowload" value="pdf"> Pdf</label> <label
					style="margin-right: 30px"><input type="radio" id="Excel"
					name="dowload" value="xlsx"> Excel</label> <label><input
					type="radio" id="Docx" name="dowload" value="docx"> Docx</label>
			</div>
		</div>
		<div
			style="width: 100%; margin: 10px; justify-content: center; align-items: center; display: flex; flex-direction: row">
			<button id="download">Tải xuống</button>
		</div>
	</div>

</div>
<script src="<c:url value='/template/web/download/FileSaver.min.js' />"></script>
<script type="text/javascript">
	$("#download").click(function(e) {
		e.preventDefault();
		var val = $("input[type='radio']:checked").val();
		if (val != null) {
			var data = {};
			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function(i, v) {
				data["" + v.name + ""] = v.value;
			});
			data["type"] = val;
			
			download(data);
		}
	});
	function download(data) {
		$
				.ajax({
					url : '/spring-mvc/api/report',
					type : 'POST',
					contentType : 'application/json',
					data : JSON.stringify(data),
					success : function(result) {
						if (data.type == 'pdf') {
							var dataURI = "data:application/pdf;base64,"
									+ result;
							var test = dataURItoBlob(dataURI);
							var filename = data.tenfile + ".pdf";
							var blob = new Blob([ test ], {
								type : "application/pdf"
							});
							saveAs(blob, filename);
						}
						if (data.type == 'xlsx') {
							var dataURI = "data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,"
									+ result;
							var test = dataURItoBlob(dataURI);
							var blob = new Blob(
									[ test ],
									{
										type : "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
									});
							var filename = data.tenfile + ".xlsx";
							saveAs(blob, filename);
						}
						if (data.type == 'docx') {
							var dataURI = "data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,"
									+ result;
							var test = dataURItoBlob(dataURI);
							var blob = new Blob(
									[ test ],
									{
										type : "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
									});
							var filename = data.tenfile + ".docx";
							saveAs(blob, filename);
						}

					},
					error : function(error) {
						//		    	window.location.href = "${userURL}?page=1&limit=2&message=error_system";
						alert("Error");
					}
				});
	}
	function dataURItoBlob(dataURI) {
		var byteString;
		if (dataURI.split(',')[0].indexOf('base64') >= 0)
			byteString = atob(dataURI.split(',')[1]);
		else
			byteString = unescape(dataURI.split(',')[1]);

		// separate out the mime component
		var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];

		// write the bytes of the string to a typed array
		var ia = new Uint8Array(byteString.length);
		for (var i = 0; i < byteString.length; i++) {
			ia[i] = byteString.charCodeAt(i);
		}
		return new Blob([ ia ], {
			type : mimeString
		});
	}

	$('#xembaocao').click(function(e) {
		e.preventDefault();
		var data = {};
		var formData = $('#formSubmit').serializeArray();
		$.each(formData, function(i, v) {
			data["" + v.name + ""] = v.value;
		});
		$("#filePdf").children().remove();
		show(data);
	});
	function show(data) {
		$.ajax({
			url : '/spring-mvc/api/report',
			type : 'POST',
			contentType : 'application/json',
			data : JSON.stringify(data),
			success : function(result) {
				//var x ='<iframe src="data:application/pdf;base64,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IDE1IDAgUj4+CmVuZG9iagoxNyAwIG9iago8PC9OYW1lcyAxNiAwIFIvVHlwZS9DYXRhbG9nL1BhZ2VzIDQgMCBSL1ZpZXdlclByZWZlcmVuY2VzPDwvUHJpbnRTY2FsaW5nL0FwcERlZmF1bHQ+Pj4+CmVuZG9iagoxOCAwIG9iago8PC9Nb2REYXRlKEQ6MjAyMTA0MTQyMDI5MDYrMDcnMDAnKS9DcmVhdG9yKEphc3BlclJlcG9ydHMgTGlicmFyeSB2ZXJzaW9uIDYuMi4xKS9DcmVhdGlvbkRhdGUoRDoyMDIxMDQxNDIwMjkwNiswNycwMCcpL1Byb2R1Y2VyKGlUZXh0IDIuMS43IGJ5IDFUM1hUKT4+CmVuZG9iagp4cmVmCjAgMTkKMDAwMDAwMDAwMCA2NTUzNSBmIAowMDAwMDAwMzE3IDAwMDAwIG4gCjAwMDAwMDIzNzcgMDAwMDAgbiAKMDAwMDAwMDAxNSAwMDAwMCBuIAowMDAwMDAyNDY1IDAwMDAwIG4gCjAwMDAwMDA4NzcgMDAwMDAgbiAKMDAwMDAwMDU2OCAwMDAwMCBuIAowMDAwMDAxNDM3IDAwMDAwIG4gCjAwMDAwMDExMjggMDAwMDAgbiAKMDAwMDAwMTk4MSAwMDAwMCBuIAowMDAwMDAxNjg4IDAwMDAwIG4gCjAwMDAwMDIyMzMgMDAwMDAgbiAKMDAwMDAwMjI2OSAwMDAwMCBuIAowMDAwMDAyMzA1IDAwMDAwIG4gCjAwMDAwMDIzNDEgMDAwMDAgbiAKMDAwMDAwMjU0NiAwMDAwMCBuIAowMDAwMDAyNjgzIDAwMDAwIG4gCjAwMDAwMDI3MTcgMDAwMDAgbiAKMDAwMDAwMjgyMiAwMDAwMCBuIAp0cmFpbGVyCjw8L0luZm8gMTggMCBSL0lEIFs8NzY1NDdiZDQ3Njk1MTdkZmUxZjE1OWUwYWYyYzcwZWM+PGYyOTQ2ODZkYjViMWUyMzljMjFjMGRhMGRjNWUzOTFkPl0vUm9vdCAxNyAwIFIvU2l6ZSAxOT4+CnN0YXJ0eHJlZgoyOTkwCiUlRU9GCg==" alt="Red dot" />'
				var x = '<iframe src="data:application/pdf;base64,' + result
						+ '"  height="600" width="100%" />';

				$("#filePdf").append(x);
			},
			error : function(error) {
				//		    	window.location.href = "${userURL}?page=1&limit=2&message=error_system";
				alert("Error");
			}
		});
	}
</script>
