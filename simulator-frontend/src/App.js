import logo from './logo.svg';
import React, { Component, useEffect, useState } from 'react';
import { Text, View } from "react-native";

class App extends Component {

  constructor(props) {
    super(props)

    this.state = {
      springMsg: "fail",
      flaskMsg: "fail"
    }
  }

  componentDidMount() {
    this.getMsgs();
  }

  getMsgs() {
    this.getSpring();
    this.getFlask();
  }

  getSpring() {
    fetch(
      'http://localhost:8080/spring',
      {
        method: 'GET',
        credentials: 'include'
      }
    )
      .then(response => {
        if (response.status == 200) {
          response.json().then(resp => {
            this.setState({
              springMsg: resp.msg
            })
          })
        }
      }
      ).catch(error => {
        // TODO error handling
      })
  }
  
  getFlask() {
    fetch(
      'http://localhost:5000/flask',
      {
        method: 'GET'
      }
    )
      .then(response => {
        if (response.status == 200) {
          response.json().then(resp => {
            this.setState({
              flaskMsg: resp.msg
            })
          })
        }
      }
      ).catch(error => {
        // TODO error handling
      })
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a>
        </header>
        <Text>{`\n`}</Text>
        {this.state.springMsg}
        <Text>{`\n`}</Text>
        {this.state.flaskMsg}
        <Text>{`\n`}</Text>
      </div>
    );
  }
}
export default App;