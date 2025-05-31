import {View, Text, StyleSheet} from 'react-native'

export const Header = () => {
    return<View style={[styles.container, styles.header]}>
        <Text style={styles.text}>Header</Text>
    </View>
}

export const Contents = () => {
    return<View style={[styles.container, styles.contents]}>
        <Text style={styles.text}>Contnents</Text>
    </View>
}

export const Footer = () => {
    return<View style = { [styles.container, styles.footer]   }>
        <Text style={styles.text}>Footer</Text>
    </View>
}

//클래스 스타일링
const styles = StyleSheet.create({
    container: {        
        width: '100%',
        alignItems: 'center',
        justifyContent: 'center',
        height: 80
    },
    header: {   
        flex: 1,
        backgroundColor: '#faf061',        
    },
    contents: {
        flex: 2,
        backgroundColor: '#27f5a6',
        height: 500
    },
    footer: {
        flex: 1,
        backgroundColor: '#97f6f7'
    },
    text: {
        fontSize: 26
    }
});
