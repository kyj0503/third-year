function f1() {
    return [1, 2, 3];
}

const x = f1();
console.log("x: ", x);      //x: [1,2,3]

const [x1, x2] = f1();
console.log("x1:", x1, ", x2:", x2);       //x1:1, x2:2


const [y1, ...rest] = f1();         //spread연산자
console.log("y1:", y1);             //y1: 1
console.log("rest:", rest);         //rest: [2,3] */