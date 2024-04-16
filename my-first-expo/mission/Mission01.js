import {Text, View } from "react-native";

//Naming 카드 만들기
//글자크기 20, bold로 style
export default function App() {
    return <View>
      <NamingCard name="아이유" />
    </View>
}

const NamingCard = ({name}) => {
    return <View>
        <Text style={{fontSize: 20, fontWeight: "bold"}}>{name}</Text>
    </View>
}