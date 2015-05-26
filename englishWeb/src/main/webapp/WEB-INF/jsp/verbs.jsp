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
                function next(){
                    if(${fullList!=true}){
                        window.location = "/verbs.html?portion=${portion}&startPosition=${startPosition+portion}"
                    }
                }
                function preview(){
                    if(${startPosition>0}){
                        window.location = "/verbs.html?portion=${portion}&startPosition=${startPosition-portion}"
                    }
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
      <form class="formWork" onsubmit="return checkForEmpty('viewVerbs','error')" action="/verbs.html" method="post">
          <a class="promptText"> Choice amount words to view: </a>

          <c:if test="${portion==null}">
              <input class="viewVerbs" id="range" type="range" min="5" max="50" step="5" name="portion"
                     onchange="rangeValue('range','rangeValue')" value="15" > <div class="error" ></div >
              <a id="rangeValue">15</a>
          </c:if>

          <c:if test="${portion!=null}">
              <input class="viewVerbs" id="range" type="range" min="5" max="50" step="5" name="portion"
                     onchange="rangeValue('range','rangeValue')" value="${portion}" > <div class="error" ></div >
              <a id="rangeValue">${portion}</a>
          </c:if>

          <BR>
          <input type="submit" value="View">

      </form>

      <h4>${userName}, where are irregular verbs by portion: </h4>
      <table class="resultTable">
          <TR>

              <Th>Infinitive</Th>
              <Th>PastSimple</Th>
              <Th>PastParticiple</Th>
          </TR>
          <c:set var="rowIndex" value="0"></c:set>

          <c:forEach var="verb" items="${verbsList}">
              <c:set var="idName" value=""></c:set>
              <c:set var="rowIndex" value="${rowIndex+1}"></c:set>
              <c:if test="${rowIndex%2!=0}">
                  <c:set var="idName" value="whiteRow"></c:set>
              </c:if>
              <TR id="${idName}">

                  <TD>${verb.infinitive}</TD>
                  <TD>${verb.pastSimple}</TD>
                  <TD>${verb.pastParticiple}</TD>

              </TR>
          </c:forEach>
      </table>
      ${message}
      <button onclick="preview()" > <<< </button>
      <button onclick="next()" > >>> </button>

  </div>
  <div id="rightColumn">

  </div>
  <div class="Horizontal">

  </div>

  </body>

</html>
