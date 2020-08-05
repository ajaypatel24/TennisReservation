import React from 'react'
import {Form, Col, Row, Card, Button, Jumbotron} from 'react-bootstrap'


export default class ReservationComponent extends React.Component {
    constructor(props) {
        super(props);


        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

        this.state = {
            Day: "",
            Park: "",
            StartTime: "",
            EndTime: "",
            Categories: []
        };
    }

    async handleSubmit (e) {
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
        console.log(body);
        for (var x in body ) {
        this.setState({Categories: body[x], isLoading: false});
         }       
    }

    handleChange(e) {
        this.setState({
            [e.target.name]: e.target.value
        });
    }
    

    render() {
        return (
            <Form.Group controlId="validationCustom01">
            <Row>
            <Col lg="6">
                <Form.Label>Start Time</Form.Label>
                <Form.Control
                    name="StartTime"
                    required
                    type="text"
                    placeholder="Business Name"
                    onChange={this.handleChange}
                    value={this.state.StartTime}/>
                <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            </Col>

            <Col lg="6">

                <Form.Label>End Time</Form.Label>
                <Form.Control
                    name="EndTime"
                    required
                    type="text"
                    placeholder="City"
                    onChange={this.handleChange}
                    value={this.state.EndTime}
                    pattern="^[a-zA-Z]+$"
                />

                <Form.Control.Feedback>Looks good!</Form.Control.Feedback>

            </Col>
            </Row>

            <Row>
            <Col lg="6">
                <Form.Label>Park</Form.Label>
                <Form.Control
                    name="Park"
                    required
                    type="text"
                    placeholder="Business Name"
                    onChange={this.handleChange}
                    value={this.state.Park}/>
                <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            </Col>

            <Col lg="6">

                <Form.Label>Day</Form.Label>
                <Form.Control
                    name="Day"
                    required
                    type="text"
                    placeholder="City"
                    onChange={this.handleChange}
                    value={this.state.Day}
                    pattern="^[a-zA-Z]+$"
                />

                <Form.Control.Feedback>Looks good!</Form.Control.Feedback>

            </Col>
            </Row>

            <Button onClick={this.handleSubmit}>Submit</Button>

            <Jumbotron>{this.state.Categories}</Jumbotron>
        
        </Form.Group>
        
        );
    };






}
