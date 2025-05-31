import {View, Text, StyleSheet} from 'react-native'
import MyButton from './MyButton5'
import { useState } from 'react'

//single counter
export default function Counter1() {

    const [count, setCount] = useState(0);    

    return<View style={{alignItems: 'center'}}>
        <Text style={{fontSize: 30, margin: 10}}>{count}</Text>
        <MyButton title='1' onPress={() => setCount(count + 1)   }/>
        <MyButton title='-1' onPress={ () => setCount(count -1)} />
    </View>
}

/*
const styles = StyleSheet.create({
    container: {    
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center'
    },
    text: {
        fontSize: 30,
        marginBottom: 10
      }


});
*/