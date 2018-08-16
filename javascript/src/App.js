import React, { Component } from 'react';
import PropTypes from 'prop-types';
import * as UrlService from './services/url';
import logo from './logo.svg';
import './App.css';

class App extends Component {

  static propsTypes = {
    userInfo: PropTypes.array
  }

  constructor(props){
    super(props);

    this.state = {
      userInfo: [],
    }
  }

  getHome(){
    UrlService.getHome()
      .then(r => r.json())
      .then(userInfo => {
        console.log('userInfo', userInfo)
        this.setState({userInfo : userInfo})
      }) 
  }

  componentDidMount(){
    console.log("tentative de connexion en cours")
    this.getHome();
  }
  
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
      </div>
    );
  }
}

export default App;
