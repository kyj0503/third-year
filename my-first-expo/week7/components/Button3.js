import styled from "styled-components/native";

//props로 theme를 받아 색을 적용

const ButtonContainer = styled.TouchableOpacity`                        
        background-color: ${(props) => 
            (props.title ==='Hanbit' ? props.theme.blue : props.theme.purple)};
        border-radius: 15px;
        padding: 15px 40px;
        margin: 10px 0px;
        justify-content: center;    
    `;

const Title = styled.Text`
            font-size: 20px;
            font-weight: 600;           
            color: ${props => props.theme.text};
    `;

const Button = props => {    
    return (
        <ButtonContainer title={props.title} onPress={props.onPress}>
            <Title>{props.title}</Title>
        </ButtonContainer>
    );
}

export default Button;