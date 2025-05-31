import {View, Text, StyleSheet, StatusBar} from 'react-native'

//정렬
//alignItem: flexDirection과 수직인 방향으로 정렬
//'stretch'(default), 'flex-start', 'flex-end', 'center', 'baseline'
export default function AppStyle03() {

    return<View style={[styles.container]}>
            <View style={[styles.box, {backgroundColor: 'powderblue'}]} />                            
            <View style={[styles.box, {backgroundColor: 'skyblue'}]}/>
            <View style={[styles.box, {backgroundColor: 'steelblue', width: 'auto', minWidth: 50}]}/>
        <StatusBar style="auto"/>
    </View>
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        /* flexDirection: 'row', */
        marginTop: 8,
        padding: 10,
        backgroundColor: 'aliceblue',
        justifyContent: 'flex-start',
        alignItems: 'center' 
    },
    box: {        
        width: 80,
        height: 80,        
    },
});