function listar() {
	$('#cell-loading-widget').show();
	$('.loading-widget').show();
	$('.loading-message').hide();
	var req = $.ajax({
		url : host + 'v1/conto',
		type : 'GET',
		contentType : 'application/json',
		timeout: 5000,
		accepts : {
	        json: 'application/json'
	    }
	});
	req.success(function(data, textStatus, jqXHR) {
		drawTable(data);
		$('#cell-loading-widget').hide();
		$('.loading-widget').hide();
		$('.loading-message').hide();
	});
	req.error(function(jqXHR, textStatus, errorThrown) {
		if(jqXHR.status==0) {
			$('.loading-message').html('Não foi possível conectar ao servidor... <a onclick="listar();" class="btn btn-primary btn-xs">Tentar novamente</a>').show();
		} else {
			$('.loading-message').html('Ocorreu um erro ao carregar a lista... <a onclick="listar();" class="btn btn-primary btn-xs">Tentar novamente</a>').show();
		}
		$('#cell-loading-widget').show();
		$('.loading-widget').hide();
	});
}

function adicionaConto() {
	$("#dataCadastro").val(moment().format(isoDateFormat));
	var data = $("#criarContoForm").serializeJSON();
	data = JSON.stringify(data);
	var req = $.ajax({
		url : host + 'v1/conto',
		type : 'POST',
		contentType : 'application/json',
		timeout: 10000,
		data : data
	});
	req.success(function() {
		listar();
	});
	req.error(function(jqXHR, textStatus, errorThrown) {
		if(jqXHR.status==0) {
			$("#modal .modal-title").html("Sem conexão");
			$("#modal .modal-body").html("Não foi possível conectar ao servidor...");
		} else {
			$("#modal .modal-title").html(jqXHR.status + " - " + jqXHR.statusText);
			$("#modal .modal-body").html(jqXHR.responseText);
		}
		$('#modal').modal('show');
	});
}

function drawTable(data) {
	$("#contos tbody tr").remove();
    for (var i = 0; i < data.length; i++) {
        drawRow(data[i], (i % 2 == 1 ? '' : ''));
    }
}

function drawRow(rowData, cssClass) {
    var row = $("<tr class='" + cssClass + "'><td>" + rowData.id + "</td><td>" + rowData.autorId + "</td><td>" + rowData.titulo + "</td><td>" + rowData.dataCadastro + "</td></tr>");
    row.appendTo("#contos tbody");
}