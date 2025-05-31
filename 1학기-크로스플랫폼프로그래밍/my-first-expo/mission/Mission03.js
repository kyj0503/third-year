import { Text, View, Image } from "react-native";
import ScoreIcon from "../assets/score.jpg";

const Score = ({score=400}) =? {

    return <View style={{flexDirection:'row', alignItems:'center'}}>
        <Image
            source={ScoreIcon}
            style={{height:50, flex:1, paddingHorizontal:100}}
            resizeMode='center'/>
        <Text style={{flex:1, fontSize:30}}>{score}</Text>
    </View>
}