import React, { useState, Component } from 'react';
import {Col ,Row} from 'react-bootstrap'
import DatePicker from 'react-datepicker'
import "react-datepicker/dist/react-datepicker.css"
import './Bookings.css'
import Moment from 'moment'

export default class Bookings extends React.Component{
  constructor(props) {
    super(props);
    
    this.state = {
        url: "/confirmation/",
        pdf: "",
        combo: "",
        TableData: [],
        t: "",
        p: []
    };

    this.handleChange = this.handleChange.bind(this);
    
}

componentDidMount() {
  fetch("/VerificationEmail/")
  .then(response => response.json())
  .then(data => console.log(data))
  
  fetch("/ReservationDate/2020-08-16")
  .then(response => response.json())
  .then(data => this.setState({TableData: data}))
}

changeDate = () => {
  console.log(this.state.t)
  
  
  
}

async handleChange(date) {
  await this.setState({
    t: date
  });
  console.log(this.state.t)
  await fetch("/Changedate/" + this.state.t)
  .then(response => response.json())
  .then(data => this.setState({p: data}))
  console.log(this.state.t)

  await fetch("/ReservationDate/" + this.state.p.newDate)
  .then(response => response.json())
  .then(data => this.setState({TableData: data}))

  console.log(this.state.TableData)

};


    render () {
      return (
        <div className="Container">
          <Col lg="12">
          <h3> Current Reservations </h3>
          <h4>{this.state.p.newDate}</h4>
          <DatePicker 
            selected={this.state.date}
            onSelect={this.handleChange}
            dateFormat="yyyy/MM/dd"
          />

          <button onClick={this.changeDate}> change </button>
      

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
              
              <td><a href={this.state.url + this.state.TableData[keyName].confirmationPDF} download> {this.state.TableData[keyName].confirmationPDF} </a></td>
            
            
              <td><p> {this.state.TableData[keyName].date} </p></td>
          
         
              <td><p> {this.state.TableData[keyName].time} </p></td>
          
            
              <td><p> {this.state.TableData[keyName].court} </p></td>

              <td><p> {this.state.TableData[keyName].park} </p></td>

            </tr>
            
          ))}

          </tbody>
          </table>
          </Col>
        </div>
      );
}

}