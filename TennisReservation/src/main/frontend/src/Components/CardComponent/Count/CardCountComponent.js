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
            CourtData: [],
            url: "/confirmation/",
            pdf: ""
        };
    }




    async componentDidMount() {
        await fetch("/ReservationDateCount/" + moment().format("YYYY-MM-DD"))
            .then(response => response.json())
            .then(data => this.setState({ TableData: data }))

        await fetch("/ReservationCourtDistribution/" + moment().format("YYYY-MM-DD"))
            .then(response => response.json())
            .then(data => this.setState({CourtData: data}))

        const charData = [['Court', 'Count']]
        for (let i = 0; i < this.state.CourtData.length; i += 1) {
            charData.push([this.state.CourtData[i].court, this.state.CourtData[i].count])
        }
        this.setState({CourtData: charData})
        
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
                            width={'100%'}
                            height={'50%'}
                            chartType="ColumnChart"
                            loader={<div>Loading Chart</div>}
                            data={this.state.CourtData}
                       
                            
                        />
                    </Card.Text>

                    <Card.Link href="#">Card Link</Card.Link>
                    <Card.Link href="#">Another Link</Card.Link>
                </Card.Body>
            </Card>

        );
    };

}