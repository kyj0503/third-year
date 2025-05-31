const baseUrl = 'https://koreanjson.com"

export default () => {
    const data =useFetch(baseUrl, "users")

    console.log("rendering...")

    return <View>
        <Text style = {{fontSize: 30, fontWeight: "bold"}}>useFetch</Text>
        <Text>{JSON.stringify(data, null, 2)}</Text>
    </View>
}