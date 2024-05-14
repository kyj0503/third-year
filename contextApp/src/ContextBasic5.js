import { Button } from "react-native"

const MyContext = createContext(null)

const Component = () => {
    const { count, inc, dec } = useContext(MyContext)    //Consumer(read)
    return <>
        <Text>count={count}</Text>
        <Button title="+" onPress={() => inc()} />
        <Button title="-" onPress={() => dec()} />
        <Button title="-2" onPress={() => dec(2)} />
    </>
}

export default function ContextBasic4() {
    const [count, setCount] = useState(0)

    return <View>
        <MyContext.Provider value={ { count, setCount} }>
            <Component />
        </MyContext.Provider>
    </View>
}   