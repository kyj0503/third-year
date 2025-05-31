import { createContext } from "react";


//ThemeContext 객체 생성
export const ThemeContext = createContext(null)

//ThemeProvider가 없어도 초기값으로 값 전달
//export const ThemeContext = createContext("1234") 