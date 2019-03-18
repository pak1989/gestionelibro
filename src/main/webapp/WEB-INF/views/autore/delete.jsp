<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dettaglio Autore</title>
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
				<dd class="col-sm-9">${autoreCommand.id }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Nome</dt>
				<dd class="col-sm-9">${autoreCommand.nome }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Cognome</dt>
				<dd class="col-sm-9">${autoreCommand.cognome }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Data Nascita</dt>
				<dd class="col-sm-9">${autoreCommand.dataNascita }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Nickname</dt>
				<dd class="col-sm-9">${autoreCommand.nickname }</dd>
			</dl>
		</div>
		<div class="form-group">        
	      <div class="col-sm-offset-2 col-sm-10">
	        <a href="remove?idAutore=${autoreCommand.id }" class="btn btn-primary btn-md">DELETE</a>
	      </div>
	    </div>

	</div>

</body>
</html>