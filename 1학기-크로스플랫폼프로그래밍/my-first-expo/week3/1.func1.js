//함수 선언문
function add1(x, y){
    return x + y;
}
console.log(add1(3, 4));

//함수 표현식
var add2 = function(x, y) {
    return x + y;
}
console.log(add2(3, 4));

//Function 생성자 함수
var add3 = new Function('x', 'y', 'return x + y');
console.log(add3(3, 4)); 


//화살표 함수
var add4 = (x, y) => {
    return x + y;
}

var add5 = (x, y) => (x+y);
var add6 = (x, y) => x+y; 

console.log('add4: ', add4(3,4));
console.log('add5: ', add5(3,4));
console.log('add6: ', add6(3,4));
