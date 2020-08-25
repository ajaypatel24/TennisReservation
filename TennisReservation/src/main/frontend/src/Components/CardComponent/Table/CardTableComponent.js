import React from 'react'
import { Card } from 'react-bootstrap'
import MaterialTable from 'material-table'
import moment from 'moment'

export default class HomePage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            TableData: [],
            ParkData: [],
            url: "/confirmation/",
            pdf: ""
        };
    }




    componentDidMount() {
        fetch("/ReservationDate/" + moment().format("YYYY-MM-DD"))
            .then(response => response.json())
            .then(data => this.setState({ TableData: data }))

        fetch("ParkInformation/marl")
            .then(response => response.json())
            .then(data => this.setState({ ParkData: data }))
    }

    handleRowClick = (event, rowData) => {
        var link = this.state.url + rowData.confirmationPDF;
        window.open(link)
    }

    render() {
        return (


            <Card style={{ width: '100%', height: '100%' }}>
                <Card.Body>
                    <Card.Title>Todays Reservations</Card.Title>
                    <MaterialTable
                        columns={[
                            { title: 'Reservation File', field: 'confirmationPDF' },
                            { title: 'Reservation Date', field: 'date' },
                            { title: 'Reservation Time', field: 'time' },
                            { title: 'Court Number', field: 'court' },
                            { title: 'Park', field: 'park' }
                        ]}
                        title={moment().format("LL")}
                        data={this.state.TableData}
                        onRowClick={this.handleRowClick}
                    />
                </Card.Body>
            </Card>

        );
    };

}