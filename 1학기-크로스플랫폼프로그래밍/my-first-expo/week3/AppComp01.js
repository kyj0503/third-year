import { Fragment } from 'react';
import {View, Text, StyleSheet, StatusBar} from 'react-native';

//주석: ctrl + shift + /
//<View>, <Fragment>, <>
export default function AppComp01() {

    return<>
        <Text /* Text Comment */ >Hello React Native~!</Text>
        <Text //Text Comment 
        >Hello React Native2~!</Text>
        <Text>{/* Hello */} React Native~!</Text>

        {/* <StatusBar barStyle="default" backgroundColor="orange" /> */}

    </>
        
}
const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff',
      alignItems: 'center',
      justifyContent: 'center',
    },
  });