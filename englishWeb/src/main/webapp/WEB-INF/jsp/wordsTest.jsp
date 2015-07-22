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


                function checkWord(){
                    var wordsUKR = document.getElementsByClassName('UKR');
                    var rows = document.getElementsByClassName('rowValue');
                    var result = 0;
                    var i=0;
                    document.getElementById('start').disabled=false;
                    document.getElementById('check').disabled=true;
                    var wordId = [];
                    var ukrainian = [];
                    var ukrainianResult = [];

                    <c:forEach var="word" items="${wordsList}">
                                $(wordsUKR[i]).css('background', 'white');
                                wordId.push("${word.word.wordId}");
                                ukrainian.push(wordsUKR[i].value);

                                if(wordsUKR[i].value=="${word.word.ukrainianWord}"){
                                    $(wordsUKR[i]).css('background', 'lightgreen');
                                    ukrainianResult.push(1);
                                    result++;
                                }else{
                                    $(wordsUKR[i]).css('background', 'orangered');
                                    wordsUKR[i].value = wordsUKR[i].value + ' (correct=' +
                                    "${word.word.ukrainianWord}" +')';
                                    ukrainianResult.push(0);
                                }
                        i++;
                    </c:forEach>

                            document.getElementById('result').innerHTML=
                                    'Your result is ' + result + ' from ' + (wordsUKR.length);

                    $.ajax({
                        dataType:'json',
                        url:'/EngUkrTranslate?wordId='+wordId+'&ukrainian='+ukrainian+
                        '&ukrainianResult='+ukrainianResult,
                        type:'GET',
                        success: function (data) {

                        },
                        error: function (a, b, c) {
                            alert('error:' + a + b + c);
                        }
                    });
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

      <form id="paramForm" class="formWork" onsubmit="return checkForEmpty('authTest','error')"
            action="/wordsTest.html" method="post">

          <div class="divSubMenu"> <a> Choice amount words to test: </a> </div>
          <c:if test="${cntWords==null}">
              <input class="authTest" id="range" type="range" min="1" max="15" step="1" name="cntWords"
                     onchange="rangeValue('range','rangeValue')" value="10" > <div class="error" ></div >
              <a id="rangeValue">10</a>

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

          </c:if>
          <c:if test="${cntWords!=null}">
              <input class="authTest" id="range" type="range" min="1" max="15" step="1" name="cntWords"
                     onchange="rangeValue('range','rangeValue')" value="${cntWords}" > <div class="error" ></div >
              <a id="rangeValue">${cntWords}</a>

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
          </c:if>


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

              <input id="start" type="submit" value="Start">

      </form>

      <h4>${userName}, where are words for test: </h4>
      <table class="resultTable">
          <TR id = "headTable">

              <Th>English</Th>
              <Th>Language category</Th>
              <Th>Ukrainian</Th>

          </TR>
          <c:set var="rowIndex" value="0"></c:set>

          <c:forEach var="word" items="${wordsList}">
              <c:set var="idName" value=""></c:set>
              <c:set var="rowIndex" value="${rowIndex+1}"></c:set>
              <c:if test="${rowIndex%2!=0}">
                  <c:set var="idName" value="whiteRow"></c:set>
              </c:if>
              <TR id="${idName}" class="rowValue">

                  <TD>${word.word.englishWord}</TD>
                  <TD>${word.word.category.categoryName}</TD>
                  <TD><input class="UKR" type="text"></TD>


              </TR>
          </c:forEach>
      </table>

      <button id="check" onclick="checkWord()"> check </button>
      <div id="result"></div>

  </div>
  <div id="rightColumn">

  </div>
  <div class="Horizontal">

  </div>


  </body>

</html>
