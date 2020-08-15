import React from 'react'
import { Card } from 'react-bootstrap'
import './CardCountComponent.css'
import moment from 'moment'

export default class HomePage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            TableData: [],
            url: "/confirmation/",
            pdf: ""
        };
    }

    


    componentDidMount() {
        fetch("/ReservationDateCount/" + moment().format("YYYY-MM-DD"))
            .then(response => response.json())
            .then(data => this.setState({TableData: data}))
    }

    handleRowClick = (event, rowData) => {
        var link = this.state.url + rowData.confirmationPDF;
        window.open(link)
      }
    render() {
        return (


            <Card className="Card-Standard" style={{ width: '100%', height:'100%'}}>
                <Card.Body>
                    <Card.Title>Number of Reservations</Card.Title>
                    
                    <Card.Subtitle className="mb-2 text-muted">{moment().format("LLLL")}</Card.Subtitle>
                    <Card.Text style={{ fontSize:'7rem', fontWeight: 600}}>
                        {this.state.TableData.count}
                    </Card.Text>
                    <Card.Link href="#">Card Link</Card.Link>
                    <Card.Link href="#">Another Link</Card.Link>
                </Card.Body>
            </Card>

        );
    };

}