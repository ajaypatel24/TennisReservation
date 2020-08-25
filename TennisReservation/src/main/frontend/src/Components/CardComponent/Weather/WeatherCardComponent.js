import React from 'react'
import { Card , Row, Col} from 'react-bootstrap'
import '../Count/CardCountComponent.css'
import key from "./key.js"

export default class WeatherCardComponent extends React.Component {
    constructor(props) {
        super(props);


        this.state = {
            Park: "",
            WeatherData: "",
            Forecast: "",
            icon: "",
            high: "",
            low: "",
            humidity: "",
            currentTemp: "",
            RealFeel: "",
            Wind: "",
            Play: "",
        }
    }

    async componentDidMount() {
        
        
        await fetch("http://api.openweathermap.org/data/2.5/weather?q=Montreal&appid=" + key)
        .then(response => response.json())
        .then(data => this.setState({WeatherData: data}))

        console.log(this.state.WeatherData)

        await this.setState({
        Forecast: this.state.WeatherData.weather[0].description,
        icon: "http://openweathermap.org/img/wn/" + this.state.WeatherData.weather[0].icon + "@2x.png",
        currentTemp: Math.round(this.state.WeatherData.main.temp - 273),
        RealFeel: Math.round(this.state.WeatherData.main.feels_like - 273),
        high: Math.round(this.state.WeatherData.main.temp_max - 273),
        low: Math.round(this.state.WeatherData.main.temp_min - 273),
        humidity: this.state.WeatherData.main.humidity,
        Play: this.state.WeatherData.weather[0].main,
        Wind: this.state.WeatherData.wind.speed
    })
    }
    
    

    render() {
        return (


            <Card className="Card-Standard" style={{ width: '100%', height:'100%'}}>
                <Card.Header><h2>Today's Forecast</h2></Card.Header>
                <Card.Body>
                    

                    <Row>
                        <Col>
                        <h2>{this.state.Forecast} </h2>
                        <Card.Subtitle className="mb-2 text-muted"><i>Real Feel: {this.state.RealFeel}°C</i></Card.Subtitle>
                        <i></i>
                        <h1>{this.state.currentTemp}°C</h1>
                        </Col>
                        <Col>
                        <img width="55%" src={this.state.icon}/>
                        </Col>
                    </Row>
                    <Row>
                        
                        <Col>
                            Max: <strong>{this.state.high}°C</strong>
                        </Col>
                        <Col>
                           Min: <strong>{this.state.low}°C</strong> 
                        </Col>
                        <Col lg="auto">
                            Humidex: <strong>{this.state.humidity}%</strong>
                        </Col>
                        <Col lg="auto">
                            Wind: <strong>{Math.round(this.state.Wind * 1.60934)}km/h</strong>
                        </Col>
                    </Row>
                   
                    <hr/>
                    <Row>
                        <Col>
                        {
                            this.state.Play === "Rain" || this.state.Play === "Thunderstorm"
                            ?
                            <h4>Conditions may not be suitable due to rain</h4>
                            :
                            <h4>Conditions are good to play</h4>
                        }
                        </Col>
                    </Row>
                   
                </Card.Body>
                
            </Card>

            

        );
    };

}