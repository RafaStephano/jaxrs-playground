'use strict';
angular.module("ngLocaleCustom", [], ["$provide", function($provide) {    
    $provide.value("ngLocaleCustomValue", {
        new_ : "novo",
        save : "salvar",
        cancel : "cancelar",
       	close : "fechar",
       	title : "título",
       	autor : "autor"
    });
}]);
