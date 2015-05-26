<%@ page import="java.util.Date" %>
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
      <title>Edit user</title>
      <script src="js/jquery-1.11.2.min.js"></script>
      <script src="js/english.js"></script>
            <script>

            </script>
      <link rel="stylesheet" type="text/css" href="css/englishStyle.css">
      <style type="text/css">

      </style>

  </head>
  <body>


  <div class="Horizontal">

      <div class="User">${userName}</div>

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
      <form class="formWork" onsubmit="return checkForEmpty('editUser','error')" action="/register.html" method="post">

          <div class="promptText">Input user login*: </div>
          <input id="loginNew" class="editUser" type="text" name="userLogin" value="${userLogin}"
                 onchange="checkTextLong('loginNew','erLogin','4')">
          <div id="erLogin" class="error" ></div>
          <div class="promptText">Input user password*: </div>
          <input id="pass" class="editUser" type="password" name="userPassword"
                 onchange="checkTextLong('pass','erPass','4')" value="${userPassword}">
          <div id="erPass" class="error" ></div >
          <div class="promptText">Confirm user password*: </div>
          <input id="passConfirm" class="editUser" type="password" name="userPasswordConfirm"
                 onchange="checkEquals('pass','passConfirm','erConfirm')" value="${userPasswordConfirm}">
          <div id="erConfirm" class="error" ></div >
          <BR>
          <input type="submit" value="Register">

      </form>
      <h4 class="error"> ${RegisterMessageFalse} </h4>

  </div>
  <div id="rightColumn">

  </div>
  <div class="Horizontal">

  </div>


  </div>

  </body>

</html>
