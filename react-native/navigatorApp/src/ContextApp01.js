import { useState } from "react"
import Page from "./components/Page"


export default function ContextApp01() {

    const [isDark, setIsDark] = useState(false)

    return(             
        <Page isDark={isDark} setIsDark={setIsDark}/>        
    )
}