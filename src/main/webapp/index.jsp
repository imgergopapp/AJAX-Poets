<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <c:url value="/style.css" var="styleUrl" />
    <c:url value="/index.js" var="indexScriptUrl" />
    <c:url value="/login.js" var="loginScriptUrl" />
    <c:url value="/profile.js" var="profileScriptUrl" />
    <c:url value="/logout.js" var="logoutScriptUrl" />
    <c:url value="/works.js" var="worksScriptUrl" />
    <link rel="stylesheet" type="text/css" href="${styleUrl}">
    <script src="${indexScriptUrl}"></script>
    <script src="${loginScriptUrl}"></script>
    <script src="${profileScriptUrl}"></script>
    <script src="${logoutScriptUrl}"></script>
    <script src="${worksScriptUrl}"></script>
    <title>App</title>
</head>

<body>
    <div id="login-content" class="content">
        <h1>Login</h1>
        <form id="login-form" onsubmit="return false;">
            <input type="text" name="email">
            <input type="password" name="password">
            <button id="login-button">Login</button>
        </form>
    </div>
    <div id="profile-content" class="hidden content">
        <h1 id="user-name"></h1>
        <h2>My works</h2>
    </div>
    <h2 id="works-h2" class="hidden content">My works</h2>
    <div id="works-content" class="hidden content">
    </div>
    
    <div id="search" class="hidden content">
        <form id="search" onsubmit="return false;">
            <input type="text" name="regex">
            <button id="search-button">Search</button>
        </form>
        <p id= "search-result"></p>
    </div>
    <div id="work-content" class="hidden content">
    </div>
    
    <div id="logout-content" class="hidden content">
        <button id="logout-button">Logout</button>
    </div>
</body>

</html>