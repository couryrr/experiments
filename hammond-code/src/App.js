import React from 'react';

import './App.css';

export default class App extends React.Component {

  state = {
    message: null
  }

  initMessage = () =>{
    const message = [];
    let pa = 0;
    let p1 = 0;
    let p2 = 0;
    let p3 = 0;
    let p4 = 0;
    
    for(let x = 0; x <= 3; x++){
      message[x] = [];

      for(let y = 0; y <= 3; y++){
        
        if(x===0 && y === 0) {message[x][y] = 0; continue;}
        if(x===0 && y === 1) {message[x][y] = 0; continue;}
        if(x===0 && y === 2) {message[x][y] = 0; continue;}
        if(x===1 && y === 0) {message[x][y] = 0; continue;}
        if(x===2 && y === 0) {message[x][y] = 0; continue;}
        

        let value = Math.floor((Math.random() * 2));
        
        message[x][y] = value;
        
        pa += value;
        if(y === 1 || y === 3){ p1 += value;}

        if(y === 2 || y === 3){ p2 += value;}
        if(x === 1 || x === 3){ p3 += value;}
        if(x === 2 || x === 3){ p4 += value;}
      }
    }

    
    if(p1%2 === 0){
      message[0][1] = 0;
    } else {
      message[0][1] = 1;
      pa += 1;
    }

    if(p2%2 === 0){
      message[0][2] = 0;
    } else {
      message[0][2] = 1;
      pa += 1;
    }

    if(p3%2 === 0){
      message[1][0] = 0;
    } else {
      message[1][0] = 1;
      pa += 1;
    }

    if(p4%2 === 0){
      message[2][0] = 0;
    } else {
      message[2][0] = 1;
      pa += 1;
    }   

    if(pa%2 === 0){
      message[0][0] = 0;
    } else {
      message[0][0] = 1;
    }
  
    this.setState({message : [...message]})
  }

  validateMessage = () => {
    const {message} = this.state;
    validateMessage(message);
  }

  updateMessage = () =>{
    
  }

  componentDidMount = () => {
    console.log("Setting message")
    this.initMessage();
  }

  render() {
  return (
    <div className="App">
      <table>
        <thead>
          <tr>
            <th></th>
            <th>0</th>
            <th>1</th>
            <th>2</th>
            <th>3</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th>0</th>
            <td>
              {this.state.message !== null &&
                this.state.message[0][0]
              }
            </td>
            <td>{this.state.message !== null &&
                this.state.message[0][1]
              }</td>
            <td>{this.state.message !== null &&
                this.state.message[0][2]
              }</td>
            <td>{this.state.message !== null &&
                this.state.message[0][3]
              }</td>
          </tr>
          <tr>
            <th>1</th>
            <td>
              {this.state.message !== null &&
                this.state.message[1][0]
              }
            </td>
            <td>{this.state.message !== null &&
                this.state.message[1][1]
              }</td>
            <td>{this.state.message !== null &&
                this.state.message[1][2]
              }</td>
            <td>{this.state.message !== null &&
                this.state.message[1][3]
              }</td>
          </tr>
          <tr>
            <th>2</th>
            <td>
              {this.state.message !== null &&
                this.state.message[2][0]
              }
            </td>
            <td>{this.state.message !== null &&
                this.state.message[2][1]
              }</td>
            <td>{this.state.message !== null &&
                this.state.message[2][2]
              }</td>
            <td>{this.state.message !== null &&
                this.state.message[2][3]
              }</td>
          </tr>
          <tr>
            <th>3</th>
            <td>
              {this.state.message !== null &&
                this.state.message[3][0]
              }
            </td>
            <td>{this.state.message !== null &&
                this.state.message[3][1]
              }</td>
            <td>{this.state.message !== null &&
                this.state.message[3][2]
              }</td>
            <td>{this.state.message !== null &&
                this.state.message[3][3]
              }</td>
          </tr>
        </tbody>
      </table>
      <button onClick={this.validateMessage}>Validate</button>
    </div>
  );
  }
}


const validateMessage = (message) => {
  let pa = 0;
  let p1 = 0;
  let p2 = 0;
  let p3 = 0;
  let p4 = 0;
  
  for(let x = 0; x <= 3; x++){
    for(let y = 0; y <= 3; y++){
      const value = message[x][y];        
      pa += value;
      
      if(y === 1 || y === 3){ p1 += value;}
      if(y === 2 || y === 3){ p2 += value;}
      if(x === 1 || x === 3){ p3 += value;}
      if(x === 2 || x === 3){ p4 += value;}

    }
  }

  const paCheck = pa%2===0;
  const p1Check = p1%2===0;
  const p2Check = p2%2===0;
  const p3Check = p3%2===0;
  const p4Check = p4%2===0;

  let binary = `${+p4Check}${+p3Check}${+p2Check}${+p1Check}`;

  console.log(parseInt(binary,2));
  
  
}