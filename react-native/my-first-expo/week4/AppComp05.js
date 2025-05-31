import {View, Text, StyleSheet, StatusBar, Button} from 'react-native';
import Counter1 from './components/Counter1';

//Counter
export default function AppComp05() {

    return(<View style={ styles.container}>         
      <Counter1 />
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