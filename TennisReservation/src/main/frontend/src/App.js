import React, { Component } from 'react';
import {HashRouter, Route, Switch} from 'react-router-dom'
import {Nav, Navbar} from 'react-bootstrap'
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import ReservationComponent from './Components/ReservationComponent/ReservationComponent';
import Bookings from './Components/BookingComponent/Bookings'
import HomePage from './Components/HomePageComponent/HomePage'


class App extends Component {
  state = {
    isLoading: true
  }


  render() {
    const {Categories, isLoading} = this.state;
    /*
    if (isLoading)
      return(<div>Loading...</div>);
*/
    return (
      <div id="Container">
            <Navbar className="NavBar" bg="light" variant="light">
              <Navbar.Brand style={{fontSize: '2rem'}}>
                <img
                alt=""
                src="/tennisball.png"
                width="45"
                length="45"
                className="d-inline-block align-top"
                />{' '}
                Tennis Reservation Automation
                </Navbar.Brand>
                <Nav defaultActiveKey="/" as="ul">
                    <Nav.Item as="li">
                        <Nav.Link href="#/">Home</Nav.Link>
                    </Nav.Item>
                    <Nav.Item as="li">
                        <Nav.Link href="#/Reserve">Reserve</Nav.Link>
                    </Nav.Item>
                    <Nav.Item as="li">
                        <Nav.Link href="#/Booking">Bookings</Nav.Link>
                    </Nav.Item>
                </Nav>
                </Navbar>

            <div>
                <HashRouter>
                    <Switch>
                      <Route exact path="/Reserve" component={ReservationComponent} />
                      <Route exact path="/Booking" component={Bookings} />
                      <Route exact path="/" component={HomePage} />
                    </Switch>
                    

                </HashRouter>
            </div>
      

      </div>
    )
  }

}

export default App;
