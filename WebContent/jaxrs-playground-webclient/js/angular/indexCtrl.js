app.controller("indexCtrl", function($scope, $http) {
	$scope.listarContos = function() {
		$scope.showLoadingRow = true;
		$scope.showLoadingWidget = true;
		$scope.showLoadingMessage = false;
		$http.get(host + "v1/conto", {
			headers : {
				"Accept" : "application/json"
			}
		}).success(function(data, status, headers, config) {
			$scope.contos = data;
			$scope.showLoadingRow = false;
		}).error(function(data, status, headers, config) {
			$scope.contos = null;
			$scope.showLoadingRow = true;
			$scope.showLoadingWidget = false;
			$scope.showLoadingMessage = true;
			if (status == -1) {
				$scope.erro = {mensagem:"Timeout"};
			} else {
				$scope.erro = {mensagem:status};
			}
		});
	};
	$scope.novoConto = function() {
		$("#modalAddConto").modal("show");
	};
	$scope.adicionarConto = function() {
		$("#dataCadastro").val(moment().format(isoDateFormat));
		var data = $("#criarContoForm").serializeJSON();
		data = JSON.stringify(data);
		$http.post(host + "v1/conto", data, {
			headers : {
				"Accept" : "application/json"
			}
		}).success(function(data, status, headers, config) {
			alert(headers("Location"));
			$scope.listarContos();
		}).error(function(data, status, headers, config) {
			if (status == -1) {
				alert("Timeout");
			} else {
				$scope.erro = {mensagem:data};
			}
		});
	};
	$scope.listarContos();
});