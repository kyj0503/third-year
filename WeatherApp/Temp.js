import React, { useEffect, useState } from 'react';
import { Text, View } from 'react-native';
import axios from 'axios';
import { parseString } from 'react-native-xml2js';
import { getClosestBaseTime, getFormattedDate, getKoreanTime } from './Time';

const baseTimes = ['0200', '0500', '0800', '1100', '1400', '1700', '2000', '2300'];

export default function Temp({ userNx, userNy }) {
  const [weather, setWeather] = useState([]);

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
              nx: userNx,
              ny: userNy,
            },
          }
        );

        parseString(data, (err, result) => {
          if (err) {
            console.error(err);
          } else {
            const items = result.response.body[0].items[0].item;
            const weatherData = {};

            items.forEach(item => {
              const category = item.category[0];
              const fcstDate = item.fcstDate[0];
              const fcstTime = item.fcstTime[0];
              const fcstValue = parseFloat(item.fcstValue[0]);

              if (!weatherData[fcstDate]) {
                weatherData[fcstDate] = { minTemp: Infinity, maxTemp: -Infinity, times: {} };
              }

              if (category === 'TMN') {
                weatherData[fcstDate].minTemp = Math.min(weatherData[fcstDate].minTemp, fcstValue);
              } else if (category === 'TMX') {
                weatherData[fcstDate].maxTemp = Math.max(weatherData[fcstDate].maxTemp, fcstValue);
              } else if (category === 'TMP') {
                weatherData[fcstDate].times[fcstTime] = fcstValue;
              }
            });

            const formattedWeather = Object.keys(weatherData).map(date => ({
              date,
              minTemp: weatherData[date].minTemp,
              maxTemp: weatherData[date].maxTemp,
              times: weatherData[date].times,
            }));

            setWeather(formattedWeather);
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
      {weather.map(({ date, minTemp, maxTemp, times }) => (
        <View key={date}>
          <Text>{date} 최저 기온: {minTemp}°C</Text>
          <Text>{date} 최고 기온: {maxTemp}°C</Text>
          {Object.keys(times).map(time => (
            <Text key={time}>{time} 온도: {times[time]}°C</Text>
          ))}
        </View>
      ))}
    </View>
  );
}
