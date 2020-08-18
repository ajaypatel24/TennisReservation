import React from 'react'
import { Form, Col, Row, Card, Button, Alert } from 'react-bootstrap'
import './styles.css'
import '../BookingComponent/Bookings.css'
import DatePicker from 'react-datepicker'

export default class ReservationComponent extends React.Component {
    constructor(props) {
        super(props);


        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.refreshFiles = this.refreshFiles.bind(this);
        this.handleDateChange = this.handleDateChange.bind(this);

        this.state = {
            Day: "",
            Park: "",
            StartTime: "",
            EndTime: "",
            Categories: "",
            Month: "",
            t: "",
            p: "",
            SubResponse: ""
        };
    }

    async handleSubmit(e) {
        e.preventDefault();

        var res = this.state.p.newDate.split("-")
        var Day = res[2];
        var Year = res[0];
        var Month = res[1];
        var choice = "";
        switch(Month) {
            case '01':
                choice = 'janvier';
                break;
            case '02':
                choice = 'février'
                break;
            case '03':
                choice = 'mars'
                break;
            case '04':
                choice = 'avril'
                break;
            case '05':
                choice = 'mai'
                break;
            case '06':
                choice = 'juin'
                break;
            case '07':
                choice = 'juillet'
                break;
            case "08":
                choice = 'août'
                break;
            case '09':
                choice = 'septembre'
                break;
            case '10':
                choice = 'octobre'
                break;
            case '11':
                choice = 'novembre'
                break;
            case '12':
                choice = 'decembre'
                break;

        }

        var api = '/Run/' + this.state.Park + '/' + Day + '/' + choice +'/'+ this.state.StartTime + '/' + this.state.EndTime
        this.setState({
            Day: "",
            Park: "",
            StartTime: "",
            EndTime: "",
            Month: ""
        });
        console.log(api);

        const response = await fetch(api)
        const body = await response.json();

        console.log(body)
        this.setState({SubResponse: body})

        await fetch('/NewReservation/', {
            method: 'POST',
            body: JSON.stringify(body),
            headers: {
                'Content-type': "application/json"
            }
        })
        .then(function (t) {
            console.log(t);
        })


    }

    refreshFiles(e) {
        fetch("/VerificationEmail/")
            .then(response => response.json())
            .then(data => console.log(data))
        

        
    }
       
    

    handleChange(e) {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    async handleDateChange(date) {
            await this.setState({
              t: date
            });

            console.log(this.state.t)

            
            await fetch("/Changedate/" + this.state.t)
            .then(response => response.json())
            .then(data => this.setState({p: data}))

            
            
            
    }

    


    render() {

        let handleColor = time => {
            return time.getHours() > 12 ? "text-success" : "text-error";
          };
        return (
           
                
                <Card style={{ width: '100%', height:'100%'}}>
                    <Card.Header id="CardHeader">Reserve</Card.Header>
                    <Card.Body>
                    <Form.Group id="ReserveForm">

                  
                  

                    <Row>
                        <Col>

                        <DatePicker 
                        showTimeSelect
                        selected={this.state.t}
                        onSelect={this.handleDateChange}
                        dateFormat="yyyy/MM/dd"
                        timeClassName={handleColor}
                        inline
                    />
                        </Col>
                        <Col>
                                <Col>
                                    <Form.Label>Start Time</Form.Label>
                                    <Form.Control
                                        name="StartTime"
                                        required
                                        type="text"
                                        placeholder="Start Time"
                                        onChange={this.handleChange}
                                        value={this.state.StartTime} />
                                    <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                                </Col>
                                <Col>
                                    <Form.Label>End Time</Form.Label>
                                    <Form.Control
                                        name="EndTime"
                                        required
                                        type="text"
                                        placeholder="End Time"
                                        onChange={this.handleChange}
                                        value={this.state.EndTime}
                                        pattern="^[a-zA-Z]+$"
                                    />
                                    <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                                </Col>
                                <Col>
                            <Form.Label>Park</Form.Label>
                            <Form.Control 
                                name="Park"
                                required
                                type="text"
                                placeholder="Business Name"
                                onChange={this.handleChange}
                                value={this.state.Park} >
                                
                                </Form.Control>
                            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                        </Col>


                        </Col>

                            
                    </Row>


                    <Row>
                        <Col>
                        {
                            this.state.SubResponse != ""
                        ?
                        <Alert variant='success'>{this.state.SubResponse.Status} court {this.state.SubResponse.court} on {this.state.SubResponse.date} at {this.state.SubResponse.time} </Alert>
                        :
                        null
                        }
                        </Col>
                    </Row>
                    

                     
                           

                  
                    <br/>
                    <Button onClick={this.handleSubmit}>Submit</Button>
                    </Form.Group>
                
                    </Card.Body>
                
                    <p>{this.state.Categories}</p>

                </Card>
                
          
            
        );
    };






}
