<%@ page language="java" contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css"
      href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" />
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<script type="text/javascript"
        src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
    <title>Ajouter Club</title>
</head>
<body>
<div class="container">
    <div >
        <form action="saveClub" method="post">
            <div class="fform-floating mb-3">
                <label class="control-label">Nom Club :</label>
                <input type="text" name="nomclub" class="form-control"/>
            </div>
            <div class="form-floating mb-3">
                <label class="control-label">Prix Club :</label>
                <input type="text" name="prixclub" class="form-control"/>
            </div>
            <div class="form-floating mb-3">
                <label class="control-label">date création :</label>
                <input type="date" name="date" class="form-control"/>
            </div>
            <div class="form-floating mb-3">
                <label class="control-label">Ojectif du club :</label>
                <input type="text" name="objetclub" class="form-control"/>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">Ajouter</button>
            </div>
        </form>
    </div>
    ${msg}
    <br/>
    <br/>
    <a href="ListeProduits">Liste Produits</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-VwmnY+TX7Vwn8WjsnJX9pLjKdKy7vL8WxgyaJ2lMEYt3qGvI8WtJET9DpO23oDdE"
        crossorigin="anonymous"></script>
</body>
</html>