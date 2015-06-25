/**
 * Created by serg on 28.04.15.
 */
function checkForEmpty(classNameInput, classNameError ){
    var x=0;
    var input = document.getElementsByClassName(classNameInput);
    var massive = document.getElementsByClassName(classNameError)

    for(i=0;i<input.length;i++){
        if(input[i].value==undefined || input[i].value =='') {
            massive[i].innerHTML = ('Can not be empty');
            x++;
        } else{
            massive[i].innerHTML = ('');
        }
    }

    if(x!=0){
        return false;
    }
    return true;
}

function toHome(){
    window.location = "/homePage.html";
}

function logOut(){
    window.location = "/homePage.html?login=Guest&pass=guest";
}

function changePage(newPageAddress){
    window.location = newPageAddress;
}


function onActive(elemId){
    var label = document.getElementById(elemId);
    $(label).css('background', 'red');
}

function desActive(elemId){
    var label = document.getElementById(elemId);
    $(label).css('background', 'blue');
}

function checkTextLong(nodeId, nodeIdError, minCntSimbol){
    var nodeError = document.getElementById(nodeIdError);
    var nodeText = document.getElementById(nodeId).value;
    if(nodeText.length<minCntSimbol){
        nodeError.innerHTML='need minimum ' + minCntSimbol + ' symbol';
    } else{
        nodeError.innerHTML = '';
    }
}
function checkEquals(nodeIdEthalon, nodeIdTest, nodeIdError){
    var nodeError = document.getElementById(nodeIdError);

    var nodeEthalon = document.getElementById(nodeIdEthalon).value;
    var nodeTest = document.getElementById(nodeIdTest).value;

    if(nodeEthalon!=nodeTest){
        nodeError.innerHTML='must be equals';
    } else{
        nodeError.innerHTML='';
    }
}

function onActiveMenu(elemId){
    var label = document.getElementById(elemId);
    $(label).css('background', 'lightgreen');
    $(label).css('border-radius', '10px');
}

function desActiveMenu(elemId){
    var label = document.getElementById(elemId);
    $(label).css('background', 'lightgoldenrodyellow');
    $(label).css('border-radius', '0px');
}
function rangeValue(nodeIdRange, nodeIdView){
    document.getElementById(nodeIdView).innerHTML=document.getElementById(nodeIdRange).value;
}
function disAbleElement(nodeId){

    document.getElementById(nodeId).disabled=true;
}

$(document).ready(function(){
    //alert('hello');
    var nodes = document.getElementsByClassName('Horizontal');
    var node = nodes[0];
    $(node).append($('<div class="backToHome" onclick="toHome()">'+'</div>'));
    $(node).append($('<div class="logOut" onclick="logOut()"></div>'));


    var results = document.getElementsByClassName('resultTest');
    for(i=0;i<results.length;i++){
        var red= 0;
        var green = 0;

        if(results[i].innerHTML<0.7){
            red = 185 + 100*(results[i].innerHTML*2);
        } else{
            green =230 - 100*((results[i].innerHTML-0.7)*2);
        }
        red =  parseInt(red);
        green= parseInt(green);
        $(results[i]).css('color', 'rgb('+red+','+green+',0)');
        results[i].innerHTML = (results[i].innerHTML*100).toFixed(0) + ' %';
    }


    var effectiveness = document.getElementsByClassName('effectiveness');
    var effectivenessIsNull = document.getElementsByClassName('effectivenessIsNull');
    var countOfColumn=effectiveness.length/effectivenessIsNull.length;

        for(i=0;i<effectivenessIsNull.length;i++){
            if(effectivenessIsNull[i].innerHTML !=''){
                for(j=0;j<countOfColumn;j++){
                    var green = 0;
                    var red= 0;
                    if(effectiveness[i*countOfColumn+j].innerHTML<0.7 ){
                        red = 185 + 100*(effectiveness[i*countOfColumn+j].innerHTML*2);
                    } else{
                        green =230 - 100*((effectiveness[i*countOfColumn+j].innerHTML-0.7)*2);
                    }
                    red =  parseInt(red);
                    green= parseInt(green);
                    $(effectiveness[i*countOfColumn+j]).css('color', 'rgb('+red+','+green+',0)');
                effectiveness[i*countOfColumn+j].innerHTML = (effectiveness[i*countOfColumn+j].innerHTML*100).toFixed(0)
                + ' %';
            }
        }
    }
});
