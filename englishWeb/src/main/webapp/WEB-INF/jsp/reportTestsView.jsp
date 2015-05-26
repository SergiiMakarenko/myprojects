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
      <title>View tests</title>
      <script src="js/jquery-1.11.2.min.js"></script>
      <script src="js/english.js"></script>
            <script>
                function next(){
                    if(${fullList!=true}){
                        window.location = "/reportTestsView.html?portion=${portion}&startPosition=${startPosition+portion}"
                    }
                }verbs.jsp
                function preview(){
                    if(${startPosition>0}){
                        window.location = "/reportTestsView.html?portion=${portion}&startPosition=${startPosition-portion}"
                    }
                }



                function setBG() {
                   var effectiveness = document.getElementsByClassName('resultTest');
                    for(i=0;i<effectiveness.length;i++){
                        var red= 0;
                        var green = 0;

                        if(effectiveness[i].innerHTML<0.5){
                             red = 255*(1-effectiveness[i].innerHTML*2);
                        } else{
                            green = 255*((effectiveness[i].innerHTML-0.5)*2);
                        }
                        red =  parseInt(red);
                        green= parseInt(green);
                        $(effectiveness[i]).css('color', 'rgb('+red+','+green+',0)');
                        effectiveness[i].innerHTML = effectiveness[i].innerHTML*100 + ' %';
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
      <form class="formWork" onsubmit="return checkForEmpty('viewVerbs','error')"
            action="/reportTestsView.html" method="post">
          <a class="promptText"> Choice amount tests to view: </a>

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

      <h4>${userName}, where are test by portion: </h4>
      <table class="resultTable">
          <TR>

              <Th>Test date</Th>
              <Th>Test name</Th>
              <Th>Count verbs for test</Th>
              <Th>Count correct past simple answer</Th>
              <Th>Past simple effectiveness</Th>
              <Th>Count correct past participle answer</Th>
              <Th>Past participle effectiveness</Th>
              <Th>Common effectiveness</Th>

          </TR>
          <c:set var="rowIndex" value="0"></c:set>

          <c:forEach var="test" items="${testsList}">
              <c:set var="idName" value=""></c:set>
              <c:set var="rowIndex" value="${rowIndex+1}"></c:set>
              <c:if test="${rowIndex%2!=0}">
                  <c:set var="idName" value="whiteRow"></c:set>
              </c:if>
              <TR id="${idName}">

                  <TD>${test.test.testDate}</TD>
                  <TD>${test.test.testName}</TD>
                  <TD>${test. countWordTest}</TD>
                  <TD>${test.correctPastSimpleCount}</TD>
                  <TD class="resultTest" >${test.pastSimpleEffectiveness}</TD>
                  <TD>${test.correctPastParticipleCount}</TD>
                  <TD class="resultTest" >${test.pastParticipleEffectiveness}</TD>
                  <TD class="resultTest" >${test.effectiveness}</TD>

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