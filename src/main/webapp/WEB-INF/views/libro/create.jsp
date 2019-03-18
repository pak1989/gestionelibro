<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserisci nuovo elemento</title>
</head>
<body>

<div class="container">

   <%@ include file="../header.jsp" %>
      
    <div class="page-header">
	  <h3>Pagina di Inserimento</h3>
	</div>

		<form:form class="form-horizontal" action="save" method="post" commandName="libroCommand">
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="titoloId">Titolo:</label>
	    		<div class="col-sm-4">
					<form:input class="form-control" path="titolo" id="titoloId" />
					<form:errors path="titolo" cssStyle="color:red;"/>
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="genereId">Genere:</label>
	    		<div class="col-sm-4">
	    			<form:input class="form-control" path="genere" id="genereId" />
	    			<form:errors path="genere" cssStyle="color:red;"/>
			 	</div>
  			</div>
			<div class="form-group">
      			<label class="control-label col-sm-2" for="numeroPagineId">Numero pagine:</label>
	    		<div class="col-sm-4">
					<form:input type="number" class="form-control" path="numeroPagine" id="numeroPagineId" />
	    			<form:errors path="numeroPagine" cssStyle="color:red;"/>
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="dataPubblicazioneId">Data pubblicazione:</label>
	    		<div class="col-sm-4">
	    			<form:input type="date" class="form-control" path="dataPubblicazione" id="dataPubblicazioneId" />
	    			<form:errors path="dataPubblicazione" cssStyle="color:red;"/>
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="isbnId">ISBN:</label>
	    		<div class="col-sm-4">
	    			<form:input type="number" class="form-control" path="isbn" id="isbnId" />
	    			<form:errors path="isbn" cssStyle="color:red;"/>
			 	</div>
  			</div>
			<div class="form-group">
      			<label class="control-label col-sm-2" for="autoreId">Autore:</label>
	    		<div class="col-sm-4">
			        <form:select path="autore.id">
			            <form:option value="" label="--Seleziona" />
			            <form:options items="${listAutori }" itemValue="id" itemLabel="nome"/>
						<form:errors path="autore" cssStyle="color:red;"/>
			        </form:select>
			 	</div>
  			</div>

<!-- <div class="form-group"> -->
<!-- 		<label class="control-label col-sm-2" for="autoreInputId">Seleziona -->
<!-- 			autore:</label> -->
<!-- 		<div class="col-sm-4"> -->
<%-- 			<form:select class="form-control" path="autore.id" id="autoreInputId" > --%>
<%--				<option>-- Seleziona Autore --</option>
<%-- 				<c:forEach var="autoreItem" items="${listAutori}"> --%>
<%-- 					<option value="${autoreItem.id}" --%>
<%-- 						${clienteItem.id==ordineSingolo.cliente.id?"selected='selected'":""}> --%>
<%-- 						${autoreItem.nome}</option> --%>
<%-- 				</c:forEach> --%>
<%-- 				<form:errors path="autore.id" cssStyle="color:red;"/> --%>
<%-- 		</form:select> --%>
<!-- 		</div> -->
<!-- 	</div> -->
  			
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Effetua Inserimento</button>
		      </div>
		    </div>
		</form:form>
		
    </div><!-- /.container -->
    
    <script type="text/javascript">

		$( document ).ready( function () {
			$.validator.addMethod('isbn', function (value) {
			    return /^(97(8|9))?\d{9}(\d|X)$/.test(value);
			}, "Codice ISBN non valido.");

			$( "#libroCommand" ).validate( {
				rules: {
					titolo: "required",
					genere: "required",
					numeroPagine: "required",
					dataPubblicazione: "required",
					isbn: {
						required: true,
						isbn: true
					},
					autore: "required"
					
				},
				messages: {
					titolo: "Inserisci una titolo",
					genere: "Inserisci un genere",
					numeroPagine: "Inserisci una numero pagine",
					dataPubblicazione: "Inserisci una numero pagine",
					isbn: {
						required: "Inserisci una numero ISBN",
					},
					autore: "Seleziona un autore"
				},
				errorElement: "em",
				errorPlacement: function ( error, element ) {
					// Add the `help-block` class to the error element
					error.addClass( "help-block" );
					element.parents( ".col-sm-5" ).addClass( "has-feedback" );
					if ( element.prop( "type" ) === "checkbox" ) {
						error.insertAfter( element.parent( "label" ) );
					} else {
						error.insertAfter( element );
					}
					if ( !element.next( "span" )[ 0 ] ) {
						$( "<span class='glyphicon glyphicon-remove form-control-feedback'></span>" ).insertAfter( element );
					}
				},
				success: function ( label, element ) {
					// Add the span element, if doesn't exists, and apply the icon classes to it.
					if ( !$( element ).next( "span" )[ 0 ] ) {
						$( "<span class='glyphicon glyphicon-ok form-control-feedback'></span>" ).insertAfter( $( element ) );
					}
				},
				highlight: function ( element, errorClass, validClass ) {
					$( element ).parents( ".col-sm-5" ).addClass( "has-error" ).removeClass( "has-success" );
					$( element ).next( "span" ).addClass( "glyphicon-remove" ).removeClass( "glyphicon-ok" );
				},
				unhighlight: function ( element, errorClass, validClass ) {
					$( element ).parents( ".col-sm-5" ).addClass( "has-success" ).removeClass( "has-error" );
					$( element ).next( "span" ).addClass( "glyphicon-ok" ).removeClass( "glyphicon-remove" );
				}
			} );
		} );
	</script>

</body>
</html>