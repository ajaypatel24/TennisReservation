import React from 'react'
import { Form, Col, Row, Card, Button, Alert, Spinner } from 'react-bootstrap'
import './styles.css'
import '../BookingComponent/Bookings.css'
import DatePicker from 'react-datepicker'
import ParkInformationCard from '../CardComponent/ParkInformation/ParkInformationCard'
import TimePicker from 'react-time-picker'
import WeatherCardComponent from '../CardComponent/Weather/WeatherCardComponent'
export default class ReservationComponent extends React.Component {
    constructor(props) {
        super(props);


        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.refreshFiles = this.refreshFiles.bind(this);
        this.handleDateChange = this.handleDateChange.bind(this);
        this.handleTimeChange = this.handleTimeChange.bind(this);

        this.state = {
            Day: "",
            Park: "",
            StartTime: "",
            EndTime: "",
            Categories: "",
            Month: "",
            t: "",
            p: "",
            SubResponse: "",
            ParkData: "",
            timeVal: "",
            Loading: false
        };
    }

    async handleSubmit(e) {
        e.preventDefault();

        this.setState({ Reserving: true })
        var res = this.state.p.newDate.split("-")
        var Day = res[2];
        var Year = res[0];
        var Month = res[1];
        var choice = "";
        switch (Month) {
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

        var api = '/Run/' + this.state.Park + '/' + Day + '/' + choice + '/' + this.state.StartTime + '/' + this.state.EndTime

        const response = await fetch(api)
        const body = await response.json();

        this.setState({ SubResponse: body })

        await fetch('/NewReservation/', {
            method: 'POST',
            body: JSON.stringify(body),
            headers: {
                'Content-type': "application/json"
            }
        })
            .then(function (t) {

            })


        this.setState({ Reserving: false })


    }


    refreshFiles(e) {
        fetch("/VerificationEmail/")
            .then(response => response.json())

    }



    handleChange(e) {
        this.setState({
            [e.target.name]: e.target.value
        });

        this.setState({ SubResponse: "" })

    }

    async handleTimeChange(time) {
        await this.setState({
            timeVal: time
        });
    }

    async handleDateChange(date) {
        await this.setState({
            t: date
        });


        await fetch("/Changedate/" + this.state.t)
            .then(response => response.json())
            .then(data => this.setState({ p: data }))


    }

    render() {


        let handleColor = time => {
            return time.getHours() > 12 ? "text-success" : "text-error";
        };
        return (


            <Card style={{ width: '100%', height: '100%' }}>

                <Card.Header id="CardHeader"><h2>Reserve</h2></Card.Header>
                <Card.Body>

                    <Form>

                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Row>
                                    <Form.Group as={Col} lg="5" >
                                        <Form.Label>Park</Form.Label>
                                        <Form.Control
                                            as="select"
                                            name="Park"
                                            required
                                            type="text"
                                            placeholder="Business Name"
                                            onChange={this.handleChange}
                                            value={this.state.Park}>

                                            <option>Select Park</option>
                                            <option value="marcel">Marcel-Laurin</option>
                                            <option value="marl">Marlborough</option>
                                            <option value="noel">Noel-Sud</option>
                                        </Form.Control>
                                    </Form.Group>

                                    <Form.Group as={Col} >
                                        <Form.Label>Start Time</Form.Label>
                                        <Form.Control
                                            name="StartTime"
                                            required
                                            type="text"
                                            placeholder="Start Time"
                                            onChange={this.handleChange}
                                            value={this.state.StartTime} />
                                        <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                                    </Form.Group>

                                    <Form.Group as={Col}>
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

                                    </Form.Group>

                                </Form.Row>

                                <Form.Row>
                                    <Form.Group as={Col}>
                                        <DatePicker
                                            selected={this.state.t}
                                            onChange={this.handleDateChange}
                                            dateFormat="yyyy/MM/dd"
                                            minDate={new Date()}
                                            timeClassName={handleColor}
                                            inline
                                        />
                                    </Form.Group>

                                    <Form.Group as={Col} lg="7">

                                        <ParkInformationCard name={this.state.Park}></ParkInformationCard>

                                    </Form.Group>
                                </Form.Row>




                            </Form.Group>









                            <Form.Group as={Col} lg="4">
                                <WeatherCardComponent></WeatherCardComponent>
                            </Form.Group>

                        </Form.Row>




                        <Form.Row>
                            <Form.Group as={Col} lg="6">
                                {
                                    this.state.SubResponse === ""
                                        ?
                                        this.state.Reserving === true
                                            ?

                                            <Alert variant='primary'>
                                                <Row>
                                                    <Spinner animation="border" size="sm" />
                                                    <p>  Reserving court in {this.state.Park} between {this.state.StartTime}:00 and {this.state.EndTime}:00</p>
                                                </Row>
                                            </Alert>

                                            :
                                            <p></p>
                                        :
                                        this.state.SubResponse.Status === "Successfully reserved"
                                            ?
                                            <Alert variant='success'>{this.state.SubResponse.Status} court {this.state.SubResponse.court} on {this.state.SubResponse.date} at {this.state.SubResponse.time} </Alert>
                                            :
                                            <Alert variant='danger'>Failed to reserve court between {this.state.StartTime}:00 and {this.state.EndTime}:00</Alert>


                                }
                            </Form.Group>
                        </Form.Row>

                        <Button onClick={this.handleSubmit}>Submit</Button>


                    </Form>

                </Card.Body>

            </Card>


        );
    };
}
