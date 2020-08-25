import React from 'react'
import { Card , Button } from 'react-bootstrap'
import '../Count/CardCountComponent.css'


export default class ParkInformationCard extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            Val: "",
            test: "",
            Data: "",
            Value: "",
            Park: "Select Park for more information",
            Address: "",
            Phone: "",
            url: ""
        }

    }

    async componentWillReceiveProps(nextProps) {
        if (nextProps.name !== this.props.name) {
            this.setState({Val: nextProps.name})
        }

        if (nextProps.name !== "Select Park") {
        await fetch("ParkInformation/" + nextProps.name)
            .then(response => response.json())
            .then(data => this.setState({Data: data}))

        await this.setState({
            Park: this.state.Data[0].park,
            Address: this.state.Data[0].address,
            Phone: this.state.Data[0].phoneNumber
        })
            if (this.state.Park === "Marcel-Laurin") {
                this.setState({url: "https://montreal.ca/en/places/parc-marcel-laurin"})
            }
            else if (this.state.Park === "Marlborough") {
                this.setState({url: "https://montreal.ca/en/places/parc-marlborough"})
            }
            else if (this.state.Park === "Noel-Sud") {
                this.setState({url: "https://montreal.ca/en/places/parc-noel-sud"})
            }
        }




        else {
            this.setState({
                Park: "Select Park for more information",
                Address: "",
                Phone: ""
            })
        }


                            
    }
    


    render() {
        return (

            <Card className="Card-Standard" style={{ width: '100%', height:'100%'}}>
                <Card.Body>

                    <Card.Title>{this.state.Park}</Card.Title>
                        
                    
                    <Card.Subtitle className="mb-2 text-muted">{this.state.Address}</Card.Subtitle>
                    <Card.Subtitle className="mb-2 text-muted">{this.state.Phone}</Card.Subtitle>
                    <Card.Text>
                        
                    </Card.Text>

                    {
                        this.state.Park === "Select Park for more information"
                        ?
                        null
                        :

                    <Card.Link href={this.state.url} target="_blank">Park Website</Card.Link>
            
                    }
                </Card.Body>
            </Card>

        );
    };

}