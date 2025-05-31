import { useState } from 'react';
import {View, Text, StyleSheet, StatusBar, Button, TextInput} from 'react-native';


//onChange Event - textInput 박스(event객체)
export default function AppComp08() {

    const [text, setText] = useState('');    

    return(<View style={ styles.container}>
      <Text style={ styles.textStyle}>{text}</Text>
      <TextInput 
        style = { styles.textInputStyle }
        placeholder='Enter a text...'
        onChange={(event) => {
          console.log(event);
          setText(event.nativeEvent.text);
        }}
      
      />

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
    textStyle: {
      fontSize: 30,
      fontWeight: 'bold',
      margin: 10
    },
    textInputStyle: {
      fontSize: 20,
      padding: 10,
      borderWidth: 1
    }
  });