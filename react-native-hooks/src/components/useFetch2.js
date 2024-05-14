export const useFetch = url => {
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);

    useEffect(async () => {
        const res = await fetch(url);
        const result = await res.json();

        try {
            if(res.ok) {
                setData(result);
                setError(null);
            } else {
                throw result;
            }
        } catch (error) {
            setError(error);
        }
    }, []);

    return {data, error}
}