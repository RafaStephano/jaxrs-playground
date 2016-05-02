app.controller("indexCtrl", function($scope, $http) {
	$scope.listarContos = function() {
		$scope.showLoadingRow = true;
		$scope.showLoadingWidget = true;
		$scope.showLoadingMessage = false;
		$http.get(host + "v1/conto", {
			headers : {
				'Accept' : 'application/json'
			}
		}).then(function(response) {
			// Success
			$scope.contos = response.data;
			$scope.showLoadingRow = false;
		}, function(response) {
			// Error
			$scope.contos = null;
			$scope.showLoadingRow = true;
			$scope.showLoadingWidget = false;
			$scope.showLoadingMessage = true;
			if (response.status = -1) {
				// Timeout
				$scope.erro = {mensagem:"Timeout"};
			} else {
				$scope.erro = {mensagem:response.status};
			}
		});
	};
	$scope.listarContos();
});