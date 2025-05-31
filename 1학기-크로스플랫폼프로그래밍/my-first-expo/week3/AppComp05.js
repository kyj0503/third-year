import {View, Text, StyleSheet, StatusBar} from 'react-native';

//AND 연산자와 OR 연산자
/*
  A && B : A가 참이면 B이다.
  A && B : A가 거짓이면 B도 아니다.
  A || B: A가 참이면 B는 거짓이다.
  A || B: A가 거짓이면 B이다.
*/
export default function AppComp05() {

  const name = '홍길동';

    return(<View style={styles.container}>
      {name === '홍길동' && (<Text style={styles.text}>My name is {name}</Text>)} {/* print */}
      {name !== '홍길동' && (<Text style={styles.text}>My name is not {name}</Text>)} {/* 거짓 */}
      {name === 'shin' || (<Text style={styles.text}>My name is {name}</Text>)} {/* print */}
      {name !== 'shin' || (<Text style={styles.text}>My name is not {name}</Text>)} {/* 거짓 */}
      <StatusBar style='auto' />
    </View>);
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff',
      alignItems: 'center',
      justifyContent: 'center',
    },
    text: {
      fontSize: 30,
    }
  });