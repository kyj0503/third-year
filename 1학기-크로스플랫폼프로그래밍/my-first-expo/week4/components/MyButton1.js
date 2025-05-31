import {View, Text, Pressable} from 'react-native';

//1. MyButton Component: Pressable
const MyButton = () => {
    return<View>
        <Pressable style={styles.btn}  onPress={() => alert('my button!')}>
            <Text style={styles.text}>My Button</Text>
        </Pressable>
    </View>
}

const styles = {   
    btn: {
      backgroundColor: '#3498db',
      padding: 16,
      margin: 10,
      borderRadius: 8
    },
    text: {
        fontSize: 24,
        color: 'white'
    }
}

export default MyButton;