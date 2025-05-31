import {View, Text, StyleSheet, StatusBar} from 'react-native';

//삼항연산자
export default function AppComp04() {

  const name = 'hongildong';

    return(<View style={styles.container}>
      <Text style={styles.text}>
      {/* My name is {name === 'hanbit'? 'hanbit': 'React Native' } */}
      My name is {name === 'hanbit'? 'hanbit': (name === 'hongildong'? 'hongildong' : 'React Native') }        
      </Text>
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