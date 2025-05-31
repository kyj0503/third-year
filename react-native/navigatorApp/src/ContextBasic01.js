import { useContext, createContext } from "react"
import { View, Text } from "react-native";

//MyContext 생성
const MyContext = createContext(null)

const Component = () => {
    const value = useContext(MyContext)     //Consumer(read)
    return <Text>value={value}</Text>
}

export default function ContextBasic01() {

    return <View>
        <MyContext.Provider value="hello">
            <Component/>
        </MyContext.Provider>
    </View>
}