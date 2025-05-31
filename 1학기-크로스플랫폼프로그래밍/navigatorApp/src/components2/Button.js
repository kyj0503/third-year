import { Pressable, Text } from "react-native";
import { styles } from "./styles";


const Button = ({title, onPress}) => {
    return<Pressable style={styles.btnContainer} onPress={onPress}>
        <Text style={styles.btnText}>{title}</Text>
    </Pressable>

}

export default Button;