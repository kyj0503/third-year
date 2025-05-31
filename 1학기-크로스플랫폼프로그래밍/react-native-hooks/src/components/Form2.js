import { useState, useEffect } from 'react'
import {View} from 'react-native'
import styled from 'styled-components/native'

//useEffect - email이 변경될때마다 useEffect의 함수가 호출
const Form = () => {
        const [name, setName] = useState("")
        const [email, setEmail] = useState("")

        useEffect(
            () => {console.log(`name: ${name}, email: ${email}\n`)},
            [email]
        )

        return(
            <View>
                <StyledText>Name: {name}</StyledText>
                <StyledText>Email: {email}</StyledText>
                <StyledTextInput value={name} onChangeText={(text) => setName(text)} placeholder='name' />
                <StyledTextInput value={email} onChangeText={(text) => setEmail(text)} placeholder='email'/>                
            </View>
        )
}


//StyledTextInput Component
const StyledTextInput = styled.TextInput.attrs(
    {
        autoCapitalize: 'none',
        autoCorrect: false
    }
)`
border: 1px solid #747474;
padding: 10px;
margin: 10px 0;
width: 200px;
font-size: 20px;
`
const StyledText = styled.Text`
font-size: 24px;
margin: 10px;
`
export default Form