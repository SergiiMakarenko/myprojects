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
      <title>Edit verb</title>
      <script src="js/jquery-1.11.2.min.js"></script>
      <script src="js/english.js"></script>
            <script>
                function verbRead(){
                    var verbId = document.getElementById('selectVerb').value;
                    var infinitive = document.getElementById('infinitive');
                    var pastSimple = document.getElementById('pastSimple');
                    var pastParticiple = document.getElementById('pastParticiple');

                    $.ajax({
                        datatype: 'json',
                        url: '/verbAjax?verbId='+verbId,
                        success: function(data){
                            infinitive.value = data.infinitive;
                            pastSimple.value=data.pastSimple;
                            pastParticiple.value=data.pastParticiple;
                        },
                        error: function(a,b,c){
                            alert(a+b+c);
                        }
                    })
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
      <form class="formWork" onsubmit="return checkForEmpty('verbEdit','error')" action="/verbEdit.html" method="post">

          <div class="promptText"> Choice verb*: </div>
          <select id="selectVerb" class="verbEdit" name="verbId" onchange="verbRead()" >
              <option></option>
              <c:forEach var="verb" items="${verbList}">
                  <c:if test="${verb.verbId!=verbId}">
                      <option value="${verb.verbId}">${verb.infinitive}</option>
                  </c:if>
                  <c:if test="${verb.verbId==verbId}">
                      <option selected="selected" value="${verb.verbId}">${verb.infinitive}</option>
                  </c:if>
              </c:forEach>
          </select>
          <div id="erVerb" class="error" ></div>
          <div class="promptText">Edit infinitive*: </div>
          <input id="infinitive" class="verbEdit" type="text" name="infinitive" value="${infinitive}">
          <div id="erLogin" class="error" ></div>
          <div class="promptText">Edit pastSimple*: </div>
          <input id="pastSimple" type="text" name="pastSimple" value="${pastSimple}">
          <div id="erPastSimple" class="error" ></div >
          <div class="promptText">Edit pastParticiple*: </div>
          <input id="pastParticiple" type="text" name="pastParticiple" value="${pastParticiple}">
          <div id="erPastParticiple" class="error" ></div>
          <BR>
          <input type="submit" value="Update">

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
