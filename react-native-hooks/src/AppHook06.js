import Button from "./components/Button";
import { Container } from "./components/Container";
import { useState } from "react";
import Form from "./components/Form5";
import { theme } from "./theme";
import { ThemeProvider } from "styled-components/native";

export default function AppHook06() {

  const [isVisible, setIsVisible] = useState(true);
  return (
    <ThemeProvider theme={theme}>
      <Container>
        <Button
          title={isVisible ? "Hide" : "Show"}
          onPress={() => setIsVisible(!isVisible)}
        />
        {isVisible && <Form />}
      </Container>
    </ThemeProvider>
  )
}
