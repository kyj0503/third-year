import {View, Text, StyleSheet, StatusBar, Button} from 'react-native';
import Counter2 from './components/Counter2';

//Counter2 - double Counter
export default function AppComp06() {

    return(<View style={ styles.container}>          
      <Counter2 />      
      <StatusBar style='auto' />
    </View>);
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff',
      alignItems: 'center',
      justifyContent: 'center',
    }
  });