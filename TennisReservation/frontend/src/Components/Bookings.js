import React, { useState, Component } from 'react';
import { Document, Page, pdfjs } from 'react-pdf';


export default class Bookings extends React.Component{
  constructor(props) {
    super(props);


    this.handleChange = this.handleChange.bind(this);

    
    this.state = {
        url: "/confirmation/",
        pdf: "",
        combo: ""
    };
}

handleChange = () => {
  this.setState({pdf: "IC3-108639-01.pdf"});
  this.setState({combo: this.state.url + this.state.pdf});
  console.log(this.state.pdf);
  console.log(this.state.combo);
}
    render () {
      return (
        <div>

          <button onClick={this.handleChange}>switch</button>

          <a href={this.state.combo} download>Get Confirmation</a>

        </div>
      );
}

}