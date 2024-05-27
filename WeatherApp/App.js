import React, { useEffect, useState } from 'react';
import { Text, View } from 'react-native';
import Dust from './Dust';
import Temp from './Temp copy';
import Weather from './Weather';

export default function App() {
  return (
    <View>
      <Temp />
      <Dust />
    </View>
  );
}
