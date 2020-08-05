import React, { Component } from 'react';
import {HashRouter, Route, Switch, Redirect} from 'react-router-dom'
import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import ReservationComponent from './Components/ReservationComponent';

class App extends Component {
  state = {
    isLoading: true,
    Categories : "Dysyi"
  }


  async componentDidMount() {
    const response = await fetch('/Run');
    const body = await response.json();
    console.log(body);
    for (var x in body ) {
    this.setState({Categories: body[x], isLoading: false});
    }
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

      
            <div>
                <HashRouter>
                    <Switch>
                      <Route exact path="/" component={ReservationComponent} />
                    </Switch>


                </HashRouter>
            </div>
         
        


      </div>
    )
  }

}

export default App;
