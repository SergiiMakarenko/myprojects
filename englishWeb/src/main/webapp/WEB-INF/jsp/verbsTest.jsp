<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 30.03.15
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
  <head>
      <title>Test verbs</title>
      <script src="js/jquery-1.11.2.min.js"></script>
      <script src="js/english.js"></script>
            <script>
                function checkVerb(){
                    var verbsPS = document.getElementsByClassName('PS');
                    var verbsPP = document.getElementsByClassName('PP');
                    var rows = document.getElementsByClassName('rowValue');
                    var result = 0;
                    var i=0;
                    document.getElementById('start').disabled=false;
                    document.getElementById('check').disabled=true;
                    var verbId = [];
                    var pastSimple = [];
                    var pastParticiple = [];
                    var pastSimpleResult = [];
                    var pastParticipleResult = [];

                    <c:forEach var="verb" items="${verbsList}">
                                $(verbsPS[i]).css('background', 'white');
                                $(verbsPP[i]).css('background', 'white');
                                verbId.push("${verb.verb.verbId}");
                                pastSimple.push(verbsPS[i].value);
                                pastParticiple.push(verbsPP[i].value);

                                if(verbsPS[i].value=="${verb.verb.pastSimple}"){
                                    $(verbsPS[i]).css('background', 'lightgreen');
                                    pastSimpleResult.push(1);
                                    result++;
                                }else{
                                    $(verbsPS[i]).css('background', 'orangered');
                                    verbsPS[i].value = verbsPS[i].value + ' (correct=' + "${verb.verb.pastSimple}" +')';
                                    pastSimpleResult.push(0);

                                }
                                if(verbsPP[i].value=="${verb.verb.pastParticiple}"){
                                    $(verbsPP[i]).css('background', 'lightgreen');
                                    result++;
                                    pastParticipleResult.push(1);
                                } else{
                                    $(verbsPP[i]).css('background', 'orangered');
                                    verbsPP[i].value = verbsPP[i].value + ' (correct=' + "${verb.verb.pastParticiple}" +')'
                                    pastParticipleResult.push(0);
                                }
                        i++;
                    </c:forEach>

                            document.getElementById('result').innerHTML=
                                    'Your result is ' + result + ' from ' + (verbsPP.length)*2;
                    $.ajax({
                        dataType:'json',
                        url:'/verbsAjax?verbListId='+verbId+'&pastSimple='+pastSimple+
                        '&pastParticiple='+pastParticiple+'&pastSimpleResult='+pastSimpleResult+
                            '&pastParticipleResult='+pastParticipleResult,
                        type:'GET',
                        success: function (data) {

                        },
                        error: function (a, b, c) {
                            alert('error:' + a + b + c);
                        }
                    });
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

      <form id="paramForm" class="formWork" onsubmit="return checkForEmpty('inputParam','error')"
            action="/verbsTest.html" method="post">

          <a class="promptText"> Choice amount irregular verbs to test: </a>
          <c:if test="${wordsAmount==null}">
              <input class="inputParam" id="range" type="range" min="1" max="15" step="1" name="wordsAmount"
                     onchange="rangeValue('range','rangeValue')" value="5" > <div class="error" ></div >
              <a id="rangeValue">5</a>
          </c:if>
          <c:if test="${wordsAmount!=null}">
              <input class="inputParam" id="range" type="range" min="1" max="15" step="1" name="wordsAmount"
                     onchange="rangeValue('range','rangeValue')" value="${wordsAmount}" > <div class="error" ></div >
              <a id="rangeValue">${wordsAmount}</a>
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

          <input id="start" type="submit" value="Start">
      </form>

      <h4>${userName}, where are irregular verbs for test: </h4>
      <table class="resultTable">
          <TR id = "headTable">

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
              <TR id="${idName}" class="rowValue">

                  <TD>${verb.verb.infinitive}</TD>
                  <TD><input class="PS" type="text"></TD>
                  <TD><input class="PP" type="text"></TD>

              </TR>
          </c:forEach>
      </table>

      <button id="check" onclick="checkVerb()"> check </button>
      <div id="result"></div>

  </div>
  <div id="rightColumn">

  </div>
  <div class="Horizontal">

  </div>


  </body>

</html>
