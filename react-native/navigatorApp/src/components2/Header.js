import { useContext } from "react"
import { styles } from "./styles"
import { Text, View } from "react-native"
import { ThemeContext } from "../contexts/ThemeContext"
import { UserContext } from "../contexts/UserContext"


const Header= () => {
  
  const {isDark} = useContext(ThemeContext)
  const user = useContext(UserContext)
  
  return(
    <View style={[styles.header, 
                { backgroundColor: isDark? '#000' : '#d3d3d3' }]}>
        {/* <Text style={[styles.text, {color: isDark? '#fff': '#000'}] }>Welcome 홍길동!</Text> */}
        <Text style={[styles.text, {color: isDark? '#fff': '#000'}] }>Welcome {user}!</Text>
    </View>
  )  
}

export default Header