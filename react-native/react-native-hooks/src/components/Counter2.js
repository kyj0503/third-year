import { useState } from 'react'
import styled from 'styled-components/native'
import Button from './Button'

const StyledText = styled.Text`
    font-size: 24px;
    margin: 10px;
`

/* 
setter 함수는 비동기로 호출 -> 상태가 바로 변경안됨
여러 업데이트가 함께 발생할 경우 setter 함수에 함수를 인자로 전달 
*/

//arrow 함수를 이용한 setter 함수 호출
const Counter = () => {
    const [count, setCount] = useState(0);

    return<>
        <StyledText>count: {count}</StyledText>
        <Button 
            title="+"
            onPress={() => {
               /*  setCount(count + 1)
                setCount(count + 1) */
                setCount(prevCount => prevCount +1)
                setCount(prevCount => prevCount +1) 
            }}
        />
        <Button
            title="-"
            onPress={() => setCount(count -1)}
        />    
    </>
}

export default Counter