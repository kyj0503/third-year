import React, { useEffect, useState } from 'react';
import { Text, View } from 'react-native';
import axios from 'axios';
import { parseString } from 'react-native-xml2js';

export default function App() {

  const [weather, setWeather] = useState(null);     /* weather는 변수 선언, setWeather는 변수 업데이트 함수 */

  // useEffect 훅을 사용하여 컴포넌트가 마운트될 때 fetchWeather 함수를 호출
  useEffect(() => {
    const fetchWeather = async () => {
      try {
        // axios.get을 사용하여 기상청 API로부터 날씨 데이터를 가져옴. 요청에 필요한 파라미터(서비스 키, 날짜, 시간, 좌표 등)를 포함
        const { data } = await axios.get(
          'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst', {
            params: {
              serviceKey: 'V7RZpsZ3goxaM6p+ssykmuOrRrMqJhojqMa6GbYCOaXRdFV1vKburVUVbFUdARFRk+T9TfQpIyigFlRblBFwDA==',
              numOfRows: 10,
              pageNo: 1,
              base_date: 20240527,
              base_time: '0600',
              nx: 55,
              ny: 127,
            },
          }
        );

        // parseString을 사용하여 XML 형식의 응답 데이터를 파싱. 파싱 결과를 result로 받음
        parseString(data, (err, result) => {
          if (err) {
            console.error(err);
          } else {
            console.log(JSON.stringify(result, null, 2)); // Log the entire result object
          }
        });
      } catch (error) {
        console.error('Error fetching weather data:', error);
      }
    };

    fetchWeather();
  }, []);

  return;
}
