import {View, Text, TouchableOpacity} from 'react-native';

//Counter Button

const MyButton = (props) => {
    
    console.log(props);

    return<View>
        {/* <TouchableOpacity style={styles.btn}  onPress={() => alert('my button!')}> */}
        <TouchableOpacity style={styles.btn}  onPress={ props.onPress} >
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