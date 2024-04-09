import { useState } from 'react';
import {View, Text, StyleSheet, StatusBar, Pressable} from 'react-native';

//HitRect(클릭 시작 범위), PressRect(슬라이딩 가능 범위)
const Button = (props) => {
  return<View>
    <Pressable
      style = {styles.btn}
      onPressIn = {() => console.log('Press In!')}
      onPressOut={ () => console.log('Press Out!')}
      onPress={ () => console.log('Press!')}
      onLongPress={ () => console.log('Long Press!')}
      delayLongPress={ 3000 }
      pressRetentionOffset={{bottom: 10, left: 50, right: 50, top: 50}} /* HitRect */
      hitSlop={50}
    >
      <Text style={styles.textStyle}>{props.title}</Text>
    </Pressable>
  </View>
}

//Pressable Component
export default function AppComp10() {    

    return(<View style={ styles.container}>
      <Button title = 'Pressable'/>
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
    btn: {
      backgroundColor: '#1abc9c',
      padding: 10,
      margin: 10,
      borderRadius: 8
    }
  });