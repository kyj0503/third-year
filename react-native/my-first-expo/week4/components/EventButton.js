import { useState } from 'react'
import {View, Text, TouchableOpacity, StyleSheet} from 'react-native'


//Event Button - Press In, Press Out, Press, LongPress
export default function EventButton() {    

    const _onPressIn =  () => console.log('Press In!');
    const _onPressOut = () => console.log('Press Out!');
    const _onPress = () => console.log('Press!');

    return<View style={{alignItems: 'center'}}>
        <TouchableOpacity style={styles.btn}
            onPressIn={ _onPressIn}
            onPressOut={ _onPressOut}
            onPress={ _onPress}
            onLongPress={ () => console.log('Long Press!')}
            delayLongPress = {3000} 
        >
            <Text style={styles.textStyle}>Press</Text>            
        </TouchableOpacity>    
    </View>
}


const styles = StyleSheet.create({
    btn: {
        backgroundColor: '#f1c40f',
        padding: 16,
        margin: 10,
        borderRadius: 8
    },
    textStyle: {
        fontSize: 24,
        color: 'white'
    }
}

)