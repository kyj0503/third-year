import { useContext, createContext } from "react"
import { View, Text } from "react-native";


const MyContext = createContext(null)


const Component = () => {
    //const value = useContext(MyContext)     
    const [name, id] = useContext(MyContext)     

    return <Text>name = {name}, id={id}</Text>
}


export default function ContextBasic02() {

    return <View>
        <MyContext.Provider value={['hello', 1234]}>
            <Component/>
        </MyContext.Provider>
    </View>
}