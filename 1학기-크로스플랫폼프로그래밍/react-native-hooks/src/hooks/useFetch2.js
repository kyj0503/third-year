export const useFetch = url => {    //url: 전달받은 API의 주소
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);

    useEffect(async () => {
        const res = await fetch(url);   //url 요청
        const result = await res.json();    //json으로 데이터 추출

        try {
            if(res.ok) {    //정상적이면 data 설정
                setData(result);
                setError(null);
            } else {    //응답이 ok가 아니면 예외 발생시킴
                throw result;
            }
        } catch(error) {    //진행중 에러 발생시 error 메시지 설정
            setError(error);
        }
    }, []);
    return {data, error}    //data와 error를 객체로 전달
}