/**
 * 
 */

function send(user , mensagem) {
    var mensagem = {
        m : $("#mensagem").val(),
        u :user ;
    }

    $('#target').html('sending..');

    $.ajax({
    	url : 'http://localhost:8080/mensagem/detalhe/json/usuario='+user ,
        type: 'post',
        dataType: 'json',
        success: function (data) {
        	 document.getElementById("progress").style.display = 'none';
 			$.each(result,function(K, V) {
 							
 								if (V.author !=  usuarioLogado){
 									mensagens += '<div class="direct-chat-msg">'+
 					              ' <div class="direct-chat-info clearfix">'+
 				                  '<span class="direct-chat-name float-left">'+V.author+'</span>'+
 				                  '<span class="direct-chat-timestamp float-right">'+ V.data +'</span>'+
 				                  '  </div>'+  
 				                  '<img class="direct-chat-img" src="/img/user1-128x128.jpg" alt="Message User Image">'+					               
 				                  '<div class="direct-chat-text">'+ V.mensagem +
 				                  ' </div>'+                
 				                 ' </div>';
 						
 								}else {
 									mensagens += '<div class="direct-chat-msg right">'+
 					                   ' <div class="direct-chat-info clearfix">'+
 				                      '<span class="direct-chat-name float-right">'+V.author+'</span>'+
 				                      '<span class="direct-chat-timestamp float-lef">'+ V.data +'</span>'+
 				                  '  </div>'+					                   
 				                  '<img class="direct-chat-img" src="/img/user1-128x128.jpg" alt="Message User Image">'+
 				               
 				                  '<div class="direct-chat-text">'+ V.mensagem +
 				                  ' </div></div>';
 								}
 								
 								
 							});
 		
 			$("#contentM").html($(mensagens));
        },
    	data: {usuario : user , mensagem : mensagem },
    });
}