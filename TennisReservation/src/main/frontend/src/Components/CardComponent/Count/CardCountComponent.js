import React from 'react'
import { Card } from 'react-bootstrap'
import './CardCountComponent.css'
import moment from 'moment'
import { Chart } from 'react-google-charts'
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
            .then(data => this.setState({ TableData: data }))
    }

    handleRowClick = (event, rowData) => {
        var link = this.state.url + rowData.confirmationPDF;
        window.open(link)
    }
    render() {
        return (


            <Card className="Card-Standard" style={{ width: '100%', height: '100%' }}>
                <Card.Body>
                    <Card.Title>Number of Reservations</Card.Title>

                    <Card.Subtitle className="mb-2 text-muted">{moment().format("LLLL")}</Card.Subtitle>
                    <Card.Text style={{ fontSize: '7rem', fontWeight: 600 }}>
                        {this.state.TableData.count}
                        <Chart
                            width={'500px'}
                            height={'400px'}
                            chartType="PieChart"
                            loader={<div>Loading Chart</div>}
                            data={[
                                ['Court', 'Number of Reservations'],
                                ['Court 1', 2],
                                ['Court 2', 1],
                                ['Court 3', 0],
                                ['Court 4', 1], // Below limit.
                                ['Court 5', 0], // Below limit.
                            ]}
                            options={{
                                title: 'Court Distribution',
                                sliceVisibilityThreshold: 0.2, // 20%
                            }}
                            rootProps={{ 'data-testid': '7' }}
                        />
                    </Card.Text>

                    <Card.Link href="#">Card Link</Card.Link>
                    <Card.Link href="#">Another Link</Card.Link>
                </Card.Body>
            </Card>

        );
    };

}