import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
    container: {        
        flex: 1,
        width: '100%',        
    },
    header: {   
        flex: 1,
        borderBottomWidth: 1,
        borderColor: 'gray',
        alignItems: 'center',
        justifyContent: 'center'        
    },
    contents: {
        flex: 5,        
        alignItems: 'center',
        justifyContent: 'center',    
    },
    footer: {
        flex: 1,
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'flex-end',    
        borderTopWidth: 1,
        borderColor: 'gray',
        backgroundColor: '#d3d3d3'        
    },
    text: {
        marginTop: 30,
        fontSize: 26,
        fontWeight: 'bold'
    },
    btnContainer: {
        backgroundColor: '#fff',        
        padding: 5,
        margin: 10,
        borderRadius: 8
    },
    btnText: { 
        fontSize: 15,
        fontWeight: '600',
        color: 'black' 
    }
    
})
