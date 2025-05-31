import { useState } from 'react'
import { ThemeProvider } from 'styled-components/native'
import {theme} from './theme'
import { Container } from './components/Container'
import Button from './components/Button'
import Form from './components/Form4'

export default function AppHook05() {

  const [isVisible, setIsVisible] = useState(true)
    return (
      <ThemeProvider theme={theme}>
        <Container>
          <Button
            title={isVisible ? "Hide" : "Show"}
            onPress={() => {
              setIsVisible((prev) => !prev);
            }}
          />
          {isVisible && <Form />}
        </Container>
      </ThemeProvider>
    );

}