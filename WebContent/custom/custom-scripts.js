function confirmarEIrA(servlet) {
	if (confirm('¿Esta seguro?')) {
		location.href = servlet;
	}
}	

function detalleArticulo(id) {
	window.location = "DetalleArticuloServlet/?idArticulo="+id;
}
		
function ponerMano(id) {
	document.getElementById(id).style.cursor = 'hand';
}

function ponerFlechita(id) {
	document.getElementById(id.style.cursor = 'auto');
}

function recoverPassword() {
	var username = document.getElementById('username').value;
	location.href = 'RecuperarContraseniaServlet?username=' + username;
}
