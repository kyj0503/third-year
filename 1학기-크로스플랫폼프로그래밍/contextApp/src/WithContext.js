import { createContext, useState } from 'react'
import { View } from 'react-native'
import { styles } from './components2/styles'
import { createContext } from 'react'

const ThemeContext = createContext(null)

export default function App() {

    const [isDark, setIsDark] = useState(false)

    return (
        <ThemeContext.Provider value={{ isDark, setIsDark }}>
            <Page / >
        </ThemeContext.Provider>
    );
}

const Page = () => {   
    return (
        <View style={styles.container}>
            <Header />
            <Content />
            <Footer />
        </View>
    )
}

const Header= () => {
    const {isDark} = useContext(ThemeContext)
    return(
        <View style={[styles.header, { backgroundColor: isDark? '#000' : '#d3d3d3' }]}>
            <Text style={[styles.text, {color: isDark? '#fff': '#000'}] }>Welcome 홍길동!</Text>
        </View>
    )
}
const Content = () => {
    const {isDark} = useContext(ThemeContext)
    return(
        <View style={[styles.contents, { backgroundColor: isDark? '#000' : '#fff'} ]}>
            <Text style={[styles.text, {color: isDark? '#fff': '#000'}]}>홍길동님, 좋은 하루 되세요</Text>
        </View>
    )
}
const Footer = () => {
    const {isDark, setIsDark} = useContext(ThemeContext)
    const toggleTheme = () => {
        setIsDark(prev => !prev)
    }
    return (
        <View style={[styles.footer, {backgroundColor: isDark? '#000': '#d3d3d3'}]}>
            <Button title={isDark? 'Bright Mode' : 'Dark Mode'}
            onPress={toggleTheme} />
        </View>
    )
}
