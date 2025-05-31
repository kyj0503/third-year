import { Text, View } from "react-native"
import { styles } from "./styles"

const Content = ({isDark}) => {
    return(
        <View style={[styles.contents,
            { 
                backgroundColor: isDark? '#000' : '#fff',
                
            }
        ]}>
            <Text style={[styles.text, {color: isDark? '#fff': '#000'}]}>홍길동님, 좋은 하루 되세요</Text>
        </View>
    )
}

export default Content