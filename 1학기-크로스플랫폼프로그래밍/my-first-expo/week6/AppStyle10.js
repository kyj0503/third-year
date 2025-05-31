import styled from "styled-components/native";
import Button from "./components/Button2";
import Input from "./components/Input";

const Container = styled.View`
    flex: 1;
    background-color: #fff;
    align-items: center;
    justify-content: center;
`;

//StyledInput Component - InputTextComponent without attribute
export default function AppStyle10() {

    return(
        <Container>
            <Button title="Hanbit"/>
            <Button title="React Native"/>
            <Input />
        </Container>        
    );


}