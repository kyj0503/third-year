import { useEffect, useState } from "react";
import { View } from "react-native";
import styled from "styled-components/native";

/*
Mount시 console메세지
UnMount시 console메세지

단, Form이 만들어질때는 Mount되었다는 console메시지를 띄우고, 
Form이 안보일때는 Unmount되었다는 console메세지를 띄운다.
*/
const Form = () => {

    useEffect(() => {
        console.log("==Form is mounted!==\n")
        return() => console.log("==Form is Unmounted! ==")
    }, [])

    const [name, setName] = useState('')
    const [email, setEmail] = useState('')

    return<View>
        <StyledText>{name}</StyledText>
        <StyledText>{email}</StyledText>
        <StyledTextInput placeholder="Enter a name..." 
        onChangeText={(text) => setName(text)}/>
        <StyledTextInput placeholder="Enter a email..." 
        onChangeText={(text) => setEmail(text)}/>        
    </View>
    
}

const StyledText = styled.Text`
    font-size: 24px;
    font-weight: bold;
`

const StyledTextInput = styled.TextInput.attrs(
    {
        autoCapitalize: 'none',
        autoCorrect: false
    }
)
`
width: 200px;
height: 40px;
border-color: ${props => props.theme.gray};
border: 1px solid;
margin: 10px 0;
font-size: 20px;
`

export default Form;
