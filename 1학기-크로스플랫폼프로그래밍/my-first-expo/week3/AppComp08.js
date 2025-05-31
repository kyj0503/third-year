import { View, Text, StatusBar } from 'react-native';
import MyButton from './components/MyButton';


//Custom button: Touchable Opacity
export default function AppComp08() {

  return (<View style={{
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  }}>

    <Text style={{
      fontSize: 30,
      marginBottom: 10
    }}>My Button Component</Text>

    <MyButton />

    <StatusBar style='auto' />

  </View>);
}
