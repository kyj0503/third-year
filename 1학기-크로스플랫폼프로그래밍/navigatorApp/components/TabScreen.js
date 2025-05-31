import { StyleSheet, View, Text } from "react-native";

export const SettingsScreen = () => {
    return (
        <View style={styles.container}>
            <Text style={styles.text}>Settings!</Text>
        </View>
    );
}

export const HomeScreen = () => {
    return (
        <View style={styles.container}>
            <Text style={styles.text}>Home!</Text>
        </View>
    );
};

export const NoteScreen = () => {
    return (
        <View style={styles.container}>
            <Text style={styles.text}>Note!</Text>
        </View>
    );
};

const styles = StyleSheet.create({
    container: { flex: 1, justifyContent: "center", alignItems: "center" },
    text: {fontSize: 30}
})
