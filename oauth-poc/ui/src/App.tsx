import { useState, useEffect } from 'react'
import './App.css'

function App() {
  const [user, setUser] = useState("")

  useEffect(() => {
    const user = getUserFromCookie()
    setUser(user?.trim() === undefined ? "0" : user)

    const queryParams = new URLSearchParams(window.location.search)
    const code = queryParams.get("code")
    console.log(code)

    // This needs to be on a server :(
    if(code != null){
      console.log(code)
      fetch("https://github.com/login/oauth/access_token", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          client_id:"1091c1a7e7b8bea8f371",
          client_secret:"eacd5cb6259e2a09a3feadf8c61f466050423441",
          code:code
        })
      }).then((data) => {
        console.log(data); // JSON data parsed by `data.json()` call
      }).catch((error) => console.log(error));
      //window.location.replace('http://localhost:5173');
    }
  }, [])

  const getCookies = () => {
    const currentCookies = document.cookie
    return currentCookies.split("; ")
  }
  
  const getUserFromCookie = () => {
    const currentCookies = getCookies()

    const cachedUserName = currentCookies.find(row => row.trim().startsWith("user="))
    
    return cachedUserName?.split("=")[1]
  }

  const logout = () => {
    console.log("Logging out")
    document.cookie="user=0"
    setUser("0")
  }
  const callNc = () => {
    console.log("Calling NC")
    fetch("http://localhost:8080", {
        method: "GET",
        headers: {
          "Content-Type": "application/json"
        }
      }).then((data) => {
        console.log(data); // JSON data parsed by `data.json()` call
      }).catch((error) => console.log(error));
    
  }

  return (
    <div className="App">
      <h1>Github OAuth Login</h1>
      <div className="card" >
        <button onClick={() => callNc()}>NC Call</button>

        <a href='https://github.com/login/oauth/authorize?scope=user:email&client_id=1091c1a7e7b8bea8f371' style={{display: user == "0" ? "block" : "none"}}>Login</a>
        <button style={{display: user != "0" ? "block" : "none"}} onClick={() => logout()}>Log Out</button>
      </div>
    </div>
  )
}

export default App
