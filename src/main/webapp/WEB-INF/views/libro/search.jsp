<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Libro</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/jqueryUI/jquery-ui.min.css" />
<style>
.ui-autocomplete-loading {
	background: white url("${pageContext.request.contextPath}/resources/img/anim_16x16.gif") right center no-repeat;
}
</style>
</head>
<body>

<div class="container">

   <%@ include file="../header.jsp" %>
      
    <div class="page-header">
	  <h3>Pagina di Ricerca</h3>
	</div>

		<form:form class="form-horizontal" action="list" method="post" commandName="libroCommand">
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="titoloId">Titolo:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="titoloId" name="titolo" >
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="genereId">Genere:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="genereId" name="genere" >
			 	</div>
  			</div>
			<div class="form-group">
      			<label class="control-label col-sm-2" for="numeroPagineId">Numero pagine:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="number" id="numeroPagineId" name="numeroPagine" >
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="dataPubblicazioneId">Data publicazione:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="date" id="dataPubblicazioneId" name="dataPubblicazione" >
			 	</div>
  			</div>
<!-- 			<div class="form-group"> -->
<!--       			<label class="control-label col-sm-2" for="autoreId">Autore:</label> -->
<!-- 	    		<div class="col-sm-4"> -->
<%-- 			        <form:select path="autore.id"> --%>
<%-- 			            <form:option value="" label="--Seleziona" /> --%>
<%-- 			            <form:options items="${listAutori }" itemValue="id" itemLabel="nome"/> --%>
<%-- 						<form:errors path="autore" cssStyle="color:red;"/> --%>
<%-- 			        </form:select> --%>
<!-- 			 	</div> -->
<!--   			</div> -->
  			<div class="form-group">
				<label class="control-label col-sm-2" for="autoreId">Autore:</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" id="autoreInputId"
						name="clienteInput">
					<input type="hidden" name="autore.Id" id="autoreId">
				</div>
			</div>
  			
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Effetua Ricerca</button>
		        <a href="create" class="btn btn-primary btn-md">Inserisci Nuovo Elemento</a>
		      </div>
		    </div>
		</form:form>
		
				<script> 
				$( "#autoreInputId" ).autocomplete({
					 source: function(request, response) {
					        $.ajax({
					            url: "searchAutoreJquery",
					            datatype: "json",
					            data: {
					                termAutore: request.term,   
					            },
					            success: function(data) {
					                response($.map(data, function(item) {
					                    return {
						                    label: item.label,
						                    value: item.value
					                    }
					                }))
					            }
					        })
					    },
					//quando seleziono la voce nel campo deve valorizzarsi la descrizione
				    focus: function(event, ui) {
				        $("#autoreInputId").val(ui.item.label)
				        return false
				    },
				    minLength: 2,
				    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
				    select: function( event, ui ) {
				    	$('#autoreId').val(ui.item.value);
				    	console.log($('#autoreId').val())
				        return false;
				    },
				});
				</script>
		
    </div><!-- /.container -->

</body>
</html>