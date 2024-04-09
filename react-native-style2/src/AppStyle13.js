import styled, { ThemeProvider } from "styled-components/native";
import Button from "./components/Button3";  //thema button
import Input from "./components/Input3";     //attribute 사용
import { theme, lightTheme, darkTheme } from "./theme";            //purple, blue
import { Switch } from "react-native";
import { useState } from "react";

const Container = styled.View`
    flex: 1;    
    background-color: 
    align-items: center;
    justify-content: center;
`;

//ThemaProvider 스타일2 - light, dark
export default function AppStyle13() {

    
    

    return (
       
            <Container>
                <Switch                                                         />
                <Button title="Hanbit"  />
                <Button title="React Native" />
                <Input borderColor="#3498db" />
                <Input borderColor="#9b59b6" />
            </Container>
        
    );
} 