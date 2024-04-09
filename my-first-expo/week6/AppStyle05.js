import {View, Text, StyleSheet, StatusBar} from 'react-native'
import styled, {css} from 'styled-components/native';

//Styled component 사용법- example1
export default function AppStyle05() {

    //MyTextComponent: color가 white인 Text Component
    const MyTextComponent = styled.Text`
        color: #fff;
    `;

    //whiteText css: color는 white, font-size: 14px인 css객체
    const whiteText = css`
        color: #fff;
        font-size: 14px;
    `;

    //MyBoldTextComponent는 orangeText css문법과 font-weight: 600인 Text Component
    const MyBoldTextComponent = styled.Text`
        
        font-weight: 600;
    `;

    //MyLightTextComponent는 whiteText css문법과 font-weight: 200인 Text Component
    const MyLightTextComponent = styled.Text`
        ${whiteText}
        font-weight: 200
    `;

    //StyledText 컴포넌트: color가 검은색, font-size가 20px, margin: 10px, padding: 10px인 Text Component
    const StyledTextComponent = styled.Text`
        color: #000;
        font-size: 20px;
        margin: 10px;
        padding: 10px;
    `;

    //ErrorText컴포넌트는 StyledText컴포넌트의 스타일을 그대로 상속받은 채로 font-weight: 600; color: red인  Component
    const ErrorTextComponent = 



    

    return <View style={styles.container}>
        <View>
            <MyTextComponent>My Text Component!</MyTextComponent>
        </View>
        <View style={{ backgroundColor: 'black' }}>            
            <MyBoldTextComponent>MyBoldTextComponent text!</MyBoldTextComponent>
            <MyLightTextComponent>MyLightTextComponent text!</MyLightTextComponent>
        </View>
        <View>
            <StyledTextComponent>StyledTextComponent text!</StyledTextComponent>
            <ErrorTextComponent>Error</ErrorTextComponent>
        </View>
        <StatusBar style="auto" />
    </View>
}

const styles = StyleSheet.create({
    container: {
        flex: 1,        
        marginTop: 8,
        padding: 10,
        backgroundColor: 'aliceblue',
        justifyContent: 'flex-start',
        alignItems: 'stretch'
    },    

});