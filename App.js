import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';

export default function App() {

  const name = 'hongildong';

  const MyButton = () => {
    return 
  }

  return (
    <View style={StyleSheet.container}>
      <Text>
        My name is {name === 'hanbit' ? 'hanbit' : (name === 'hongildong' ? 'hongildong' : 'React Native')}
      </Text>
      <StatusBar style="auto" />
    </View>)
}