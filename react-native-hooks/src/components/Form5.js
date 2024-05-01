import { useEffect, useRef, useState } from "react";
import { View } from "react-native";
import styled from "styled-components/native";

const Form = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");

  const refName = useRef(null);         
  const refEmail = useRef(null);

  useEffect(() => {
    console.log("====Form 컴포넌트 Mount====");
    refName.current.focus();
    return () => console.log("====Form 컴포넌트 Unmount====");
  }, []);

  
  return (
    <View>
      <StyledText>name: {name}</StyledText>
      <StyledText>email: {email}</StyledText>
      <StyledTextInput placeholder="name" 
            onChangeText={(text) => setName(text)} 
            ref={refName} 
            returnKeyType="next"
            onSubmitEditing={() => refEmail.current.focus()}
      />
      <StyledTextInput placeholder="email" 
            onChangeText={(text) => setEmail(text)} 
            ref={refEmail}
            returnKeyType="done"
       />       
    </View>
  );
};

const StyledText = styled.Text`
  font-size: 24px;
  font-weight: bold;
`;

const StyledTextInput = styled.TextInput.attrs({
  autoCapitalize: "none",
  autoCorrect: false  
})`
  width: 200px;
  height: 40px;
  border-color: ${(props) => props.theme.gray};
  border: 1px solid;
  margin: 10px 0;
  font-size: 20px;  
`;

export default Form;
