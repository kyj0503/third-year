import { View, Text, StyleSheet, StatusBar, TouchableOpacity } from 'react-native'
import styled from 'styled-components/native';

//버튼 배경색 바꾸기(styled component 사용)
//props로 전달되는 title의 값이 Hanbit인 경우 바탕색을 다르게 표현
//스타일 작성 부분과 컴포넌트 사용되는 부분을 명확히 구분 -> 깔끔하게 관리

const ButtonContainer = styled.TouchableOpacity`        
        background-color: 
        border-radius: 15px;
        padding: 15px 40px;
        margin: 10px 0px;
        justify-content: center;    
    `;

const Title = styled.Text`
            font-size: 20px;
            font-weight: 600;
            color: #fff;
    `;

const Button = props => {
    return (
        <ButtonContainer title={props.title}>
            <Title>{props.title}</Title>
        </ButtonContainer>
    );
}

const Container = styled.View`
    flex: 1;
    background-color: #ffffff;
    align-items: center;
    justify-content: center;
`;

//styled component 사용 - 버튼 배경색 바꾸기-props
export default function AppStyle09() {
    return<Container>
        <Button title="Hanbit" />
        <Button title="React native" />        
    </Container>
}