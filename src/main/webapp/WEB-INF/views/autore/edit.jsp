<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifica autore</title>
</head>
<body>

	<div class="container">

	   <%@ include file="../header.jsp" %>
	      
	    <div class="page-header">
		  <h3>Pagina di Modifica</h3>
		</div>
	
			<form:form class="form-horizontal" action="update" method="post" commandName="autoreCommand">
				<form:hidden path="id"/>
	      		<div class="form-group">
	      			<label class="control-label col-sm-2" for="nomeId">Nome:</label>
		    		<div class="col-sm-4">
						<form:input class="form-control" path="nome" id="nomeId" />
						<form:errors path="nome" cssStyle="color:red;"/>
				 	</div>
	  			</div>
	  			<div class="form-group">
	      			<label class="control-label col-sm-2" for="cognomeId">Cognome:</label>
		    		<div class="col-sm-4">
		    			<form:input class="form-control" path="cognome" id="cognomeId" />
		    			<form:errors path="cognome" cssStyle="color:red;"/>
				 	</div>
	  			</div>
	  			<div class="form-group">
	      			<label class="control-label col-sm-2" for="dataNascitaId">Data nascita:</label>
		    		<div class="col-sm-4">
		    			<form:input type="date" class="form-control" path="dataNascita" id="dataNascitaId" />
		    			<form:errors path="dataNascita" cssStyle="color:red;"/>
				 	</div>
	  			</div>
				<div class="form-group">
	      			<label class="control-label col-sm-2" for="nicknameId">Nickname:</label>
		    		<div class="col-sm-4">
						<form:input class="form-control" path="nickname" id="nicknameId" />
		    			<form:errors path="nickname" cssStyle="color:red;"/>
				 	</div>
	  			</div>
	  			
	  			<div class="form-group">        
			      <div class="col-sm-offset-2 col-sm-10">
			      	<a href="list" class="btn btn-primary btn-md">Torna alla lista</a>
			        <button type="submit" class="btn btn-primary btn-md">Effettua Modifica</button>
			      </div>
			    </div>
			</form:form>
		
    </div><!-- /.container -->

</body>
</html>