
import styled, { ThemeProvider } from 'styled-components/native'
import {theme} from '../theme'
import Button from './Button'
import { useState } from 'react';


const list = ['JavaScript', 'Expo', 'Expo', 'React Native']
let idx = 0;

const getLength = (text) => {
    console.log(`Target text: ${text}`)
    return text.length
}

//without "useMemo"
export default function Length() {

    const [text, setText] = useState(list[0])
    const [length, setLength] = useState('')


    const _onPress = () => {
         //이전 text의 길이를 화면에 보여주는 기능        
        setLength(getLength(list[idx]))        //button을 누를때마다 getLength를 호출 (e.g. Expo, React Native)
        if(idx < list.length-1) {
            ++idx
            setText(list[idx])
        }            
    }

    return(
        <ThemeProvider theme={theme}>
            <StyledText>text: {text}</StyledText>
            <StyledText>length: {length}</StyledText>
            <Button title="Get Length" onPress={_onPress} />
        </ThemeProvider>
    )

}


const StyledText = styled.Text`
    font-size: 24px;
`