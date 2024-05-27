import React, { useEffect, useState } from 'react';
import { Text, View } from 'react-native';
import axios from 'axios';
import { parseString } from 'react-native-xml2js';
import { getClosestBaseTime, getFormattedDate, getKoreanTime } from './Time';

const baseTimes = ['0200', '0500', '0800', '1100', '1400', '1700', '2000', '2300'];

export default function Temp() {
  const [weather, setWeather] = useState({ minTemp: null, maxTemp: null });

  useEffect(() => {
    const fetchWeather = async () => {
      const { baseDate, baseTime } = getClosestBaseTime(baseTimes);

      try {
        const { data } = await axios.get(
          'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst', {
            params: {
              serviceKey: 'V7RZpsZ3goxaM6p+ssykmuOrRrMqJhojqMa6GbYCOaXRdFV1vKburVUVbFUdARFRk+T9TfQpIyigFlRblBFwDA==',
              numOfRows: 500,
              pageNo: 1,
              dataType: 'XML',
              base_date: baseDate,
              base_time: baseTime,
              nx: 55, // 사용자한테 입력받기
              ny: 127, // 사용자한테 입력받기
            },
          }
        );

        parseString(data, (err, result) => {
          if (err) {
            console.error(err);
          } else {
            const items = result.response.body[0].items[0].item;
            let minTemp = null;
            let maxTemp = null;
            
            items.forEach(item => {
              const category = item.category[0];
              const fcstValue = parseFloat(item.fcstValue[0]);
              if (category === 'TMN') {
                minTemp = fcstValue;
              } else if (category === 'TMX') {
                maxTemp = fcstValue;
              }
            });

            setWeather({ minTemp, maxTemp });
          }
        });
      } catch (error) {
        console.error('Error fetching weather data:', error);
      }
    };

    fetchWeather();
  }, []);

  return (
    <View>
      <Text>일일 최저 기온: {weather.minTemp}°C</Text>
      <Text>일일 최고 기온: {weather.maxTemp}°C</Text>
    </View>
  );
}
