//import Form from './components/Form'    //rendering시마다 useEffect 함수 호출
//import Form from './components/Form2'     //email값이 변경될 때만 useEffect 함수 호출
import Form from './components/Form3'     //component가 mount될 때 useEffect 함수 호출

import {View, StyleSheet} from 'react-native'

//이름, 이메일 Form
export default function AppHook04() {
    return(
      <View style={styles.container}>
          <Form />  
      </View>
    )    
}

const styles = StyleSheet.create(
  {
    container: {
      flex: 1,
      backgroundColor: '#fff',
      alignItems: 'center',
      justifyContent: 'center',
    },

  }
)