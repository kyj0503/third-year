import { StatusBar } from 'expo-status-bar';

//기본 Context 예제
import ContextBasic01 from './src/ContextBasic01';

//Provider value: array
import ContextBasic02 from './src/ContextBasic02';

//Provider value: object
import ContextBasic03 from './src/ContextBasic03';

//Provider value: Global state
import ContextBasic04 from './src/ContextBasic04';

//Provider value: Global state with 일반 function
import ContextBasic05 from './src/ContextBasic05';

//without context
import ContextApp01 from './src/ContextApp01';

//with ThemeContext
import ContextApp02 from './src/ContextApp02';


//with UserContext
import ContextApp03 from './src/ContextApp03';

export default function App() {
  return (     
    <ContextApp03 />         
  );
}