import {View, Text, StyleSheet, StatusBar} from 'react-native'
import ShadowBox from './components/ShadowBox';

//그림자
export default function AppStyle04() {

    return<View style={[styles.container]}>
           <ShadowBox />
        <StatusBar style="auto"/>
    </View>
}

const styles = StyleSheet.create({
    container: {
        flex: 1,        
        marginTop: 8,
        padding: 10,
        backgroundColor: 'aliceblue'        
    }
});