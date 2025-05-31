import { useState } from "react"
import Page from "./components2/Page"
import { ThemeContext } from "./contexts/ThemeContext"
import { UserContext } from "./contexts/UserContext"

//with Context
export default function ContextApp03() {

    const [isDark, setIsDark] = useState(false)


    return (
      <UserContext.Provider value={'사용자'}>      
      <ThemeContext.Provider value={{ isDark, setIsDark }}>
        <Page />
      </ThemeContext.Provider>
      </UserContext.Provider>
    );
}