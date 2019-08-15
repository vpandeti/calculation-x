import React, { Component } from 'react';
import './calculation.css';
import axios from 'axios';
import BinaryTree from './binary-tree.png'

export default class Calculation extends Component {
  state = { calculationError: "", calculationResult: "" };

  componentWillMount() {
    document.title="Calculation - CalculationX";
  }

  calculateLongestPath(event) {
    event.preventDefault();
    let input = "[" + this.refs.binaryTreeInput.value + "]";
    axios.post(`http://localhost:30001/binaryTree/longestPath`, input, { headers: { "Authorization": this.props.authorization, "Content-Type": "application/json" } })
      .then(response => {
        this.setState({ calculationError: "" });
        let result = "[" + response.data.result.join(" -> ") + "]";
        this.setState({ calculationResult: result });
      })
      .catch(error => {
        let message = "";
        if(error.response.status === 401) {
          message = "Authorization failed, Please login";
        } else {
          message = "Invalid input " + error.response.data;
        }
        console.log(error.response);
        this.setState({ calculationResult: "" });
        this.setState({ calculationError: message });
      });
  }

  logout(event) {
    event.preventDefault();
    this.props.handler(false, true, false, false, "", "", "Successfully logged out");
  }

  render() {
    return (
      <div className="calculation-container">
        <div className="calculation-top-strip">
          <div className="calculation-company-name">CalculationX</div>
          <div className="calculation-profile">
            <label className="calculation-username">{this.props.username}</label>
            <label className="calculation-logout" onClick={this.logout.bind(this)}>logout</label>
          </div>
        </div>

        <div className="calculation-center-content">
          <div className="calculation-image">
            <img className="binary-tree" src={BinaryTree} alt="Binary Tree"/>
          </div>
          <div className="calculation-input-container">
            <textarea className="calculation-input" placeholder='1,2,3,4,null,5,6' ref="binaryTreeInput" />
          </div>
          <div className="calculation-button-container">
            <button className="calculation-button" onClick={this.calculateLongestPath.bind(this)}>Calculate</button>
          </div>
          <div className="calculation-result-container">
            <label className="calculation-result">{this.state.calculationResult}</label>
          </div>
          <div className="calculation-error">
            <label>{this.state.calculationError}</label>
          </div>
        </div>
      </div>
    );
  }
};