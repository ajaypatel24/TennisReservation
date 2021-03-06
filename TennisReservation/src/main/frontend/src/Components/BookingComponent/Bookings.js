import React from 'react';
import {Col, Modal, Spinner} from 'react-bootstrap'
import DatePicker from 'react-datepicker'
import "react-datepicker/dist/react-datepicker.css"
import './Bookings.css'
import MaterialTable from 'material-table'

export default class Bookings extends React.Component{
  constructor(props) {
    super(props);
    
    this.state = {
        url: "/confirmation/",
        pdf: "",
        combo: "",
        TableData: [],
        t: "",
        p: [],
        range1: "",
        range2: "",
        rangeArg1: "",
        rangeArg2: "",
        switch: -1,
        selectedOption: "option1",
        noresult: "",
        Downloading: true
    };

    this.handleChange = this.handleChange.bind(this);
    this.getRange = this.getRange.bind(this);
    this.handleOptionChange = this.handleOptionChange.bind(this);
    
}

componentDidMount() {
  
  fetch("/VerificationEmail/")
  .then(response => response.json())
  .then(data => this.setState({Downloading: false}))

  
}


async handleChange(date) {
  await this.setState({
    t: date,
    switch: 0
  });
  await fetch("/Changedate/" + this.state.t)
  .then(response => response.json())
  .then(data => this.setState({p: data}))

  await fetch("/ReservationDate/" + this.state.p.newDate)
  .then(response => response.json())
  .then(data => this.setState({TableData: data}))

  if (this.state.TableData.length === 0) {
      await this.setState({
        noresult: "No Reservations on "
      })
  } else {
    await this.setState({
      noresult: "Reservations on "
    })
  }
  

};

async changer(date, name) {
  await this.setState({
    [name]: date
  })
};

handleOptionChange(e) {
  this.setState({
    selectedOption: e.target.value
  });

  this.setState({
    TableData: [],
    switch: -1,
    range1: "",
    range2: "",
    t: ""


  })
}

handleRowClick = (event, rowData) => {
  var link = this.state.url + rowData.confirmationPDF;
  window.open(link)
}
async getRange() {
  await this.setState ({switch: 1})
  await fetch("/Changedate/" + this.state.range1)
  .then(response => response.json())
  .then(data => this.setState({rangeArg1: data}))

  await fetch("/Changedate/" + this.state.range2)
  .then(response => response.json())
  .then(data => this.setState({rangeArg2: data}))

  await fetch("/ReservationRange/" + this.state.rangeArg1.newDate + "/" + this.state.rangeArg2.newDate)
  .then(response => response.json())
  .then(data => this.setState({TableData: data}))

  if (this.state.TableData.length === 0) {
    await this.setState({
      noresult: "No Reservations between "
    })
  } else {
  await this.setState({
    noresult: "Reservations between "
  })
}

}

    render () {
      return (
        <div className="Container">
          <Col lg="12">
          <h3> Current Reservations </h3>

         
            <Modal size="lg"
              aria-labelledby="contained-modal-title-vcenter"
              centered show={this.state.Downloading}>
              <Modal.Body><Spinner animation="border" /> Downloading New Confirmations</Modal.Body>
            </Modal>
          

        <div>
          {
            this.state.switch === 0 ? <h4>{this.state.noresult} {this.state.p.newDate}</h4>
            : this.state.switch === 1 ? <h4>{this.state.noresult} {this.state.rangeArg1.newDate} & {this.state.rangeArg2.newDate}</h4>
            : <h4></h4>
          }  
          <h4></h4>
          </div>

          <form>
    <div className="radio">
      <label>
        <input type="radio" value="option1" 
                      checked={this.state.selectedOption === 'option1'} 
                      onChange={this.handleOptionChange} />
        Reservations On a Specific Date
      </label>
    </div>
    <div className="radio">
      <label>
        <input type="radio" value="option2" 
                      checked={this.state.selectedOption === 'option2'} 
                      onChange={this.handleOptionChange} />
        Reservation in a Range of Dates
      </label>
    </div>
  </form>

  { this.state.selectedOption === "option1" ?
          <DatePicker 
            selected={this.state.t}
            onSelect={this.handleChange}
            dateFormat="yyyy/MM/dd"
          />

          : 

          <div>
          <DatePicker 
            name="range1"
            selected={this.state.range1}
            onSelect={(date) => this.changer(date,"range1")}
            dateFormat="yyyy/MM/dd"
          />
          <DatePicker 
            name="range2"
            selected={this.state.range2}
            onSelect={(date) => this.changer(date,"range2")}
            dateFormat="yyyy/MM/dd"
          />
          <button onClick={this.getRange}> Get Reservations </button>
          </div>

        }

        <MaterialTable
          columns={[
            { title: 'Reservation File', field: 'confirmationPDF' },
            { title: 'Reservation Date', field: 'date' },
            { title: 'Reservation Time', field: 'time' },
            { title: 'Court Number', field: 'court'},
            { title: 'Park', field: 'park' }
          ]}
          data = {this.state.TableData}
          title="Reservations"
          onRowClick={this.handleRowClick}
          />

          </Col>
        </div>
      );
}

}