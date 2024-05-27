import React, { useEffect, useState } from 'react';
import { Text, View, ScrollView, StyleSheet } from 'react-native';
import axios from 'axios';
import { parseString } from 'react-native-xml2js';

const baseTimes = ['0200', '0500', '0800', '1100', '1400', '1700', '2000', '2300'];

function getFormattedDate(date = new Date()) {
  return date.toISOString().slice(0, 10).replace(/-/g, '');
}

function getClosestBaseTime() {
  const now = new Date();
  const currentHour = now.getUTCHours();
  const currentMinute = now.getUTCMinutes();

  console.log(`Current UTC Time: ${now.toISOString()}`);
  console.log(`Current Hour: ${currentHour}, Current Minute: ${currentMinute}`);

  let closestBaseTime = '0200';
  let closestBaseTimeDate = new Date();

  for (let i = baseTimes.length - 1; i >= 0; i--) {
    const baseHour = parseInt(baseTimes[i].substring(0, 2), 10);
    if (currentHour > baseHour || (currentHour === baseHour && currentMinute >= 0)) {
      closestBaseTime = baseTimes[i];
      break;
    }
    closestBaseTimeDate.setUTCHours(baseHour);
  }

  if (closestBaseTimeDate.getUTCHours() > currentHour) {
    closestBaseTimeDate.setUTCDate(now.getUTCDate() - 1);
  }

  console.log(`Closest Base Time: ${closestBaseTime}`);
  console.log(`Closest Base Date: ${closestBaseTimeDate.toISOString()}`);

  return {
    baseDate: getFormattedDate(closestBaseTimeDate),
    baseTime: closestBaseTime
  };
}

function Weather() {
  const [weather, setWeather] = useState({
    today: { minTemp: null, maxTemp: null },
    tomorrow: { minTemp: null, maxTemp: null },
    dayAfterTomorrow: { minTemp: null, maxTemp: null },
    twoDaysAfterTomorrow: { minTemp: null, maxTemp: null }
  });
  const [rawData, setRawData] = useState('');

  useEffect(() => {
    const { baseDate, baseTime } = getClosestBaseTime();
    console.log(`Using Base Date: ${baseDate}, Base Time: ${baseTime}`);

    const fetchWeather = async (date, setDayWeather) => {
      try {
        console.log(`Fetching weather for date: ${date}`);
        const { data } = await axios.get(
          'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst', {
            params: {
              serviceKey: 'V7RZpsZ3goxaM6p+ssykmuOrRrMqJhojqMa6GbYCOaXRdFV1vKburVUVbFUdARFRk+T9TfQpIyigFlRblBFwDA==',
              numOfRows: 500,
              pageNo: 1,
              dataType: 'XML',
              base_date: baseDate,
              base_time: baseTime,
              nx: 55, // 사용자에게 입력받은 값으로 대체
              ny: 127 // 사용자에게 입력받은 값으로 대체
            }
          }
        );

        console.log(`API Response Data:`, data);
        setRawData(data);

        parseString(data, (err, result) => {
          if (err) {
            console.error(err);
          } else {
            console.log('Parsed XML Data:', result);
            const items = result.response.body[0].items[0].item;
            let minTemp = null;
            let maxTemp = null;

            items.forEach(item => {
              const category = item.category[0];
              const fcstValue = parseFloat(item.fcstValue[0]);
              const fcstDate = item.fcstDate[0];

              if (fcstDate === date) {
                if (category === 'TMN') {
                  minTemp = fcstValue;
                } else if (category === 'TMX') {
                  maxTemp = fcstValue;
                }
              }
            });

            console.log(`Date: ${date}, Min Temp: ${minTemp}, Max Temp: ${maxTemp}`);

            setDayWeather(prevState => ({
              ...prevState,
              minTemp,
              maxTemp
            }));
          }
        });
      } catch (error) {
        console.error('Error fetching weather data:', error);
      }
    };

    fetchWeather(getFormattedDate(new Date()), (data) => setWeather(prevState => ({ ...prevState, today: data })));
    fetchWeather(getFormattedDate(new Date(Date.now() + 86400000)), (data) => setWeather(prevState => ({ ...prevState, tomorrow: data })));
    fetchWeather(getFormattedDate(new Date(Date.now() + 2 * 86400000)), (data) => setWeather(prevState => ({ ...prevState, dayAfterTomorrow: data })));
    fetchWeather(getFormattedDate(new Date(Date.now() + 3 * 86400000)), (data) => setWeather(prevState => ({ ...prevState, twoDaysAfterTomorrow: data })));

  }, []);

  return (
    <ScrollView>
      <Text>오늘 최저 기온: {weather.today.minTemp}°C</Text>
      <Text>오늘 최고 기온: {weather.today.maxTemp}°C</Text>
      <Text>내일 최저 기온: {weather.tomorrow.minTemp}°C</Text>
      <Text>내일 최고 기온: {weather.tomorrow.maxTemp}°C</Text>
      <Text>모레 최저 기온: {weather.dayAfterTomorrow.minTemp}°C</Text>
      <Text>모레 최고 기온: {weather.dayAfterTomorrow.maxTemp}°C</Text>
      <Text>글피 최저 기온: {weather.twoDaysAfterTomorrow.minTemp}°C</Text>
      <Text>글피 최고 기온: {weather.twoDaysAfterTomorrow.maxTemp}°C</Text>
    </ScrollView>
  );
}

export default Weather;
