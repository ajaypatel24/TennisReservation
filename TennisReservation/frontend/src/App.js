import React, { Component } from 'react';
import {HashRouter, Route, Switch, Redirect} from 'react-router-dom'
import {Nav} from 'react-bootstrap'
import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import ReservationComponent from './Components/ReservationComponent';
import Bookings from './Components/Bookings'


class App extends Component {
  state = {
    isLoading: true,
    Categories : "Dysyi"
  }


  render() {
    const {Categories, isLoading} = this.state;
    /*
    if (isLoading)
      return(<div>Loading...</div>);
*/
    return (
      <div>
        <h2> Tennis Reservation Automation </h2>
        <Nav defaultActiveKey="/home" as="ul">
                    <Nav.Item as="li">
                        <Nav.Link href="/">Home</Nav.Link>
                    </Nav.Item>
                    <Nav.Item as="li">
                        <Nav.Link href="#/Reserve">Reserve</Nav.Link>
                    </Nav.Item>
                    <Nav.Item as="li">
                        <Nav.Link href="#/Booking">Bookings</Nav.Link>
                    </Nav.Item>
                </Nav>

            <div>
                <HashRouter>
                    <Switch>
                      <Route exact path="/Reserve" component={ReservationComponent} />
                      <Route exact path="/Booking" component={Bookings} />
                    </Switch>
                    

                </HashRouter>
            </div>
         
        


      </div>
    )
  }

}

export default App;
