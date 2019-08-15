import React, { Component } from 'react';
import './signup.css';
import axios from 'axios';

export default class SignUp extends Component {
  state = { signUpError: "" };

  componentWillMount() {
    document.title="Sign up - CalculationX";
  }

  handleSignUpSubmit(event) {
    event.preventDefault();
    let username = this.refs.username.value.trim();
    let password = this.refs.password.value.trim();
    let rPassword = this.refs.rPassword.value.trim();
    if (password != rPassword) {
      this.setState({ signUpError: "Passwords don't match" });
      return;
    }

    if (password.length < 6) {
      this.setState({ signUpError: "Password should have atlease 6 characters" });
      return;
    }

    let firstName = this.refs.firstName.value.trim();
    let lastName = this.refs.lastName.value.trim();
    let email = this.refs.email.value.trim();

    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!re.test(String(email).toLowerCase())) {
      this.setState({ signUpError: "Enter valid email" });
      return;
    }

    let date1 = new Date();
    let date2 = new Date(date1.getUTCFullYear(), date1.getUTCMonth(), date1.getUTCDate(), date1.getUTCHours(), date1.getUTCMinutes(), date1.getUTCSeconds(), date1.getMilliseconds());
    let createdTime = date2.getTime();
    let request = {
      "firstName": firstName,
      "lastName": lastName,
      "username": username,
      "password": password,
      "email": email,
      "createdTime": createdTime
    };
    axios.post(`http://localhost:30001/user/`, request)
      .then(res => {
        console.log(res);
        console.log(res.data);
        this.props.handler(false, true, false, false, "", "", "Successfully signed up, Please login");
      })
      .catch(function (error) {
        this.setState({ signUpError: error.response.data });
      });
  }

  showSignIn(event) {
    event.preventDefault();
    this.props.handler(false, true, false, false, "", "", "");
  }

  render() {
    return (
      <form onSubmit={this.handleSignUpSubmit.bind(this)}>
        <div className="signup-container">
          <div className="signup-content">
            <div className="signup-block">
              <div className="signup-company-name">CalculationX</div>
              <div className="signup-center-content">
                <input className="signup-input" type="text" placeholder="First name" ref="firstName" />
                <input className="signup-input" type="text" placeholder="Last name" ref="lastName" />
                <input className="signup-input" type="text" placeholder="Email" ref="email" />
                <input className="signup-input" type="text" placeholder="Username" ref="username" />
                <input className="signup-input" type="password" placeholder="Password" ref="password" />
                <input className="signup-input" type="password" placeholder="Confirm password" ref="rPassword" />
                <button className="sign-up-button" type="submit" onSubmit={this.handleSignUpSubmit.bind(this)}>Sign Up</button>
                <label className="sign-up-sign-in" onClick={this.showSignIn.bind(this)}>Sign In</label>
                <label className="sign-up-error">{this.state.signUpError}</label>
              </div>
            </div>
          </div>
        </div>
      </form>
    );
  }
};