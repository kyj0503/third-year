
import styled, { ThemeProvider } from 'styled-components/native'
import {theme} from '../theme'
import Button from './Button'
import { useMemo, useState } from 'react';


const list = ['JavaScript', 'Expo', 'Expo', 'React Native']
let idx = 0;

const getLength = (text) => {
    console.log(`Target text: ${text}`)
    return text.length
}

//useMemo
export default function Length() {

    const [text, setText] = useState(list[0])

    const _onPress = () => {
        if(idx < list.length-1) {
            ++idx
            setText(list[idx])
        }            
    }

    const length = useMemo( () => getLength(text), [text])

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