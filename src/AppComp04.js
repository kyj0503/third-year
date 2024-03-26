import {View, Text, StyleSheet, StatusBar, Button} from 'react-native';
import MyButton from './components/MyButton4';

//Default Props
export default function AppComp04() {

    return(<View style={ styles.container}>

      <Text style={styles.text}>My Button - Children Props</Text>
      <MyButton />
      <MyButton title='Button' />
      <MyButton title='Button'>Children Props</MyButton>
      <MyButton title='Button'>
        자식 Props
        <Text>자손 Props</Text>
      </MyButton>
      
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