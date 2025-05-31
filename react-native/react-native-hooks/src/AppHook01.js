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

export default function AppHook01() {

    return (
      <ThemeProvider theme={theme}>
        <Container>
          <Counter />
          <Button title="btn1" onPress={() => alert("btn1 pressed!")} />
        </Container>
      </ThemeProvider>
    );
}