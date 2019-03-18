<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dettaglio Libro</title>
</head>
<body>

	<div class="container">

		<%@ include file="../header.jsp"%>

		<div class="page-header">
			<h3>Pagina di Dettaglio</h3>
		</div>
		<div class="container-fluid">
			<dl class="row">
				<dt class="col-sm-3 text-right">Id</dt>
				<dd class="col-sm-9">${libroCommand.id }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Titolo</dt>
				<dd class="col-sm-9">${libroCommand.titolo }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Genere</dt>
				<dd class="col-sm-9">${libroCommand.genere }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Numero pagine</dt>
				<dd class="col-sm-9">${libroCommand.numeroPagine }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Data Pubblicazione</dt>
				<dd class="col-sm-9">${libroCommand.dataPubblicazione }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Autore</dt>
				<dd class="col-sm-9">${libroCommand.autore.nome } ${libroCommand.autore.cognome }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Azione</dt>
				<dd class="col-sm-9"><a href="delete?idLibro=${libroCommand.id }" class="btn btn-primary btn-md">Elimina</a></dd>
			</dl>
		</div>
		<div class="form-group">        
	      <div class="col-sm-offset-2 col-sm-10">
	        <a href="list" class="btn btn-primary btn-md">Torna alla lista</a>
	      </div>
	    </div>

	</div>

</body>
</html>