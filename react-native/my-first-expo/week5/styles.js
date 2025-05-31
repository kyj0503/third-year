import { StyleSheet } from "react-native";


export const viewStyles = StyleSheet.create({
        container: {            
            flex: 1,
            padding: 10,
            backgroundColor: '#fff',
            alignItems: 'center',     //flexDirection과 동일한 방향으로 정렬
            justifyContent: 'center'  //flexDirection과 수직인 방향으로 정렬
        }
});

export const textStyles = StyleSheet.create({
    text: {
        padding: 10,
        fontSize: 26,
        fontWeight: '600',
        color: 'black'
    },
    error: {
        fontWeight: '400',
        color: 'red'
    } 

});