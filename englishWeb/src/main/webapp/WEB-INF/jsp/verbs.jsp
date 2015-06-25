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
      <title>Verbs</title>
      <script src="js/jquery-1.11.2.min.js"></script>
      <script src="js/english.js"></script>
            <script>
                function next(){
                    if(${fullList!=true}){
                        window.location = "/verbs.html?portion=${portion}&startPosition=${startPosition+
                        portion}&effectiveness=${effectiveness}";
                    }
                }
                function preview(){
                    if(${startPosition>0}){
                        window.location = "/verbs.html?portion=${portion}&startPosition=${startPosition
                        -portion}&effectiveness=${effectiveness}";
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
      <form class="formWork" action="/verbs.html" method="post">
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

          <a class="promptText"> Choice the max effectiveness: </a>
          <c:set var="eff" value="0.75"></c:set>
          <c:if test="${effectiveness!=null}">
              <c:set var="eff" value="${effectiveness}"></c:set>
          </c:if>
          <input class="viewVerbs" id="effectiveness" type="range" min="0" max="1" step="0.05" name="effectiveness"
                 onchange="rangeValue('effectiveness','effectivenessValue')" value="${eff}">
          <a id="effectivenessValue">${eff}</a>
          <BR>


          <input class="buttonForm" type="submit" value="View">

      </form>

      <h4>${userName}, where are irregular verbs by portion: </h4>
      ${message}
      <button onclick="preview()" > <<< </button>
      <button onclick="next()" > >>> </button>
      <table class="resultTable">
          <TR>

              <Th>Infinitive</Th>
              <Th>PastSimple</Th>
              <Th>PastParticiple</Th>
              <Th>Amount of tests</Th>
              <Th>Effectiveness PS</Th>
              <Th>Effectiveness PP</Th>

          </TR>
          <c:set var="rowIndex" value="0"></c:set>

          <c:forEach var="verb" items="${verbsList}">
              <c:set var="idName" value=""></c:set>
              <c:set var="rowIndex" value="${rowIndex+1}"></c:set>
              <c:if test="${rowIndex%2!=0}">
                  <c:set var="idName" value="whiteRow"></c:set>
              </c:if>
              <TR id="${idName}">
                  <TD>${verb.verb.infinitive}</TD>
                  <TD>${verb.verb.pastSimple}</TD>
                  <TD>${verb.verb.pastParticiple}</TD>
                  <TD class="effectivenessIsNull">${verb.amount}</TD>
                  <TD class="effectiveness">
                          ${verb.effectivenessPS}
                  </TD>
                  <TD class="effectiveness">
                          ${verb.effectivenessPP}
                  </TD>
              </TR>
          </c:forEach>
      </table>


  </div>
  <div id="rightColumn">

  </div>
  <div class="Horizontal">

  </div>

  </body>

</html>
