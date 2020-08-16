import React from 'react'
import { Form, Col, Row, Card, Button } from 'react-bootstrap'
import './styles.css'
import '../BookingComponent/Bookings.css'

export default class ReservationComponent extends React.Component {
    constructor(props) {
        super(props);


        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.refreshFiles = this.refreshFiles.bind(this);

        this.state = {
            Day: "",
            Park: "",
            StartTime: "",
            EndTime: "",
            Categories: ""
        };
    }

    async handleSubmit(e) {
        e.preventDefault();
        var api = '/Run/' + this.state.Park + '/' + this.state.Day + '/' + this.state.StartTime + '/' + this.state.EndTime
        this.setState({
            Day: "",
            Park: "",
            StartTime: "",
            EndTime: ""
        });
        console.log(api);
        const response = await fetch(api);
        const body = await response.json();

        console.log(response);
        console.log(body);
        console.log(JSON.stringify(body))

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


    render() {
        return (
            <div id="ContainerReserve">
                
                <Card id="Card">
                    <Card.Header id="CardHeader">Reserve</Card.Header>
                    <Card.Body>
                    <Form.Group id="ReserveForm">

                    <Row>
                        <Col lg="12">
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
                    </Row>

                    <br/>
                    
                    <Row>

                        <Col lg="12">
                            <Row>
                                <Col lg="3">
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
                                <Col lg="3">
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
                                <Col lg="6">

                            <Form.Label>Day</Form.Label>
                            <Form.Control
                                name="Day"
                                required
                                type="text"
                                placeholder="Day of Month"
                                onChange={this.handleChange}
                                value={this.state.Day}
                                pattern="^[a-zA-Z]+$"
                            />

                            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>

                        </Col>
                            </Row>

                        </Col>
                    </Row>
                    <br/>
                    <Button onClick={this.handleSubmit}>Submit</Button>
                    </Form.Group>
                
                    </Card.Body>
                
                    <p>{this.state.Categories}</p>

                </Card>
                
            </div>
            
        );
    };






}