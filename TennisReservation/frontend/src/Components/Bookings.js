import React, { useState, Component } from 'react';
import {Col ,Row} from 'react-bootstrap'
import DatePicker from 'react-datepicker'
import "react-datepicker/dist/react-datepicker.css"
import './Bookings.css'

export default class Bookings extends React.Component{
  constructor(props) {
    super(props);
    
    this.state = {
        url: "/confirmation/",
        pdf: "",
        combo: "",
        TableData: [],
        date: ""
    };

    
}

componentDidMount() {
  fetch("/VerificationEmail/")
  .then(response => response.json())
  .then(data => console.log(data))
  
  fetch("/Reservation")
  .then(response => response.json())
  .then(data => this.setState({TableData: data}))
}

handleChange = date => {
  this.setState({
    date: date
  });

  console.log(this.state.date)
};


    render () {
      return (
        <div className="Container">
          <Col lg="12">
          <h3> Current Reservations </h3>
          <DatePicker 
            selected={this.state.date}
            onChange={this.handleChange}
          />

          <table id = "reservations">
            <tbody>
            <tr>
              <th>Reservation File</th>
              <th>Reservation Date</th>
              <th>Reservation Time</th>
              <th>Court Number</th>
              <th>Park</th>
            </tr>
          {Object.keys(this.state.TableData).map((keyName) => (

            <tr>
            
            
              <td><a href={this.state.url + this.state.TableData[keyName][0]} download> {this.state.TableData[keyName][0]} </a></td>
            
            
              <td><p> {this.state.TableData[keyName][1]} </p></td>
          
         
              <td><p> {this.state.TableData[keyName][2]} </p></td>
          
            
              <td><p> {this.state.TableData[keyName][3]} </p></td>

              <td><p> {this.state.TableData[keyName][4]} </p></td>

            
            
            </tr>
            
          ))}
          </tbody>
          </table>
          </Col>
        </div>
      );
}

}