import React from 'react'
import { Card } from 'react-bootstrap'
import '../Count/CardCountComponent.css'


export default class HomePage extends React.Component {
    constructor(props) {
        super(props);

    }

    render() {
        return (


            <Card className="Card-Standard" style={{ width: '100%', height:'100%'}}>
                <Card.Body>
                    <Card.Title>Card Title</Card.Title>
                    
                    <Card.Subtitle className="mb-2 text-muted">Card Subtitle</Card.Subtitle>
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