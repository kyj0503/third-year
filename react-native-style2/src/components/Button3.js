import styled from "styled-components/native";

//props로 theme를 받아 색을 적용

const ButtonContainer = styled.TouchableOpacity`                        
        background-color: 


        border-radius: 15px;
        padding: 15px 40px;
        margin: 10px 0px;
        justify-content: center;    
    `;

//글자색: 테마의 text
const Title = styled.Text`
            font-size: 20px;
            font-weight: 600;
        
            color: 
    `;

const Button = props => {    
    return (
        <ButtonContainer title={props.title}>
            <Title>{props.title}</Title>
        </ButtonContainer>
    );
}

export default Button;