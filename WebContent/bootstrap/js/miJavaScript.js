function confirmarEIrA(servlet){
	if(confirm('¿Esta seguro?')){
		location.href=servlet;
	}
}	

function detalleArticulo(id){
	window.location="DetalleArticuloServlet/?idArticulo="+id;
}
		
function ponerMano(id){
	document.getElementById(id).style.cursor='hand';
}

function ponerFlechita(id){
	document.getElementById(id.style.cursor='auto');
}

$(document).ready( function(){

	var posicionActual, posicionNueva = 0;

	$(window).scroll(function(){
	posicionNueva = $(this).scrollTop();

	if(posicionNueva>posicionActual){
	$('#header').hide('slow');
	} else if(posicionNueva<posicionActual){
	$('#header').show('slow');
	}
	posicionActual=posicionNueva;
	});

	})
	

	