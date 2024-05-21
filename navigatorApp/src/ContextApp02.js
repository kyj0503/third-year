import { useState } from "react"
import Page from "./components2/Page"
import { ThemeContext } from "./contexts/ThemeContext"

//with Context
export default function ContextApp02() {

    const [isDark, setIsDark] = useState(false)

    return (
      <ThemeContext.Provider value={{ isDark, setIsDark }}>
        <Page />
      </ThemeContext.Provider>
    );
}