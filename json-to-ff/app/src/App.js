import React, { Component } from 'react'
import './App.css';

const Field = props => ( 
  <>
    <label for={props.value}>{props.value}: </label>
    <input name={props.value}/>
    <br/>
    <br/>
  </>

)

class App extends Component {
  initalState = {
    keys:[],
    json: [],
    delimeter: null
  }

  state=this.initalState

  handlePaste = e => {
    const data = e.clipboardData.getData('text')
    this.parseJson(data)
  }

  parseJson = data =>{
    try {
      const json = JSON.parse(data);
      let keys = [];
      for(let k in json[0]){
        keys.push(k)
      }

      let updatedState = {
        keys:keys,
        json:JSON.stringify(json, null, 2)
      }
      this.setState(updatedState);
    }
    catch (error) {
      throw "Invalid JSON string sent"
    }
  }

  handleChange = e =>{
    const {name, value} = e.target
    this.setState({delimeter: value})
  }

  render() {
    const keys = this.state.keys
    return (
      <div className="wrapper">
        <div className="editor" onPaste={this.handlePaste}>
          <h2>Example Json/ Schema</h2>
          <pre>
            {this.state.json}
          </pre>
        </div>
        <div className="two">
          <h2>Mapping</h2>
          
            <label htmlFor="delimeter">delimeter: </label>
            <input name="delimeter" onChange={this.handleChange}></input> <br/><br/>
            {
              keys.map((value,index) =>{
                return (
                  <Field value={value} />
                )
              })
            }
        </div>
        <div className="three">
          <h2>Output</h2>
          {this.state.delimeter}
        </div>
      </div>
    );
  }
}

export default App;
