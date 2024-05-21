import Header from "./Header"
import Content from "./Content"
import Footer from "./Footer"
import { styles } from "./styles"
import { View } from "react-native"



const Page = () => {

    return(        
        <View style={styles.container}>            
            <Header/>
            <Content />
            <Footer />
        </View>        
    )
}


export default Page