/** week3 App.js
import AppComp01 from './week3/AppComp01'  //<View>, <Fragment>, <>
import AppComp02 from './week3/AppComp02'  //변수
import AppComp03 from './week3/AppComp03'  //조건문
import AppComp04 from './week3/AppComp04'  //삼항연산자
import AppComp05 from './week3/AppComp05'  //AND, OR 연산자
import AppComp06 from './week3/AppComp06'  //styling
import AppComp07 from './week3/AppComp07'  //Button
import AppComp08 from './week3/AppComp08'  //Custom Button:Touchable Opacity

export default function App() {
  return <AppComp01 />  
}
*/

/** week4 App.js
import AppComp01 from "./week4/AppComp01";  //Custom button - Pressable
import AppComp02 from "./week4/AppComp02";  //props 기본//props 기본
import AppComp03 from "./week4/AppComp03";  //children props
import AppComp04 from "./week4/AppComp04";  //default props
import AppComp05 from "./week4/AppComp05";  //Counter1(useState) - single counter
import AppComp06 from "./week4/AppComp06";  //Counter2(useState)  - double counter
import AppComp07 from "./week4/AppComp07";  //Event Button - PressIn, PressOut, Press, LongPress, delayLongPress
import AppComp08 from "./week4/AppComp08";  //onChange 이벤트 - textInput(event 객체)
import AppComp09 from "./week4/AppComp09";  //onChangeText 이벤트 - textInput (text 객체)
import AppComp10 from "./week4/AppComp10";  //Pressable Component

export default function App() {
    return <AppComp10 />
}
*/

/** week5 App.js
import FlexApp01 from "./week5/basic/FlexApp01";  //외부 스타일링
import AppStyle01 from "./week5/AppStyle01";  //flex와 범위
import AppStyle02 from "./week5/AppStyle02";  //정렬(justifyContent)
import AppStyle03 from "./week5/AppStyle03";  //정렬(alignItem)
import AppStyle04 from "./week5/AppStyle04";  //그림자

export default function App() {
  return (
    <AppStyle01 />    
  );
}
*/

/** week6 App.js
import AppStyle05 from "./week6/AppStyle05";  //Styled Component 사용법 - example1
import AppStyle06 from "./week6/AppStyle06";  //스타일 적용하기 - example2(Button, Container)
import AppStyle07 from "./week6/AppStyle07";  //Styled 적용 - react native style sheet 사용
import AppStyle08 from "./week6/AppStyle08";  //버튼 배경색 바꾸기(native stylesheet사용)
import AppStyle09 from "./week6/AppStyle09";  //버튼 배경색 바꾸기(styled component 사용, props)
import AppStyle10 from "./week6/AppStyle10";  //InputTextComponent without attribute
import AppStyle11 from "./week6/AppStyle11";  //InputTextComponent with attribute
import AppStyle12 from "./week6/AppStyle12";  //ThemeProvider 스타일 - purple, blue
import AppStyle13 from "./week6/AppStyle13";  

export default function App() {
  return (
    <AppStyle06 />    
  );
}
*/

/** week7 App.js
import AppHook01 from "./week7/AppHook01";  //Button Component
import AppHook02 from "./week7/AppHook02";  //useStae 사용하기
import AppHook03 from "./week7/AppHook03";  //Setter 함수

export default function App() {
  return(
      <AppHook01 />
  );
}
*/

/** Mission1
import { View, StyleSheet } from "react-native";
import Mission01 from "./mission/Mission01"

export default function App() {
    return <View style={styles.container}>
      <Mission01/>
    </View>
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    top:30,
    backgroundColor: '#fff',
  },
});
*/

/** Mission2

//Naming 카드를 활용하여 next 버튼을 누를 떄마다 카드가 Rotation 되도록 작성
//이름: 아이유, 김범수, 성시경
//이름의 fontSize는 조절가능하도록 파라미터(props)로 처리

*/

// Mission03




