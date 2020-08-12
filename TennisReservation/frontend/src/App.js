import React, { Component } from 'react';
import {HashRouter, Route, Switch, Redirect} from 'react-router-dom'
import {Nav, Navbar} from 'react-bootstrap'
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
      <div id="Container">
            <Navbar bg="dark" variant="dark">
              <Navbar.Brand>
                <img
                alt=""
                src="/tennisball.png"
                width="30"
                length="30"
                className="d-inline-block align-top"
                />{' '}
                Tennis Reservation Automation
                </Navbar.Brand>
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
                </Navbar>

            <div>
                <HashRouter>
                    <Switch>
                      <Route exact path="/Reserve" component={ReservationComponent} />
                      <Route exact path="/Booking" component={Bookings} />
                    </Switch>
                    

                </HashRouter>
            </div>
         
        
              <footer>
                app
              </footer>

      </div>
    )
  }

}

export default App;
