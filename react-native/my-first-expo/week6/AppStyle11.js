import styled from "styled-components/native";
import Button from "./components/Button2";
//import Input from "./components/Input2";      //borderColor 속성기입
import Input from "./components/Input3";        //attribute 함수

const Container = styled.View`
    flex: 1;
    background-color: #ffffff;
    align-items: center;
    justify-content: center;
`;

//StyledInput Component with attribute
//Input attribute를 넣어 borderColor값이 Input 컴포넌트 design에 반영
export default function AppStyle11() {

    return(
        <Container>
            <Button title="Hanbit"/>
            <Button title="React Native"/>
            <Input borderColor="#3498db"/>
            <Input borderColor="#9b59b6"/>
        </Container>        
    );
} 