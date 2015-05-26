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
      <title>Menu items add</title>
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
      <form class="formWork" onsubmit="return checkForEmpty('input','error')" action="/menuItemsAdd.html" method="post">

          <div class="promptText"> Input menu items name: </div>
          <input class="input" type="text" name="menuItems" value="${menuItems}">
          <div  class="error" ></div>
          <div class="promptText"> Input menu items code: </div>
          <input class="input" type="text" name="menuItemsCode" value="${menuItemsCode}">
          <div class="error" ></div>

          <div class="promptText"> Choice menu*: </div>
          <select class="input" name="menuId" >
              <option></option>
              <c:forEach var="menu" items="${menuList}">
                  <c:if test="${menu.menuId==menuId}">
                      <option selected="selected" value="${menu.menuId}">${menu.menuCategory}=${menu.role.roleName}</option>
                  </c:if>
                  <c:if test="${menu.menuId!=menuId}">
                      <option value="${menu.menuId}">${menu.menuCategory}=${menu.role.roleName}</option>
                  </c:if>
              </c:forEach>
          </select>
          <div class="error" ></div >

          <BR>
          <input class="buttonForm" type="submit" value="Create">

      </form>
      <h4 class="success"> ${RegisterMessage} </h4>
      <h4 class="error"> ${RegisterMessageFalse} </h4>
  </div>
  <div id="rightColumn">

  </div>
  <div class="Horizontal">

  </div>

  </body>

</html>
