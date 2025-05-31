import { Text, View } from "react-native"
import { styles } from "./styles"
import { useContext } from "react"
import { ThemeContext } from "../contexts/ThemeContext"
import { UserContext } from "../contexts/UserContext"

const Content = () => {

    const {isDark} = useContext(ThemeContext)
    const user = useContext(UserContext)

    return(
        <View style={[styles.contents,
            { 
                backgroundColor: isDark? '#000' : '#fff',
                
            }
        ]}>
            {/* <Text style={[styles.text, {color: isDark? '#fff': '#000'}]}>홍길동님, 좋은 하루 되세요</Text> */}
            <Text style={[styles.text, {color: isDark? '#fff': '#000'}]}>{user}님, 좋은 하루 되세요</Text>
        </View>
    )
}

export default Content