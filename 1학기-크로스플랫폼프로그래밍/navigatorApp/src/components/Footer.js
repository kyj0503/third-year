import { View } from "react-native"
import { styles } from "./styles"
import Button from "./Button"

const Footer = ({isDark, setIsDark}) => {

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