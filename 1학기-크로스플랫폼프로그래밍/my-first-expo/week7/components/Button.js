import {View, Text, Pressable} from 'react-native'
import styled, {ThemeProvider} from 'styled-components/native'

const Container = styled.Pressable`
    background-color: ${props => props.theme.blue };    
    border-radius: 15px;
    padding: 15px 30px;
    margin: 10px 0px;
    justify-content: center;
`

const Title = styled.Text`
    font-size: 24px;
    font-weight: 600;
    color: ${props => props.theme.white};    
`


const Button = ({title, onPress}) => {
        return(
            <Container onPress={onPress}>
                <Title>{title}</Title>
            </Container>
        )

}

export default Button