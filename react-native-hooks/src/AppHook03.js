import styled, {ThemeProvider} from 'styled-components/native'
import {theme} from './theme'
import Counter from './components/Counter2'

const Container = styled.View`
  flex: 1;
  background-color: ${props => props.theme.white};
  justify-content: center;
  align-items: center;
`

//Setter 함수 with Counter2
export default function AppHook03() {
    return (
      <ThemeProvider theme={theme}>
        <Container>
          <Counter />         
        </Container>
      </ThemeProvider>
    )
}