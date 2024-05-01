import styled from 'styled-components/native'

const BtnContainer = styled.Pressable`
    background-color: ${props => props.theme.blue};
    border-radius: 15px;
    padding: 15px 30px;
    magin: 10px 0px;
    justify-content: center;
`

const Title = styled.Text`
    font-size: 24px;
    font-weight: 600;
    color: ${props => props.theme.white};
`

const Button = ({title, onPress}) => {
        return<BtnContainer onPress={onPress}>
            <Title>{title}</Title>
        </BtnContainer>
}

export default Button