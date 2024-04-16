import styled, { ThemeProvider } from "styled-components/native";
import Button from "./components/Button3";  //thema button
import Input from "./components/Input3";     //attribute 사용
import { theme } from "./theme";            //purple, blue

//ThemaProvider 스타일
export default function AppStyle12() {

    return(
        <ThemeProvider theme={theme}>
        <Container>
            <Button title="Hanbit" />
            <Button title="React Native" />
            <Input borderColor="#3498db"/>
            <Input borderColor="#9b59b6"/>
        </Container>   
        </ThemeProvider>     
    );
} 

const Container = styled.View`
    flex: 1;
    background-color: #ffffff;
    align-items: center;
    justify-content: center;
`;
