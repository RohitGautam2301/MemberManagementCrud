<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Member form</title>
<!-- CDN > Content Delivery Management-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
<header style="color:white">
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Member Management</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="home">Member</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
</header>
<br/>
<br/>
 <div class="container" style="width:50%" >
	
	<c:if test="${member == null}">
	
		<form action="add" method="post">      <!-- When data is not coming from servlet -->
	
		<h2>Add Member</h2>    
		
	</c:if>	
	
	<c:if test="${member != null}">        <!-- When data is coming from servlet -->
	
		<form action="edit" method="post">
	
	 	<h2>Edit Member</h2>
	 	
	 </c:if>	
	
	<div class="row mb-3" hidden="hidden">
      <input value="<c:out value="${member.id}"></c:out>" type="text" class="form-control" id="inputHiddenId" name="tbId">
    </div>
  <div class="row mb-3">
    <label for="inputName" class="col-sm-2 col-form-label">Name</label>
    <div class="col-sm-10">
      <input value="<c:out value="${member.name}"></c:out>" type="text" class="form-control" id="inputName" name="tbName" placeholder="Enter your Name" required="required">
    </div>
  </div>
  <div class="row mb-3">
    <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
    <div class="col-sm-10">
      <input value="<c:out value="${member.email}"></c:out>" type="email" class="form-control" id="inputEmail" name="tbEmail" placeholder="Enter your Email" required="required">
    </div>
  </div>
  <div class="row mb-3">
    <label for="inputMobile" class="col-sm-2 col-form-label">Mobile</label>
    <div class="col-sm-10">
      <input value="<c:out value="${member.mobile}"></c:out>" type="tel" class="form-control" id="inputMobile" name="tbMobile" placeholder="Enter your mobile no." required="required">
    </div>
  </div>
  
  <div>
  <button type="submit" class="btn btn-success">Save</button>
  </div>
  
</form>
</div>

</body>
</html>