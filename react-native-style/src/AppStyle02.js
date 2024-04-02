import {View, Text, StyleSheet, StatusBar} from 'react-native'

//정렬
//justifyContent: flexDirection과 동일한 방향으로 정렬
//'flex-start', 'flex-end', 'center', 'space-between', 'space-around', 'space-evenly',

export default function AppStyle02() {

    return<View style={styles.container}>
            <View style={[styles.box, {backgroundColor: 'powderblue'}]} />                            
            <View style={[styles.box, {backgroundColor: 'skyblue'}]}/>
            <View style={[styles.box, {backgroundColor: 'steelblue'}]}/>
        <StatusBar style="auto"/>
    </View>
}

const styles = StyleSheet.create({
    container: {
        flex: 1,     
        flexDirection: 'column',
        marginTop: 8,
        padding: 10,
        backgroundColor: 'aliceblue',
        justifyContent: 'center'
    },
    box: {        
        width: 80,
        height: 80,        
    },
});