import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

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
    if (isLoading)
      return(<div>Loading...</div>);

    return (
      <div>
        <h2> Result </h2>

      
            <div>
                {this.state.Categories}
            </div>
         
        


      </div>
    )
  }

}

export default App;
