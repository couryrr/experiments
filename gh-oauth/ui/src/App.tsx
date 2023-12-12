import { useState, useEffect } from 'react';
import './App.css'

function App() {
  const [user, setUser] = useState("")
  const [loggedIn, setLoggedIn] = useState(false)

  const login = () => {
    document.cookie = "user=couryrr;";
    document.cookie = "loggedIn=true";
    fetch('../data/users.json')
    .then(response => response.json())
    .then(json => console.log(json))
    setUser("couryrr");
    setLoggedIn(true);
  }

  const logout = () => {
    document.cookie = "user=couryrr;";
    document.cookie = "loggedIn=false";

    setUser("couryrr");
    setLoggedIn(false);
  }


  useEffect(() => {
    var allCookies = document.cookie;

    console.log(allCookies);

    if(allCookies != ""){
      const user = allCookies.split("; ").find((row) => row.startsWith("user="))
      ?.split("=")[1];
      
      console.log(user);

      const loggedIn = allCookies.split("; ").find((row) => row.startsWith("loggedIn="))
      ?.split("=")[1]?.toLowerCase() == 'true';

      console.log(loggedIn);

      if(user && loggedIn){
        setUser(user)
      }
    } 
  })

  return (
    <div className="App">
      <div className="card">

        {/*TODO: Make its own component*/}
        <div id="login-form" style={{ display: !loggedIn ? "block" : "none" }}>
            <label htmlFor='user_name'>User Name: </label>
            <input type="text" name="user_name" />

            <br />
            <label htmlFor='password'>Password: </label>
            <input type="password" name="password" />

            <br />
            <br />
            <button onClick={login}>Log In</button>
        </div>
       {/*TODO: Make its own component*/}
        <div id="user-profile" style={{ display: user && loggedIn ? "block" : "none" }}>
            <h1>Welcome, {user}</h1>
            <button onClick={logout}>Log Out</button>
        </div>
      </div>
    </div>
  )
}

export default App
        
