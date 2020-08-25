import React from 'react'
import { Card } from 'react-bootstrap'
import '../Count/CardCountComponent.css'


export default class HomePage extends React.Component {
    constructor(props) {
        super(props);


        this.state = {
            Park: ""
        }
    }

    componentDidMount() {
        this.setState({Park: sessionStorage.getItem("Park")})
        
        fetch("http://api.openweathermap.org/data/2.5/weather?q=Montreal&appid=" + "0beae4d33f55327b96264bb3b1d94208")
        .then(response => response.json())
        .then(data => console.log(data))


    }
    
    

    render() {
        return (


            <Card className="Card-Standard" style={{ width: '100%', height:'100%'}}>
                <Card.Body>
                    
                    <Card.Title>{this.state.Park}</Card.Title>
                    
                    <Card.Subtitle className="mb-2 text-muted">Graph of distribution</Card.Subtitle>
                    <Card.Text>
                        Card Text
                    </Card.Text>
                    <Card.Link href="#">Card Link</Card.Link>
                    <Card.Link href="#">Another Link</Card.Link>
                </Card.Body>
                
            </Card>

            

        );
    };

}