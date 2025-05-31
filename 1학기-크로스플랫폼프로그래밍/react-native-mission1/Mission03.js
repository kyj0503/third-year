import { useState } from "react";
import { View, Text, Image } from "react-native";
import ScoreIcon from "./assets/score.jpg";

const Score = ({ score=400 }) => {
  return (
    <View style={{ flexDirection: "row", alignItems: 'center' }}>
      <Image
        source={ScoreIcon}
        style={{ flex: 1, height: 50, paddingHorizontal: 100}}
        resizeMode="center"
      />
      <Text style={{ flex: 1, fontSize: 25, fontWeight: "bold" }}>{score}</Text>
    </View>
  );
};

export default function Mission3() {
  const [score, setScore] = useState(1200);

  return <Score score={score} />;
}
