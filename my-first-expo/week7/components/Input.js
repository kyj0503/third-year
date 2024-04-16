import styled from "styled-components/native";

//styledInput Component
const StyledInput = styled.TextInput`
    width: 200px;
    height: 60px;
    margin: 5px;
    padding: 10px;
    border-radius: 10px;
    border: 2px;
    border-color: #3498db;
    font-size: 24px;
`;

//attribute를 매번 TextInput에 전달
const Input = () => {
    return(
        <StyledInput 
        placeholder="Enter a text..." 
        placeholderTextColor="#3498db"/>
    );
}

export default Input;