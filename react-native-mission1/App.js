import { StyleSheet, Text, View} from 'react-native';

import Mission01  from './Mission01';
import Mission02 from './Mission02';
import Mission03 from './Mission03';
import Mission04 from './Mission04';
import Mission05 from './Mission05';

export default function App() {
  return <View style={styles.container}>
      <Mission05/>
    </View>
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    top:30,
    backgroundColor: '#fff',
  },
});
