import {View, Text, StyleSheet, StatusBar} from 'react-native'
import { viewStyles, textStyles  } from './styles';
import {Header, Contents, Footer} from './components/Layout'

//외부 스타일링
export default function AppStyle01() {

    return<View style={viewStyles.container}>
        <Header />
        <Contents />
        <Footer />
        <StatusBar style="auto"/>
    </View>
}
