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
      <title>Pazzle words</title>
      <script src="js/jquery-1.11.2.min.js"></script>
      <script src="js/english.js"></script>
            <script>
                $(document).ready(function() {
                    if("${userWord}"!=''){
                        document.getElementById("${userWord}").checked=true;
                    } else{
                        document.getElementById('onlyMy').checked=true;
                    }

                    if("${TranslateType}"!=''){
                        document.getElementById("${TranslateType}").checked=true;
                    } else{
                        document.getElementById('UkrToEng').checked=true;
                    }


                   loadLettersForms();

                });

                function loadLettersForms(){
                    var mainBlock = document.getElementById('mainBlock');
                    $(mainBlock).append($('<h1 id ="wordToTrans" >' + '</h1>').css({'display':'inline-block'}));
                    $(mainBlock).append($('<h3 id ="languagePart">' + '</h3>').css({'display':'inline-block'}));

                    $(mainBlock).append($('<div class="divLetterTableRes" id="tableCheck">'+'</div>'));
                    var tableCheck = document.getElementById('tableCheck');
                    for(i=0;i<1;i++){
                        $(tableCheck).append($('<div class="divLetterRow" id="rowCh'+i+'">' +'</div>'));
                    }

                    for(i=0;i<20;i++){
                        var row = document.getElementById('rowCh0');
                        $(row).append($('<div class="divLetterRes" onclick="delFromPuzzle(this)" ' +
                        'id="letterRes'+i+'">'+ '' + '</div>').css({'cursor': 's-resize'}));
                    }

                    $(mainBlock).append($('<BR>'+'</BR>'));

                    $(mainBlock).append($('<div class="divLetterTable" id="tableLet">'+'</div>'));
                    var tableLet = document.getElementById('tableLet');
                    for(i=0;i<2;i++){
                    $(tableLet).append($('<div class="divLetterRow" id="row'+i+'">' +'</div>'));
                    }

                    for(i=0;i<20;i++){
                        var row = document.getElementById('row'+(1+(i/20).toFixed(0)-10));
                        $(row).append($('<div class="divLetter" onclick="doPuzzle(this)" ' +
                        'id="letter'+i+'">'+ '' + '</div>'));
                    }
                    $(mainBlock).append($('<BR>'+'</BR>'));
                    $(mainBlock).append($('<Button onclick="nextWord()" class="buttonForm">'+ 'Give up' + '</Button>'));

                    $(mainBlock).append($('<BR>'+'</BR>'));
                    $(mainBlock).append($('<BR>'+'</BR>'));

                    $(mainBlock).append($('<div class="divLetterTableRes" id="tableResult">'+'</div>'));
                    $(tableResult).css({'width': '150px','border-spacing': '1px','height': '10px',
                        'border-radius': '1px'});

                    for(i=0;i<1;i++){
                        $(tableResult).append($('<div class="divLetterRow" id="rowResult'+i+'">' +'</div>').css(
                                { 'height': '10px'}));
                    }

                    var i = 0;
                    <c:forEach var="words" items="${wordsList}">
                        var row = document.getElementById('rowResult0');
                        $(row).append($('<div class="divLetterRes" id="wordResult'+i+ '">'+ '' + '</div>').css(
                                {'background': 'grey','width': '10px'}));
                    i++;
                    </c:forEach>

                    addFirstWord();
                }

                function addFirstWord(){
                    var wordsTransTo = [];
                    var wordsTransFrom = [];
                    var languagePart=[];
                    var type = document.getElementById('EngToUkr').checked;
                    <c:forEach var="word" items="${wordsList}">
                    languagePart.push("${word.word.category.categoryName}");
                    if(type){
                    wordsTransTo.push("${word.word.ukrainianWord}");
                    wordsTransFrom.push("${word.word.englishWord}");
                    } else{
                        wordsTransFrom.push("${word.word.ukrainianWord}");
                        wordsTransTo.push("${word.word.englishWord}");
                    }
                    </c:forEach>

                    var firstWord='';
                    var firstWordToTrans='';
                    var langPart= '';
                    if(wordsTransTo.length>0){
                        firstWord=wordsTransTo[0];
                        firstWordToTrans=wordsTransFrom[0];
                        langPart='  (' +languagePart[0] + ')'
                    }
                    randomWordFill(firstWord,20);

                    document.getElementById('wordToTrans').innerHTML=firstWordToTrans;
                    document.getElementById('languagePart').innerHTML=  langPart;
            }

            function doPuzzle(node){
                var userAnswer='';
                if(node.innerHTML!=''){
                for(i=0;i<20;i++){
                    var letterRes =document.getElementById('letterRes' + i);
                    if(letterRes.innerHTML=='') {
                        letterRes.innerHTML=node.innerHTML;
                        node.innerHTML='';
                    }
                    userAnswer=userAnswer+letterRes.innerHTML;
                }
                }

                var checkLetters = 0;
                for(i = 0; i<20;i++){
                    var letter =document.getElementById('letter' + i);
                    if(letter.innerHTML!=''){
                        checkLetters=1;
                    }
                }
                if(checkLetters==0){
                    nextWord(userAnswer);
                }
            }

            function delFromPuzzle(node){
                if(node.innerHTML!='') {
                    var let = [4,14,5,15,3,13,6,16,2,12,7,17,1,11,8,18,0,19];
                    for(i=0;i<20;i++){
                        var letter =document.getElementById('letter' + let[i]);
                            if(letter.innerHTML==''){
                                letter.innerHTML=node.innerHTML;
                                node.innerHTML='';
                            }
                        }
                    }
                }

                function nextWord (userAnswer){

                    var wordsTransTo = [];
                    var wordsTransFrom = [];
                    var languagePart=[];
                    var type = document.getElementById('EngToUkr').checked;
                    <c:forEach var="word" items="${wordsList}">
                    languagePart.push("${word.word.category.categoryName}");
                    if(type){
                        wordsTransTo.push("${word.word.ukrainianWord}");
                        wordsTransFrom.push("${word.word.englishWord}");
                    } else{
                        wordsTransFrom.push("${word.word.ukrainianWord}");
                        wordsTransTo.push("${word.word.englishWord}");
                    }
                    </c:forEach>

                    for(i = 0; i<20;i++){
                        document.getElementById('letter' + i).innerHTML='';
                    }

                    for(i=0; i < wordsTransFrom.length;i++){
                        if(wordsTransFrom[i] == document.getElementById('wordToTrans').innerHTML){

                            for(k=0;k<20;k++){
                                document.getElementById('letterRes' + k).innerHTML='';
                            }
                            var result = document.getElementById('wordResult'+i);

                            if(userAnswer==wordsTransTo[i]){
                                $(result).css({'background': 'green'});
                            } else{
                                alert('remember: '+ wordsTransFrom[i] + ' = ' + wordsTransTo[i]);
                                $(result).css({'background': 'red'});
                            }

                            if(i<wordsTransFrom.length-1){
                            var nextWord=wordsTransTo[i+1];
                            randomWordFill(nextWord,20);
                            document.getElementById('wordToTrans').innerHTML=wordsTransFrom[i+1];
                                document.getElementById('languagePart').innerHTML='(' + languagePart[i+1] + ')';
                            i=20;
                            } else{
                                document.getElementById('wordToTrans').innerHTML='';
                                document.getElementById('languagePart').innerHTML='';
                                alert('The end');

                            }
                        }
                    }
                }

                function randomWordFill(word, maxCntLetter){
                    var orderIndex = [4,14,5,15,3,13,6,16,2,12,7,17,1,11,8,18,0,19];
                    var randomIndex=[];
                    var wordIndex=[];
                    for(z=0;z<word.length;z++){
                        wordIndex.push(z);
                    }
                    var lengthCurrent=word.length;

                    for(z=0;z<word.length;z++){
                        var random=(lengthCurrent*Math.random()).toFixed(0)*1;
                        if(random==lengthCurrent){
                            random--;
                        }
                        randomIndex.push(wordIndex[random]);
                        wordIndex[random]=wordIndex[lengthCurrent-1];
                        lengthCurrent--;
                    }

                    for(z=0; z < word.length;z++){
                        document.getElementById('letter'+orderIndex[z]).innerHTML=
                                word.substring(randomIndex[z],randomIndex[z]+1);
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

      <form id="paramForm" class="formWork" action="/wordPuzzle.html" method="post">
          <div class="divSubMenu"> <a > Choice amount words to test: </a></div>
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
              <input type="radio" name="TranslateType" id="EngToUkr" value="EngToUkr">English-Ukrainian
          </div>
          <div class="divSubMenu">
              <input type="radio" name="TranslateType" id="UkrToEng" value="UkrToEng">Ukrainian-English
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

          <input id="start" type="submit" value="Start">

      </form>
  </div>
  <div id="rightColumn">

  </div>
  <div class="Horizontal">

  </div>

  </body>

</html>
