import {View, Text, Pressable, TouchableOpacity} from 'react-native';

//1. MyButton으로부터 전달받은 속성값을 props파라미터를 사용하여 확인 가능
const MyButton = (props) => {
    console.log(props);
    return<View>
        <TouchableOpacity style={styles.btn}  onPress={() => alert('my button!')}>
            <Text style={styles.text}>{props.title}</Text>
        </TouchableOpacity>
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