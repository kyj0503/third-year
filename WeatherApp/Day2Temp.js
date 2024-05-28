import React, { useEffect, useState } from 'react';
import { Text, View } from 'react-native';
import axios from 'axios';
import { parseString } from 'react-native-xml2js';
import { getClosestBaseTime, getFutureDateInKoreanTime } from './Time';

const baseTimes = ['0200', '0500', '0800', '1100', '1400', '1700', '2000', '2300'];

export default function Temp({ userNx, userNy }) {
  const [weather, setWeather] = useState({ minTemp: null, maxTemp: null });

  useEffect(() => {
    const fetchWeather = async () => {
      const { futureDate } = getFutureDateInKoreanTime();
      const { baseTime } = getClosestBaseTime(baseTimes);

      // Log futureDate and baseTime
      console.log(`Future Date: ${futureDate}`);
      console.log(`Base Time: ${baseTime}`);

      try {
        const response = await axios.get(
          'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst', {
            params: {
              serviceKey: 'V7RZpsZ3goxaM6p+ssykmuOrRrMqJhojqMa6GbYCOaXRdFV1vKburVUVbFUdARFRk+T9TfQpIyigFlRblBFwDA==',
              numOfRows: 500,
              pageNo: 1,
              dataType: 'XML',
              base_date: futureDate,
              base_time: baseTime,
              nx: userNx,
              ny: userNy,
            },
          }
        );

        // Log the raw XML response
        console.log('Raw XML Response:', response.data);

        parseString(response.data, (err, result) => {
          if (err) {
            console.error('Parsing Error:', err);
            return;
          }

          // Log the parsed result to understand its structure
          console.log('Parsed Result:', JSON.stringify(result, null, 2));

          const items = result?.response?.body?.[0]?.items?.[0]?.item;
          if (items) {
            let minTemp = null;
            let maxTemp = null;

            items.forEach(item => {
              const category = item.category[0];
              const fcstValue = parseFloat(item.fcstValue[0]);
              console.log(`Category: ${category}, Forecast Value: ${fcstValue}`);
              if (category === 'TMN') {
                minTemp = fcstValue;
              } else if (category === 'TMX') {
                maxTemp = fcstValue;
              }
            });

            // Log the extracted temperatures
            console.log(`Min Temp: ${minTemp}`);
            console.log(`Max Temp: ${maxTemp}`);

            setWeather({ minTemp, maxTemp });
          } else {
            console.error('Items not found in the response.');
          }
        });
      } catch (error) {
        console.error('Error fetching weather data:', error);
      }
    };

    fetchWeather();
  }, [userNx, userNy]);

  return (
    <View>
      <Text>내일 최저 기온: {weather.minTemp}°C</Text>
      <Text>내일 최고 기온: {weather.maxTemp}°C</Text>
    </View>
  );
}
