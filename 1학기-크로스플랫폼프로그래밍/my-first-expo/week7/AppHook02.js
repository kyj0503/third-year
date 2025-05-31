import {View, Text} from 'react-native';
import styled, {ThemeProvider} from 'styled-components/native';
import {theme} from './theme';
import Button from './components/Button';
import Counter from './components/Counter';


const Container = styled.View`
  flex: 1;
  background-color: ${props => props.theme.white};
  justify-content: center;
  align-items: center;
`;

//useStae 사용하기 with Counter
export default function AppHook02() {

    return (
      <ThemeProvider theme={theme}>
        <Container>
          <Counter />         
        </Container>
      </ThemeProvider>
    );
}