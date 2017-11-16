app.controller("indexCtrl", function($scope, $http, $locale, ngLocaleCustomValue) {
    
	$scope.locale= $locale.id;
    $scope.new_ = ngLocaleCustomValue.new_;
    $scope.cancel = ngLocaleCustomValue.cancel;
    $scope.close = ngLocaleCustomValue.close;
    $scope.title = ngLocaleCustomValue.title;
    $scope.autor = ngLocaleCustomValue.autor;
    
	$scope.listarContos = function() {
		$scope.showLoadingRow = true;
		$scope.showLoadingWidget = true;
		$scope.showLoadingMessage = false;
		$http.get(host + "v1/conto", {
			headers : {
				"Accept" : "application/json"
			}
		}).success(function(data, status, headers, config) {
			$scope.contos = data.contos;
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
		$("#modalAddConto").modal("hide");
		$("#modalAddConto").modal("show");
	};
	$scope.verConto = function(id) {
		$("#modalVerConto").modal("hide");
		$http.get(host + "v1/conto/" + id, {
			headers : {
				"Accept" : "application/json"
			}
		}).success(function(data, status, headers, config) {
			$scope.conto = data;
		}).error(function(data, status, headers, config) {
			$scope.conto = null;
			if (status == -1) {
				$scope.erro = {mensagem:"Timeout"};
			} else {
				$scope.erro = {mensagem:status};
			}
		});
		$("#modalVerConto").modal("show");
	};
	$scope.adicionarConto = function() {
		$("#dataCadastro").val(moment().format());
		var data = $("#criarContoForm").serializeJSON();
		data = JSON.stringify(data);
		$http.post(host + "v1/conto", data, {
			headers : {
				"Accept" : "application/json"
			}
		}).success(function(data, status, headers, config) {
			$("#modalAddConto").modal("hide");
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