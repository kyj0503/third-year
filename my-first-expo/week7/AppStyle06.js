import { View, Text, StyleSheet, StatusBar } from 'react-native'
import Button from './components/Button';
import styled from 'styled-components/native';

//Style 적용하기 using styled component - example2(Button, Container)
export default function AppStyle06() {

    return <Container>
        <Button title="Hanbit" />
        <Button title="React native" />
        <StatusBar style="auto" />
    </Container>

}

const Container = styled.View`
    flex: 1;
    background-color: #ffffff;
    align-items: center;
    justify-content: center;
`;
/*
const styles = StyleSheet.create({
    container: {
        flex: 1,        
        marginTop: 8,
        padding: 10,
        backgroundColor: 'aliceblue',
        justifyContent: 'center',
        alignItems: 'center'
    },    

});
*/