import styled from "styled-components/native";


const ButtonContainer = styled.TouchableOpacity`
        background-color: #9b59b6;
        border-radius: 15px;
        padding: 15px 40px;
        margin: 10px 0px;
        justify-content: center;    
    `;

//Title component - styledText 






const Button = props => {
    return (
        <ButtonContainer>
            <Title>{props.title}</Title>
        </ButtonContainer>
    );
}

export default Button;