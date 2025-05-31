import {View, Text, StyleSheet, StatusBar, Button} from 'react-native';
import EventButton from './components/EventButton';

//Event
export default function AppComp07() {

    return(<View style={ styles.container}>          
      <EventButton />     
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