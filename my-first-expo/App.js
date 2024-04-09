// App.js 기본값
/*
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';

export default function App() {
  return (
    <View style={styles.container}>
      <Text>Open up App.js to start working on your app!</Text>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
*/

// week3 App.js
/*
import AppComp01 from './week3/AppComp01'   //<View>, <Fragment>, <>
import AppComp02 from './week3/AppComp02'   //변수
import AppComp03 from './week3/AppComp03'   //조건문
import AppComp04 from './week3/AppComp04'   //삼항연산자
import AppComp05 from './week3/AppComp05'   //AND, OR 연산자
import AppComp06 from './week3/AppComp06'   //styling
import AppComp07 from './week3/AppComp07'   //Button
import AppComp08 from './week3/AppComp08'  //Custom Button:Touchable Opacity

export default function App() {
  return <AppComp01 />  
}
*/

// week4 App.js
/*
//Custom button - Pressable
import AppComp01 from "./week4/AppComp01";  

//props 기본
import AppComp02 from "./week4/AppComp02";

//children props
import AppComp03 from "./week4/AppComp03";

//default props
import AppComp04 from "./week4/AppComp04";


//Counter1(useState) - single counter
import AppComp05 from "./week4/AppComp05";

//Counter2(useState)  - double counter
import AppComp06 from "./week4/AppComp06";

//Event Button - PressIn, PressOut, Press, LongPress, delayLongPress
import AppComp07 from "./week4/AppComp07";

//onChange 이벤트 - textInput(event 객체)
import AppComp08 from "./week4/AppComp08";

//onChangeText 이벤트 - textInput (text 객체)
import AppComp09 from "./week4/AppComp09";

//Pressable Component
import AppComp10 from "./week4/AppComp10";


export default function App() {
    return <AppComp10 />
}
*/

// week5 App.js
/*
//외부 스타일링
import FlexApp01 from "./week5/basic/FlexApp01";

//flex와 범위
import AppStyle01 from "./week5/AppStyle01";

//정렬(justifyContent)
import AppStyle02 from "./week5/AppStyle02";

//정렬(alignItem)
import AppStyle03 from "./week5/AppStyle03";

//그림자
import AppStyle04 from "./week5/AppStyle04";

export default function App() {
  return (
    <AppStyle01 />    
  );
}
*/

// week6 App.js
//Styled Component 사용법 - example1
import AppStyle05 from "./week6/AppStyle05";

//스타일 적용하기 - example2(Button, Container)
import AppStyle06 from "./week6/AppStyle06";

//Styled 적용 - react native style sheet 사용
import AppStyle07 from "./week6/AppStyle07";

//버튼 배경색 바꾸기(native stylesheet사용)
import AppStyle08 from "./week6/AppStyle08";

//버튼 배경색 바꾸기(styled component 사용, props)
import AppStyle09 from "./week6/AppStyle09";

//InputTextComponent without attribute
import AppStyle10 from "./week6/AppStyle10";

//InputTextComponent with attribute
import AppStyle11 from "./week6/AppStyle11";

//ThemeProvider 스타일 - purple, blue
import AppStyle12 from "./week6/AppStyle12";

//ThemaProvider 스타일2 - light, dark
import AppStyle13 from "./week6/AppStyle13";

export default function App() {
  return (
    <AppStyle13 />    
  );
}

