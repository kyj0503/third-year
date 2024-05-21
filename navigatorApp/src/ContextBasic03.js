import { useContext, createContext } from "react"
import { View, Text } from "react-native";


const MyContext = createContext(null)


const Component = () => {
    const {name, id} = useContext(MyContext)

    return <Text>name = {name}, id = {id}</Text>
}


//Context Provder: value prop에 초기값을 넣어 사용 - object
export default function ContextBasic03() {

    return <View>
        <MyContext.Provider value={{name: '홍길동', id: 20240512}}>
            <Component/>
        </MyContext.Provider>
    </View>
}