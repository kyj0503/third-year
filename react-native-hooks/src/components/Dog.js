const URL = 'https://dog.ceo/api/breeds/image/random'

export default function Dog() {
    const {data, error} = useFetch(URL);
    return(
        <>
            <StyledImage source={ data?.message? {uri: data.message} : null }/>
            <ErrorMessage>{error?.message}</ErrorMessage>
        </>
    )
}