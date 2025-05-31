import {View, Text, TouchableOpacity} from 'react-native';

//default props

const MyButton = (props) => {
    
    return<View>
        <TouchableOpacity style={styles.btn}  onPress={() => alert('my button!')}>
            <Text style={styles.text}>{props.children || props.title}</Text>
        </TouchableOpacity>
    </View>
}


MyButton.defaultProps = {
    title: 'MyBtn'
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