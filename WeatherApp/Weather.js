import React, { useEffect, useState } from 'react';
import { Text, View } from 'react-native';
import axios from 'axios';
import { parseString } from 'react-native-xml2js';

export default function Weather({userNx, userNy}) {
  const [weather, setWeather] = useState({ rainPerc: null, rainType: null, rainAmount: null, skyType: null });

  useEffect(() => {
    const today = new Date();
    const dateString = `${today.getFullYear()}${today.getMonth()+1}${today.getDate()}`;
    var timeString;

    for (let i = 23; i >= 2; i-=3) {
        if(today.getHours >= i) {
            timeString = i;
        }
    }

    if(timeString > 10) {
        timeString = `${timeString}00`;
    } else {
        timeString = `0${timeString}00`;
    }

    console.log(dateString);
    console.log(timeString);
    console.log(userNx);
    console.log(userNy);

    const fetchWeather = async () => {
      try {
        const { data } = await axios.get(
          'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst', {
            params: {
              serviceKey: 'V7RZpsZ3goxaM6p+ssykmuOrRrMqJhojqMa6GbYCOaXRdFV1vKburVUVbFUdARFRk+T9TfQpIyigFlRblBFwDA==',
              numOfRows: 500,
              pageNo: 1,
              dataType: 'JSON',
              base_date: dateString, // 당일날짜 불러오기
              base_time: timeString, // 사용자 시간 불러오기
              nx: userNx, // 사용자
              ny: userNy, // 사용자
            },
          }
        );

        parseString(data, (err, result) => {
          if (err) {
            console.error(err);
          } else {
            const items = result.response.body.items.item[0];
            let rainPerc = null;
            let rainType = null;
            let rainAmount = null;
            let skyType = null;
            
            // items.forEach(item => {
            //   const category = item.category;
            //   const fcstValue = parseInt(item.fcstValue);
            //   if (category === 'POP') {
            //     rainPerc = fcstValue;
            //   } else if (category === 'PTY') {
            //     rainType = fcstValue;
            //   } else if (category === 'PCP') {
            //     rainAmount = fcstValue;
            //   } else if (category === 'SKY') {
            //     skyType = fcstValue;
            //   }
            // });

            const category = items.category;
            const fcstValue = items.fcstValue;
            if (category === 'POP') {
                rainPerc = fcstValue;
              } else if (category === 'PTY') {
                rainType = fcstValue;
              } else if (category === 'PCP') {
                rainAmount = fcstValue;
              } else if (category === 'SKY') {
                skyType = fcstValue;
              }


            switch (parseInt(rainType)) {
                case 0:
                    rainType = "없음";
                    break;
                case 1:
                    rainType = "비";
                    break;
                case 2:
                    rainType = "비/눈";
                    break;
                case 3:
                    rainType = "눈";
                    break;
                case 4:
                    rainType = "소나기";
                    break;
            }

            skyTypeCode = parseInt(skyType);

            if(skyTypeCode >= 0 && skyTypeCode <= 5) {
                skyType = "맑음";
            }else if(skyTypeCode >= 6 && skyTypeCode <= 8) {
                skyType = "구름많음";
            }else if(skyTypeCode >= 9 && skyTypeCode <= 10) {
                skyType = "흐림";
            }

            setWeather({ rainPerc, rainType, rainAmount, skyType });
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
      <Text>강수확률: {weather.rainPerc}%</Text>
      <Text>강수형태: {weather.rainType}</Text>
      <Text>강수량: {weather.rainAmount}mm</Text>
      <Text>하늘: {weather.rainType}</Text>
    </View>
  );
}
