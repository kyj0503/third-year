import { useState } from "react"
import { Button, Image, Text, View } from "react-native"
import IntroImg from './assets/intro.png'
import GameoverImg from './assets/gameover.png'


export default function Mission04() {

 
  const [scene, setScene] = useState("intro")


  const SceneIntro = () => {
    return<View>
      <Image source={IntroImg} style={{width:370, height: 600}} resizeMode="stretch"/>
      <Button title="시작" onPress={() => setScene("game")}/>
    </View>
  }

  const SceneGame = () => {

    setTimeout(() => {
        setScene("gameover")
    }, 3000)

    return<Text style={{fontSize: 20, fontWeight: 'bold'}}>메인화면~~~~~~~~</Text>
  }

  const SceneEnd = () => {
    return<View>
      <Image source={GameoverImg} style={{width:370, height: 600}} resizeMode="stretch"/>
      <Button title="다시" onPress={() => setScene("intro")} />
    </View>
  }
  
  
  return <View>
      {scene === "intro" && <SceneIntro /> }
      {scene === "game" && <SceneGame /> }
      {scene === "gameover" && <SceneEnd /> }
  </View>
}

