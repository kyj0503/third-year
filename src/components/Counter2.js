import {View, Text, StyleSheet} from 'react-native'
import MyButton from './MyButton5'
import { useState } from 'react'

//double counter
export default function Counter2() {

    const [count, setCount] = useState(0);
    const [double, setDouble] = useState(0);

    return<View style={{alignItems: 'center'}}>
        <Text style={{fontSize: 30, margin: 10}}>count: {count}</Text>
        <Text style={{fontSize: 30, margin: 10}}>double: {double}</Text>
        <MyButton title='+' onPress={() => {setCount(count + 1); setDouble(double + 2)   }}/>
        <MyButton title='-' onPress={ () => {setCount(count -1); setDouble(double -2) }} />
    </View>
}
