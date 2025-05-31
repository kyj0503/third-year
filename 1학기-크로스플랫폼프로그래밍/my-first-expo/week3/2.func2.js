
let rname = 'hanbit';

function func1(){

    if(rname === 'hanbit') 
        return 'My name is Hanbit';
    else if(rname === 'hongildong') 
        return 'My name is Hongildong';
    else 
        return 'My name is React Native'
}

console.log("func1(): ", func1());

//익명함수

const func2 = function() {
    if(rname === 'hanbit') 
        return 'My name is Hanbit';
    else if(rname === 'hongildong') 
        return 'My name is Hongildong';
    else 
        return 'My name is React Native'
}
console.log("func2(): ", func2());

const func3 = () => {
    if(rname === 'hanbit') 
        return 'My name is Hanbit';
    else if(rname === 'hongildong') 
        return 'My name is Hongildong';
    else 
        return 'My name is React Native'
}
console.log("func3(): ", func3());

console.log("arrow func: ",
(() => {
    if(rname === 'hanbit') 
        return 'My name is Hanbit';
    else if(rname === 'hongildong') 
        return 'My name is Hongildong';
    else 
        return 'My name is React Native'
})()
);

/* (() => {
    if(name === 'hanbit') return 'My name is Hanbit';
    else if(name === 'hongildong') return 'My name is Hongildong';
    else return 'My name is React Native'
  })()
 */
