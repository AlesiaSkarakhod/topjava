<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h1><a href="index.html">Home</a></h1>
<hr>
<h2>Create meal</h2>

<form method="POST" action='meals?action=change' name="frmChangeMeal">
    <table>
        <tbody>
        <tr>
            <th>Data and time :</th>
            <th>Description of meal :</th>
            <th>Calories :</th>
        </tr>

        <tr style="color: ${meal.excess? 'red': 'green'}">
            <td><input type="datetime-local" name="dateTime"
                       value="${requestScope.meal!=null?requestScope.meal.dateTime:''}"/></td>
            <td><input type="text" name="description"
                       value="${requestScope.meal!=null?requestScope.meal.description:''}"/></td>
            <td><input type="text" name="calories"
                       value="${requestScope.meal!=null?requestScope.meal.calories:0}"/></td>

        </tr>

        <th colspan="5"><input type="hidden" name="changeForm"
                               value="${requestScope.meal!=null?requestScope.meal.id:''}"/>
            <input type="submit" value="Submit"/></th>
        </tbody>
    </table>
</form>
</body>
</html>
