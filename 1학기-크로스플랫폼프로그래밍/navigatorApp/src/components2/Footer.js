import { View } from "react-native"
import { styles } from "./styles"
import Button from "./Button"
import { useContext } from "react"
import { ThemeContext } from "../contexts/ThemeContext"

const Footer = () => {

  const {isDark, setIsDark} = useContext(ThemeContext)

  const toggleTheme = () => {
    setIsDark(prev => !prev)
  }
  return (
    <View style={styles.footer}>      
      <Button title={isDark? 'Bright Mode' : 'Dark Mode'}
      onPress={toggleTheme} />
    </View>
  )  
}

export default Footer