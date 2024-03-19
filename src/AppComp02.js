import { Fragment } from "react";
import { View, Text, StyleSheet, StatusBar } from "react-native-web";

//JavaScript 변수
export default function AppComp02() {

    const name = 'hongildong';

        return(<view style={styles.container}>
            <Text style={styles.text}>My name is{name}</Text>
        </view>);

}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
    text: {
        fontSize: 30
    }
})