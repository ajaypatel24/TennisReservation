import React from 'react'
import { Card } from 'react-bootstrap'
import '../Count/CardCountComponent.css'
import ReservationComponent from '../../ReservationComponent/ReservationComponent'

export default class HomePage extends React.Component {
    constructor(props) {
        super(props);

    }

    render() {
        return (


     
            
            <Card className="Card-Standard" >
                
                <Card.Body>
                    <Card.Title>Card Title</Card.Title>
                    <ReservationComponent></ReservationComponent>
                </Card.Body>
            </Card>
    

        );
    };

}