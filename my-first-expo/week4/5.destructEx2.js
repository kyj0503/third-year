//객체에 대한 구조분할

const data = {id: 123, rname: "홍길동", age: 45, dept: 'engineering'};

const {id, age, dept, rname} = data;

console.log("name:", rname);        //name: 홍길동
console.log("id:", id);             //id: 123
console.log("age:", age);           //age: 45
console.log("dept:", dept);         //dept: engineering

const {id2, ...rest2} = {id2:97, rname: 'hongildong', age: 20};     
console.log("id2: ", id2);          //id2: 97
console.log("rest2: ", rest2);      //rest2: {rname: 'hongildong', age: 20}

const {...rest3} = {id: 20, rname: 'kim', age: 50};
console.log("rest3: ", rest3);      //rest3: {id: 20, rname: 'kim', age: 50}