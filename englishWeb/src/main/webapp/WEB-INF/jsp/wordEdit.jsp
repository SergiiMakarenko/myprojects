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
<%--<%@ page contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1" language="java" %>--%>


<html>
  <head>

      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Edit word</title>
      <script src="js/jquery-1.11.2.min.js"></script>
      <script src="js/english.js"></script>
            <script>
                function wordRead(){
                    var categoryId = document.getElementById('selectCategory');
                    var wordId = document.getElementById('selectWord').value;

                    var english = document.getElementById('english');
                    var ukrainian = document.getElementById('ukrainian');
                    var transcription = document.getElementById('transcription');

                    $.ajax({
                        datatype: 'json',
                        url: '/categoryAjax?wordId='+wordId,
                        success: function(data){
                            categoryId.value = data.categoryId;
                        },
                        error: function(a,b,c){
                            alert(a+b+c);
                        }
                    })

                    $.ajax({
                        datatype: 'json',
                        url: '/wordAjax?wordId='+wordId,
                        method: 'POST',
                        success: function(data){
                            english.value=data.englishWord;
                            ukrainian.value=data.ukrainianWord;
                            transcription.value=data.transcription;

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

      <form class="formWork" onsubmit="return checkForEmpty('inputParam','error')" action="/wordEdit.html" method="post">
          <div class="promptText"> Choice word*: </div>
          <select id="selectWord" class="inputParam" name="wordId" onchange="wordRead()" >
              <option></option>
              <c:forEach var="word" items="${wordList}">
                  <c:if test="${word.wordId!=wordId}">
                      <option value="${word.wordId}">${word.englishWord}=${word.ukrainianWord}</option>
                  </c:if>
                  <c:if test="${word.wordId==wordId}">
                      <option selected="selected" value="${word.wordId}">${word.englishWord}=${word.ukrainianWord}
                      </option>
                  </c:if>
              </c:forEach>
          </select>
          <div id="erWord" class="error" ></div>
          <div class="promptText">Edit english*: </div>
          <input id="english" class="inputParam" type="text" name="english" value="${english}">
          <div id="erEnglish" class="error" ></div>
          <div class="promptText">Edit ukrainian*: </div>
          <input id="ukrainian" class="inputParam" type="text" name="ukrainian" value="${ukrainian}">
          <div id="erUkrainian" class="error" ></div >
          <div class="promptText">Edit transcription: </div>
          <input id="transcription" type="text" name="transcription"
                 value="${transcription}">


          <div class="promptText"> Choice category*: </div>
          <select id="selectCategory" class="inputParam" name="categoryId">
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
