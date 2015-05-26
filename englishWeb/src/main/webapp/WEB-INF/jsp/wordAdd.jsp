<%@ page import="java.util.Date" %>

<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 30.03.15
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-15" pageEncoding="UTF-8" language="java" %>--%>

<html>

  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Word add</title>
      <script src="js/jquery-1.11.2.min.js"></script>
      <script src="js/english.js"></script>
            <script>
                function test(){
//                    var ukr = document.getElementById('ukr').value;
//                    alert(ukr);
                }

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
      <form class="formWork" onsubmit="return checkForEmpty('inputParam','error')" action="/wordAdd.html" method="post">

          <div class="promptText"> Input english word: </div>
          <input id="eng" class="inputParam" type="text" name="english" value="${english}">
          <div id="erEnglish" class="error" ></div>
          <div class="promptText"> Input ukrainian translate: </div>
          <input id="ukr" class="inputParam" type="text" name="ukrainian" onchange="test()" value="${ukrainian}" >
          <div id="erUkrainian" class="error" ></div>
          <div class="promptText"> Input transcription: </div>
          <input id="trans" type="text" name="transcription" value="${transcription}">
          <div class="promptText"> Choice language category: </div>
          <select id="selectCategory" class="inputParam" name="categoryId" >
              <option></option>
              <c:forEach var="category" items="${categoryList}">
                  <c:if test="${category.categoryId==categoryId}">
                      <option selected="selected" value="${category.categoryId}">${category.categoryName}</option>
                  </c:if>
                  <c:if test="${category.categoryId!=categoryId}">
                      <option value="${category.categoryId}">${category.categoryName}</option>
                  </c:if>
              </c:forEach>
          </select>
          <div id="erCategory" class="error" ></div>

          <BR>
          <input type="submit" value="Create">

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
