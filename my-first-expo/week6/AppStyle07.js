import { View, Text, StyleSheet, StatusBar, TouchableOpacity } from 'react-native'

//Styled 적용 - react native style sheet 사용
const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center'
    },
    btn_container: {
        backgroundColor: '#9b59b6',
        borderRadius: 15,
        paddingVertical: 15,
        paddingHorizontal: 40,
        marginVertical: 10,
        justifyContent: 'center'       
        
    },    
    title: {
        fontSize: 20,
        fontWeight: '600',
        color: '#fff'
    }
});

const Button = props => {
    return<TouchableOpacity style={styles.btn_container}>
        <Text style={styles.title}>{props.title}</Text>
    </TouchableOpacity>
}

export default function AppStyle07() {

    return <View style={styles.container}>
        <Button title="Hanbit" />
        <Button title="React native" />
        <StatusBar style="auto" />
    </View>
}