import React from 'react'
import { Row, Col } from 'react-bootstrap'
import CardComponent from './CardComponent'
import CardTableComponent from './CardTableComponent'
import CardComponentBase from './CardComponentBase'
import './CardComponent.css'


export default class HomePage extends React.Component {
    constructor(props) {
        super(props);
    }



    render() {
        return (
            <div>
                <Col>

                        <h1 className = "title">Welcome Back</h1>
                    <Row className="Row-Grid">

                        <Col lg="4">
                            <CardComponent></CardComponent>
                        </Col>
                        <Col lg="8">

                            <CardTableComponent></CardTableComponent>
                        </Col>

                    </Row>
                
                    <Row className="Row-Grid">
                        <Col lg="4">
                            <CardComponentBase></CardComponentBase>
                        </Col>
                        <Col lg="4">
                        <CardComponentBase></CardComponentBase>
                        </Col>
                        <Col lg="4">
                        <CardComponentBase></CardComponentBase>
                        </Col>
                    </Row>
              
                    <Row className="Row-Grid">
                        <Col lg="12"><CardComponentBase></CardComponentBase></Col>
                    </Row>

                </Col>
            </div>
        );
    };

}