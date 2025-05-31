import styled from "styled-components/native";

//styledInput Component - css만 적용
const StyledInput = styled.TextInput`
    width: 200px;
    height: 60px;
    margin: 5px;
    padding: 10px;
    border-radius: 10px;
    border: 2px;    
    border-color: 
    font-size: 24px;
`;

//placeholderTextColor는 부모로부터 받은 borderColor, borderColor는 부모로부터 받은 borderColor
const Input = (props) => {
    return(        
        //<StyledInput placeholder="Enter a text..." placeholderTextColor="#3498db" borderColor={props.borderColor}/>
        




    );
}

export default Input;