import { useContext, createContext, useState } from "react"
import { View, Text, Button} from "react-native";


const MyContext = createContext(null)

const Component = () => {

    const {count, inc, dec} = useContext(MyContext)     //Consumer(read)
    return <>
        <Text>count={count}</Text>        
        <Button title="+" onPress={()=>inc()} />
        <Button title="-" onPress={()=>dec()} />
        <Button title="-2" onPress={()=>dec(2)} />
    </>
}


export default function ContextBasic05() {

    const [count, setCount] = useState(0)

    const inc = () => setCount(count + 1)
    const dec = (delta = 1) => setCount(count - delta)

    return <View>        
        <MyContext.Provider value={ {count, inc, dec }  } >
            <Component/>
        </MyContext.Provider>
    </View>
}