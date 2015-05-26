
<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 30.03.15
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
  <head>
      <title>Menu</title>
      <script src="js/jquery-1.11.2.min.js"></script>
      <script src="js/english.js"></script>
            <script>
                function addName(nodeId, nodeText){
                    document.getElementById(nodeId).innerHTML=nodeText;
                }

            </script>

      <link rel="stylesheet" type="text/css" href="css/englishStyle.css">
      <style type="text/css">

      </style>

  </head>
  <body>

<div class="Horizontal">
    <%--<div class="backToHome"></div>--%>
    <div class="User" >
        ${userName}
    </div>

</div>
  <div class="Horizontal">

      <ul class="dropdown">
          <c:forEach var="mapKey" items="${map.keySet()}">
              <li class="dropdown-top">
                  <div class="divMenuHead">${mapKey}</div>
                      <ul class="dropdown-inside">
                          <c:forEach var="menus" items="${map.get(mapKey)}">
                            <li>
                                <div id="${menus.menuItemsCode}" class="divMenu" onclick="changePage
                                        ('/'+'${menus.menuItemsCode}'+'.html')"
                                        onmouseover="onActiveMenu('${menus.menuItemsCode}')"
                                        onmouseout="desActiveMenu('${menus.menuItemsCode}')">
                                        ${menus.menuItems}</div>
                            </li>
                          </c:forEach>


                      </ul>
              </li>

          </c:forEach>

      </ul>


  </div>
    <div id="leftColumn">

    </div>
    <div id="mainBlock">
        <c:if test="${userName=='Guest'}">
        <form class="formTitle" onsubmit="return checkForEmpty('authTest','error')" action="/homePage.html" method="post">
            <%--<h3>LOGIN</h3>--%>

            <div class="promptText"> Input your login*: </div>
            <input class="authTest" type="text" name="login" value="Sergii" > <a class="error" ></a >
            <br>
            <div class="promptText"> Input your password*: </div>
            <input class="authTest" type="password" name="pass" value="eng"> <a class="error" ></a >
            <BR>
            <input class="buttonLogIn" type="submit" value="">
            <div class="error"> ${tryAgain}</div>

        </form>
    <button class="buttonRegister" onclick="window.changePage('/register.html')"></button>
        </c:if>
    </div>
    <div id="rightColumn">

    </div>
    <div class="Horizontal">

    </div>

  </body>

</html>
