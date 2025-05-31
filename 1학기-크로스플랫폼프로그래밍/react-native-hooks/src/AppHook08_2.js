import { useMemo, useState } from "react";
import { Container } from "./components/Container";
import styled, { ThemeProvider } from "styled-components/native";
import { theme } from "./theme";

import { Text } from "react-native";

const hardCalculate = (number) => {
  console.log("어려운 계산!");
  for (let i = 0; i < 99999999; i++) {} //생각하는 시간
  return number + 10000;
};

const easyCalculate = (number) => {
  console.log("쉬운 계산!");
  return number + 1;
};

//useMemo2
export default function AppHook08_2() {
  
  
  //어려운 계산
  const [hardNumber, setHardNumber] = useState(0);
  
  //쉬운 계산  
  const [easyNumber, setEasyNumber] = useState(0);

  const easySum = useMemo(() => easyCalculate(easyNumber), [easyNumber]);
  const hardSum = useMemo(() => hardCalculate(hardNumber), [hardNumber]);


  return (
    <ThemeProvider theme={theme}>
      <Container>
        <StyledText>어려운 계산</StyledText>
        <StyledTextInput
          val={hardNumber}
          onChange={(e) => {
            setHardNumber(parseInt(e.target.value));
          }}
        />
        <Text> + 10000 = {hardSum}</Text>

        <StyledText>쉬운 계산</StyledText>
        <StyledTextInput
          val={easyNumber}
          onChange={(e) => setEasyNumber(parseInt(e.target.value))}
        />
        <Text> + 1 = {easySum}</Text>
      </Container>
    </ThemeProvider>
  );
}

const StyledText = styled.Text`
  font-size: 24px;
  font-weight: bold;
  margin: 10px;
`;

const StyledTextInput = styled.TextInput.attrs({
  autoCapitalize: "none",
  autoCorrect: false,
})`
  width: 200px;
  height: 40px;
  border-color: ${(props) => props.theme.gray};
  border: 1px solid;
  margin: 10px 0;
  font-size: 20px;
`;
