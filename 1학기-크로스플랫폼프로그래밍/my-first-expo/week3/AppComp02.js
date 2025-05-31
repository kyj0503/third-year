import {View, Text, StyleSheet, StatusBar} from 'react-native';

//JavaScript 변수
export default function AppComp02() {

  const name = 'hongildong';

    return(<View style={styles.container}>    
      <Text style={styles.text}>My name is {name}</Text>
      {/* <StatusBar style='auto' /> */}

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