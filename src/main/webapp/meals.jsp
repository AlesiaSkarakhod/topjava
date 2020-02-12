<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<style>
    table {
        border: 1px solid black;
        border-collapse: collapse;
    }

    th {
        text-align: left;
        padding: 5px;
        border: 1px solid black;
    }

    td {
        padding: 5px;
        border: 1px solid black;
    }

    #create {
        text-align: center;
    }
</style>
<body>

<h1><a href="index.html">Home</a></h1>
<hr>

<h2>List meals</h2>

<table>
    <tbody>
    <tr>
        <th>Data</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan="2">Action</th>
    </tr>
    <c:forEach items="${requestScope.listMealsDynamic}" var="meal">
    <tr style="color: ${meal.excess? 'red': 'green'}">
        <td><c:out value="${requestScope.dateTimeFormatter.format(meal.dateTime)}"></c:out></td>
        <td><c:out value="${meal.description}"></c:out></td>
        <td><c:out value="${meal.calories}"></c:out></td>
        <td><a href="meals?action=change&Id=${meal.id}">Edit</a></td>
        <td><a href="meals?action=delete&Id=${meal.id}">Delete</a></td>
    </tr>
    </c:forEach>
    <th id="create" colspan="5"><a href="meals?action=create">Create meal</a></th>
</table>
</body>
</html>
