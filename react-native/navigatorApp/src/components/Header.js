import { styles } from "./styles"
import { Text, View } from "react-native"


const Header= ({isDark}) => {
  return(
    <View style={[styles.header, 
                { backgroundColor: isDark? '#000' : '#d3d3d3' }]}>
        <Text style={[styles.text, {color: isDark? '#fff': '#000'}] }>Welcome 홍길동!</Text>
    </View>
  )  
}

export default Header