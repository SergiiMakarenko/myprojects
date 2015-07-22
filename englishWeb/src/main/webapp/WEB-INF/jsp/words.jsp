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
      <title>View words</title>
      <script src="js/jquery-1.11.2.min.js"></script>
      <script src="js/english.js"></script>
            <script>
                function next(){
                    if(${fullList!=true}){
                        window.location = "/words.html?portion=${portion}&startPosition=${startPosition+
                        portion}&userWord=${userWord}&effectiveness=${effectiveness}"
                    }
                }

                function preview(){
                    if(${startPosition>0}){
                        window.location ="/words.html?portion=${portion}&startPosition=${startPosition
                        -portion}&userWord=${userWord}&effectiveness=${effectiveness}"
                    }
                }

                $(document).ready(function() {
                    if("${userWord}"!=''){
                        document.getElementById("${userWord}").checked=true;
                    } else{
                        document.getElementById('onlyMy').checked=true;
                    }
                });


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

      <form class="formWork" action="/words.html" method="post">
          <div class="divSubMenu"> <a class="promptText1"> Choice amount words to view: </a> </div>
          <div class="divSubMenu">
          <c:set var="port" value="10"></c:set>
          <c:if test="${portion!=null}">
              <c:set var="port" value="${portion}"></c:set>
          </c:if>
          <input id="range" type="range" min="5" max="20" step="5" name="portion"
                 onchange="rangeValue('range','rangeValue')" value="${port}" > <div class="error" ></div >
          <a id="rangeValue">${port}</a>
          </div>

          <BR>

          <div class="divSubMenu">
              <input type="radio" name="userWord" id="onlyMy" value="onlyMy">Only my words
          </div>
          <div class="divSubMenu">
              <input type="radio" name="userWord" id="onlyAdmin" value="onlyAdmin">Only admins words
          </div>
          <div class="divSubMenu">
              <input type="radio" name="userWord" id="myAndAdmin" value="myAndAdmin">My and admins words
          </div>
          <BR>

          <div class="divSubMenu"> <a> Choice your actual max effectiveness: </a> </div>
          <div class="divSubMenu">
              <c:set var="eff" value="0.75"></c:set>
              <c:if test="${effectiveness!=null}">
                  <c:set var="eff" value="${effectiveness}"></c:set>
              </c:if>
              <input class="viewVerbs" id="effectiveness" type="range" min="0" max="1" step="0.05" name="effectiveness"
                     onchange="rangeValue('effectiveness','effectivenessValue')" value="${eff}">
              <a id="effectivenessValue">${eff}</a>
          </div>
          <BR>

          <input type="submit" onclick="check()" value="View">

      </form>

      <h4>${userName}, where are words by portion: </h4>
      ${message}
      <button onclick="preview()" > <<< </button>
      <button onclick="next()" > >>> </button>
      <table class="resultTable">
          <TR>
              <Th>English word</Th>
              <Th>Ukrainian word</Th>
              <Th>Transcription</Th>
              <Th>Category</Th>
              <Th>Amount of eng-ukr tests</Th>
              <Th>Eng-ukr effectiveness</Th>
              <Th>Amount of ukr-eng tests</Th>
              <Th>Ukr-eng effectiveness</Th>
          </TR>
          <c:set var="rowIndex" value="0"></c:set>

          <c:forEach var="word" items="${wordList}">
              <c:set var="idName" value=""></c:set>
              <c:set var="rowIndex" value="${rowIndex+1}"></c:set>
              <c:if test="${rowIndex%2!=0}">
                  <c:set var="idName" value="whiteRow"></c:set>
              </c:if>
              <TR id="${idName}">

                  <TD>${word.word.englishWord}</TD>
                  <TD>${word.word.ukrainianWord}</TD>
                  <TD>${word.word.transcription}</TD>
                  <TD>${word.word.category.categoryName}</TD>
                  <TD class="effectivenessIsNull">${word.amountEngUkr}</TD>
                  <TD class="effectiveness">${word.effectivenessEngUkr}</TD>
                  <TD>${word.amountUkrEng}</TD>
                  <TD>${word.effectivenessUkrEng}</TD>
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
