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


	
/*function animacionScroll(){	
$(document).on("scroll",function(){
    //detectar scroll hacia abajo
    var obj = $(window);          //objeto sobre el que quiero detectar scroll
    var obj_top = obj.scrollTop();   //scroll vertical inicial del objeto
    obj.scroll(function(){
        var obj_act_top = $(this).scrollTop();  //obtener scroll top instantaneo
        if(obj_act_top > obj_top){
            //scroll hacia abajo
            
            $('#header').fadeOut('slow');
            
        }else{
            //scroll hacia arriba
            
        	$('#header').fadeIn('slow');
            
        }
        obj_top = obj_act_top;                  //almacenar scroll top anterior
    });
});
}
	
$(document).ready(animacionScroll());
*/


