import { useState } from "react"
import { Button, Image, Text, View } from "react-native"
import IntroImg from './assets/intro.png'
import GameoverImg from './assets/gameover.png'


const SceneIntro = ({setScene}) => {
  return<View>
    <Image source={IntroImg} style={{width:370, height: 600}} resizeMode="stretch"/>
    <Button title="시작" onPress={() => setScene("game")}/>
  </View>
}

const SceneGame = ({setScene}) => {

  setTimeout(() => {
      setScene("gameover")
  }, 3000)

  return<Text style={{fontSize: 20, fontWeight: 'bold'}}>메인화면~~~~~~~~</Text>
}

const SceneEnd = ({setScene}) => {
  return<View>
    <Image source={GameoverImg} style={{width:370, height: 600}} resizeMode="stretch"/>
    <Button title="다시" onPress={() => setScene("intro")} />
  </View>
}

export default function Mission05() {

  const [scene, setScene] = useState("intro")

  return <View>
      {scene === "intro" && <SceneIntro setScene={setScene} /> }
      {scene === "game" && <SceneGame setScene={setScene} /> }
      {scene === "gameover" && <SceneEnd setScene={setScene} /> }
  </View>
}