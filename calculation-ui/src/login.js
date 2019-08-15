import React, { Component } from 'react';
import './login.css';
import axios from 'axios';

export default class Login extends Component {
  state = { signInError: "", signInMessage: this.props.signInMessage };

  componentWillMount() {
    document.title="Login - CalculationX";
  }

  handleSignInSubmit(event) {
    event.preventDefault();
    let username = this.refs.username.value.trim();
    let password = this.refs.password.value.trim();
    axios.post(`http://localhost:30001/user/${username}/login`, { "password": password })
      .then(res => {
        let authorization = ("Basic " + btoa(username + ":" + password));
        this.props.handler(true, false, false, true, username, authorization, "");
      })
      .catch(error => {
        this.setState({signInMessage: ""});
        this.setState({ signInError: "Username and password don't match"});
      })
  }

  showSignUp(event) {
    event.preventDefault();
    this.props.handler(false, false, true, false, "", "", "");
  }

  render() {
    return (
      <form onSubmit={this.handleSignInSubmit.bind(this)}>
        <div className="login-container">
          <div className="login-content">
            <div className="login-block">
              <div className="login-company-name">CalculationX</div>
              <div className="login-center-content">
                <input className="login-input" type="text" placeholder="Username" ref="username" />
                <input className="login-input" type="password" placeholder="Password" ref="password" />
                <button className="sign-in-button" type="submit" onSubmit={this.handleSignInSubmit.bind(this)}>Sign In</button>
              </div>
              <div className="signup-forgot-password">
                <label className="sign-up" onClick={this.showSignUp.bind(this)}>Sign Up</label>
                <label className="forgot-password">Forgot password?</label>
              </div>
              <div className="sign-in-error">
                <label>{this.state.signInError}</label>
                <label ref="signInMessage">{this.state.signInMessage}</label>
              </div>
            </div>
          </div>
        </div>
      </form>
    );
  }
};