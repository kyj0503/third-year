//high-order function
//인자나 반환값을 함수로 갖는 함수

function f(x) {
    return x * 3;
}

function twice(f, x) {
    return f(f(x))
}

console.log("twice(f, 7): ", twice(f, 7));      //21*3

//Hello World...!123

function outerFunc() {
    let output = 'Hello World...!';

    return function(msg) {
        return output + msg;
    }
}

console.log( outerFunc()(123));