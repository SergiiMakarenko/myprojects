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
                function userRead(){
                    var userId = document.getElementById('selectUser').value;
                    var userLogin = document.getElementById('loginNew');
                    var userPass = document.getElementById('pass');
                    var userPassConfirm = document.getElementById('passConfirm');
                    var userRole = document.getElementById('selectRole');
                    $.ajax({
                        datatype: 'json',
                        url: '/userAjax?userId='+userId,
                        success: function(data){
                            userLogin.value = data.userLogin;
                            userPass.value=data.userPassword;
                            userPassConfirm.value=data.userPassword;
                        },
                        error: function(a,b,c){
                            alert(a+b+c);
                        }
                    })

                    $.ajax({
                        datatype: 'json',
                        url: '/roleAjax?userId='+userId,
                        success: function(data){
                            userRole.value = data.roleId;
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
      <form class="formWork" onsubmit="return checkForEmpty('editUser','error')" action="/userEdit.html" method="post">

          <div class="promptText"> Choice user*: </div>
          <select id="selectUser" class="editUser" name="userId" onchange="userRead()" >
              <option></option>
              <c:forEach var="user" items="${userList}">
                  <c:if test="${user.userId!=userId}">
                      <option id="${user.userId}" value="${user.userId}">${user.userLogin}</option>
                  </c:if>
                  <c:if test="${user.userId==userId}">
                      <option selected="selected" id="${user.userId}" value="${user.userId}">${user.userLogin}</option>
                  </c:if>
              </c:forEach>
          </select>
          <div id="erUser" class="error" ></div>
          <div class="promptText">Edit user login*: </div>
          <input id="loginNew" class="editUser" type="text" name="userLogin" value="${userLogin}">
          <div id="erLogin" class="error" ></div>
          <div class="promptText">Edit user password*: </div>
          <input id="pass" class="editUser" type="text" name="userPass" onchange="checkPass()" value="${userPass}">
          <div id="erPass" class="error" ></div >
          <div class="promptText">Confirm user password*: </div>
          <input id="passConfirm" class="editUser" type="text" name="userPassConfirm"
                 onchange="checkConfirm()" value="${userPassConfirm}"> <div id="erConfirm" class="error" ></div >

          <div class="promptText"> Choice role*: </div>
          <select id="selectRole" class="editUser" name="roleId" >
              <option></option>
              <c:forEach var="role" items="${roleList}">
                  <c:if test="${role.roleId==roleId}">
                      <option selected="selected" value="${role.roleId}">${role.roleName}</option>
                  </c:if>
                  <c:if test="${role.roleId!=roleId}">
                      <option value="${role.roleId}">${role.roleName}</option>
                  </c:if>
              </c:forEach>
          </select>

          <div id="erRole" class="error" ></div >
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
