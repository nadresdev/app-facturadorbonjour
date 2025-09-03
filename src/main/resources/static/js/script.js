
let buscadorEstadisticas = {
 consultarEstadisticas : function() {

let term = $("#mes_rango").val();
			$.ajax({
                // la URL para la petición
                url : /*[[@{/bonjour/estadisticas/getestadisticas/}]]*/ "" + term,

                // la información a enviar
                // (también es posible utilizar una cadena de datos)
                data : { term : term },

                // especifica si será una petición POST o GET
                type : 'GET',

                // el tipo de información que se espera de respuesta
                dataType : 'json',

                // código a ejecutar si la petición es satisfactoria;
                // la respuesta es pasada como argumento a la función
                	success: function (data) {

                	$.map(data, function (item) {
                    						return {
                    							tot_bene: item.total_beneficio,
                    							tot_prof: item.total_profit,
                    							tot_invent: item.total_inventario

              

                    						};
                    					})
console.log(data.total_beneficio);
$("#balance").html(data.total_beneficio -  data.total_inventario- data.total_egresos);
$("#total_ventas").html(tot_bene);
$("#total_profit").html(data.total_profit);
$("#total_inventario").html(data.total_inventario);











                									},



                // código a ejecutar si la petición falla;
                // son pasados como argumentos a la función
                // el objeto de la petición en crudo y código de estatus de la petición
                error : function(xhr, status) {
                    alert('Disculpe, existió un problema');
                },

                // código a ejecutar sin importar si la petición falló o no
                complete : function(xhr, status) {

                }
            });


} }
let estadisticasHelper = {



acturalizarValores : function(total_beneficio, total_profit, total_inventario, total_egresos){

$("#balance").html(parseInt(total_beneficio) -  parseInt(total_inventario) - parseInt(total_egresos));
$("#total_ventas").html(parseInt(total_beneficio));
$("#total_profit").html(parseInt(total_profit));
$("#total_inventario").html(parseInt(total_inventario));

return;

},

getPorcentajeBeneficio : function(){ //
	
	let elementbeneficioProducto = document.getElementById("beneficioProducto");
	let precioVenta =$("#precioVenta").val();
	let precioInventario = $("#precioInventario").val();
	let porcentaje = 0 ;
	if(precioInventario!="" &&  precioVenta != "" && (precioVenta - precioInventario > 0)){
		porcentaje = (((precioVenta-precioInventario)*100) /precioVenta).toFixed(2);
	}else{porcentaje=0}
	
	elementbeneficioProducto.setAttribute("value",Math.round(porcentaje));
    $("#beneficioProducto_label").html(porcentaje)
	return ;
	
}
,

mostrarAlertaEstado : function(estado,tipoalerta){
	let alerta = document.getElementById("Estado_balance");
	alerta.className = tipoalerta; // "alert alert-warning";
	alerta.textContent = estado;
	setTimeout(function () {
	      $("#alerta_balance");
	    }, 3000);
}
		}

