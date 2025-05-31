import { Button } from "react-native"

const { useContext } = require("react")

//MyContext 생성
const MyContext = createContext(null)

const Component = () => {
    const { count, setCount } = useContext(MyContext)    //Consumer(read)
    return <>
        <Text>count={count}</Text>
        <Button title="+" onPress={() => setCount(count + 1)} />
    </>
}

//Global State관리
export default function ContextBasic4() {
    const [count, setCount] = useState(0)

    return <View>
        <MyContext.Provider value={ { count, setCount} }>
            <Component />
        </MyContext.Provider>
    </View>
}   