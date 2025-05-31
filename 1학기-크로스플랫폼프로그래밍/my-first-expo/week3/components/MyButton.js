import { Text, TouchableOpacity } from 'react-native';

//1. MyButton Component1 - TouchableOpacity 컴포넌트
const MyButton = () => {
    return <TouchableOpacity
        style={{
            backgroundColor: '#3498db',
            padding: 16,
            margin: 10,
            borderRadius: 8
        }}
        onPress={() => {alert('clicked!')}}
    >
        <Text style={{ fontSize: 24, color: 'white' }}>My Button</Text>
    </TouchableOpacity>
}

export default MyButton;