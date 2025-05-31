import styled from "styled-components/native";

//styledInput Component - attribute 속성값 설정
const StyledInput = 






`
    width: 200px;
    height: 60px;
    margin: 5px;
    padding: 10px;
    border-radius: 10px;
    border: 2px;    
    border-color: ${props => props.borderColor} ;
    font-size: 24px;
`;

const Input = (props) => {
    return(                        
        <StyledInput borderColor={props.borderColor}/>
    );
}

export default Input;