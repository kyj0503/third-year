import styled from "styled-components/native"

/*
flex는 1
background-color는 테마의 white
justify-content: center		//flexDirection과 동일한 방향
align-items: center			//flexDirection과 수직인 방향
*/

export const Container = styled.View`
flex: 1;    
background-color: ${props => props.theme.white};
justify-content: center;
align-items: center;
`