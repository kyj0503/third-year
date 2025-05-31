import Header from "./Header"
import Content from "./Content"
import Footer from "./Footer"
import { styles } from "./styles"
import { View } from "react-native"


const Page = ({isDark, setIsDark}) => {
    return(        
        <View style={styles.container}>            
            <Header isDark={isDark}/>
            <Content isDark={isDark}/>
            <Footer isDark={isDark} setIsDark={setIsDark}/>
        </View>        
    )
}


export default Page