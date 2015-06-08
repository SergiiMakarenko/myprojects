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

                function detailVerbs(testId){
                    $('body').append($('<div id="viewDetail">'+'</div>'));
                    var tempDiv = document.getElementById("viewDetail");
                    $(tempDiv).append($('<table class="resultTable" id = "tempTable">' + '</table>'));
                    var table = document.getElementById("tempTable");
                    $(table).append($('<TR id = "headTableTemp">'+'</TR>'));
                    var head = document.getElementById("headTableTemp");

                    $(head).append($('<Th>'+'Infinitive'+'</Th>'));
                    $(head).append($('<Th>'+'Past Simple answer'+'</Th>'));
                    $(head).append($('<Th>'+'Past Participle answer'+'</Th>'));

                    var colorPS;
                    var colorPP;;

                    $.ajax({
                                datatype: 'json',
                                url: '/verbTestDetail?testId='+testId,
                                success: function(data){
                                    for(i=0;i<data.length;i++){
                                        colorPS = 'inherit';
                                        colorPP = 'white';
                                        $(table).append($('<TR id = "TR'+i+'">'+'</TR>'));
                                        var row = document.getElementById('TR'+i);
                                        $(row).append($('<TD>'+data[i].infinitive+ '</TD>'));
                                        if(data[i].pastSimpleResult==0){
                                            colorPS = 'orange';
                                        }
                                        $(row).append($('<TD>'+data[i].pastSimpleTest+ '</TD>').
                                                css('background', colorPS));
                                        $(row).append($('<TD>'+data[i].pastParticipleTest+ '</TD>'));
                                    }

                                },
                                error: function(a,b,c){
                                    alert('error: ' + a+b+c);
                                }
                            }
                    );



                    $(tempDiv).append($('<button id="buttonOk" onclick="deleteDetailView()">'+'OK'+'</button>'));
                }

                function deleteDetailView(){
                    var tempDiv = document.getElementById("viewDetail");
                    $(tempDiv).remove();
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
              <Th>Detail</Th>

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
                  <TD ><button onclick="detailVerbs(${test.test.testId})">view</button></TD>

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
