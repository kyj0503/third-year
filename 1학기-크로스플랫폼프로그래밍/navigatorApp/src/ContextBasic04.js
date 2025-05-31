import { useContext, createContext, useState } from "react"
import { View, Text, Button} from "react-native";

const MyContext = createContext(null)

const Component = () => {

    const {count, setCount} = useContext(MyContext)     //Consumer(read)
    return <>
        <Text>count={count}</Text>
        <Button title="+" onPress={()=>setCount(count+1)} />
    </>
}

//Global State관리
export default function ContextBasic04() {

    const [count, setCount] = useState(0)

    return <View>        
        <MyContext.Provider value={ {count, setCount }  } >
            <Component/>
        </MyContext.Provider>
    </View>
}