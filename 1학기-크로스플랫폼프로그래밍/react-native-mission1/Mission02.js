import { useState } from 'react';
import { Button, StyleSheet, Text, View } from 'react-native';

//Naming카드를 활용하여 버튼을 누를 때마다 카드가 Rotation되도록 작성
//props로 숫자를 전달할 때는 JSX문법을 사용하여 전달 e.g) fontSize = {50}
//fontSize = "50" 으로 전달하면 문자열로 받아서 "fontSize: 숫자" 문법을 맞추지 못한다.

export default function Mission02() {

  const names = ["아이유", "김범수", "성시경"]

  const [index, setIndex] = useState(0)  

  return <View>
  <NamingCard name={names[index % names.length]} fontSize={50} />   {/* 2.fontSize를 부모에서 전달 */}
    <Button  title="Next" onPress={()=>setIndex(index + 1)}   />  
  </View>
}

const NamingCard = ({name, fontSize = 20}) => {   /* 1. 필수parameter가 아닌 값은 default값으로 할당 */
  return <View>
    <Text style={   {fontSize: fontSize, fontWeight: 'bold'}   }>{name}</Text>
  </View>
}