import React from 'react';
import './App.css';
import Login from './login';
import SignUp from './signup';
import Calculation from './calculation';

class App extends React.Component {

  state = { 
    signedIn: localStorage.hasOwnProperty('signedIn')? localStorage.getItem('signedIn') === 'true' : false
    , showLogin: localStorage.hasOwnProperty('showLogin')? localStorage.getItem('showLogin') === 'true' : true
    , showSignUp: localStorage.hasOwnProperty('showSignUp')? localStorage.getItem('showSignUp') === 'true' : false
    , showCalculation: localStorage.hasOwnProperty('showCalculation')? localStorage.getItem('showCalculation') === 'true' : false
    , username: localStorage.getItem('username') || ""
    , authorization: localStorage.getItem('authorization') || ""
    , signInMessage: ""
  };
  
  handler = (signedIn, showLogin, showSignUp, showCalculation, username, authorization, signInMessage) => {
    localStorage.setItem('signedIn', signedIn);
    localStorage.setItem('showLogin', showLogin);
    localStorage.setItem('showSignUp', showSignUp);
    localStorage.setItem('showCalculation', showCalculation);
    localStorage.setItem('username', username);
    localStorage.setItem('authorization', authorization);
    localStorage.setItem('signInMessage', signInMessage);
    
    this.setState({
      signedIn: signedIn
      , showLogin: showLogin
      , showSignUp: showSignUp
      , showCalculation: showCalculation
      , username: username
      , authorization: authorization
      , signInMessage: signInMessage
    });
  }

  render() {
    return (
      <div className="main-app">
        {!this.state.signedIn && this.state.showLogin && <Login handler={this.handler} signInMessage={this.state.signInMessage} />}
        {!this.state.signedIn && this.state.showSignUp && <SignUp handler={this.handler} />}
        {this.state.signedIn && this.state.showCalculation && <Calculation handler={this.handler} username={this.state.username} authorization={this.state.authorization} />}
      </div>
    );
  }
}

export default App;
