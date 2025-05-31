import {View, Text, TouchableOpacity} from 'react-native';

//children props
//컴포넌트의 태그 사이에 전달된 값은 props.children으로 전달
// A || B -> A가 false이면 B가 나온다.

const MyButton = (props) => {
    console.log(props);
    return<View>
        <TouchableOpacity style={styles.btn}  onPress={() => alert('my button!')}>
            <Text style={styles.text}>{props.children || props.title}</Text>
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