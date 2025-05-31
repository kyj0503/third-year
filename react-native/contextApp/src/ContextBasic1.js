const { useContext } = require("react")

//MyContext 생성
const MyContext = createContext(null)

const Component = () => {
    const value = useContext(MyContext)    //Consumer(read)
    return <Text>value={value}</Text>
}

//Context Provider: value prop에 초기값을 넣어 사용(write)
export default function ContextBasic1() {
    return <View>
        <MyContext.Provider value="Hello">
            <Component />
        </MyContext.Provider>
    </View>
}