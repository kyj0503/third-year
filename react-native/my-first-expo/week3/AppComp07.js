import {View, Text, StyleSheet, StatusBar, Button} from 'react-native';

//button component
export default function AppComp07() {

    return(<View style={styles.container}>
      <Text style={styles.text}>Button Component</Text>
      <Button title='btn' onPress={() => alert('clicked!!!')} />      
      <StatusBar style='auto' />
    </View>);
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff',
      alignItems: 'center',
      justifyContent: 'center',
    },
    text: {
      fontSize: 30,
      marginBottom: 10
    }
  });