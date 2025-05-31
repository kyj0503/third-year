import {View, Text, StyleSheet, StatusBar, Button} from 'react-native';
import MyButton from './components/MyButton2';

//props 기본
export default function AppComp02() {

    return(<View style={ styles.container}>

      <Text style={styles.text}>MyButton - Props Basic</Text>
      
      <MyButton title='Button'/>
      
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
      marginBottom: 10
    }
  });