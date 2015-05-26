/**
 * Created by serg on 19.04.15.
 */
console.log("hello js");
var masiv = [1,2,3,4,5,6,7,8,9];
for ( i=0;i<masiv.length;i++){
    if(masiv[i]%2==0){
        console.log(masiv[i]);
    }
}
function funk(param){
    var name =  prompt('What is you name?');
    alert('Hello, ' + name);
}


function check(){
    var name = document.getElementById('1').val;
    var password = document.getElementById('2').val;
    var div1 = document.getElementById('3');
    alert("fignya")
    if(name==undefined ) {

        div1.innerHTML('input login');
        return false;
    }
    return false;
}




