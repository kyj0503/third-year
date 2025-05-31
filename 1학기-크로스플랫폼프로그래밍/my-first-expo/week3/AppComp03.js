import {View, Text, StyleSheet, StatusBar} from 'react-native';

//JavaScript 조건문 (func3.js 참조)
export default function AppComp03() {

  const name = 'ext';

    return(<View style={styles.container}>
      <Text style={styles.text}>
        {
          (() => {
            if(name === 'hanbit') return 'My name is Hanbit';
            else if(name === 'hongildong') return 'My name is Hongildong';
            else return 'My name is React Native'
          })()
        }
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