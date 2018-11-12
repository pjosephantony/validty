import React, { Component } from 'react';
import './App.css';
import User from './User';



class App extends Component {

    constructor(props){
        super(props);

        this.state = {
            unique: [],
            duplicates: []
        };
    }

    componentWillMount(){
        fetch('/customer').then((response) => {
            if(response.status >= 200 && response.status < 300){
            return response;
        } else{
            const error = new Error(`HTTP Error ${response.statusText}`);
            error.status = response.statusText;
            error.response = response;
            console.log(error);
            throw error;
        }
    }).then((response) => {
            response.json().then((data) => {
            console.log(data);
        this.setState({unique: data["unique"], duplicates: data["duplicates"]});
    });
    });
    }


    render() {
        const duplicateUsers = this.state.duplicates.map((user) => {

            return <User
        id={user.id}
        firstName={user.firstName}
        lastName={user.lastName}
        company={user.company}
        email={user.email}
        address1={user.address1}
        address2={user.address2}
        zip={user.zip}
        city={user.city}
        stateLong={user.stateLong}
        state={user.state}
        phone={user.phone}
        />;
    });
        const uniqueUsers = this.state.unique.map((user) => {
            return <User
        id={user.id}
        firstName={user.firstName}
        lastName={user.lastName}
        company={user.company}
        email={user.email}
        address1={user.address1}
        address2={user.address2}
        zip={user.zip}
        city={user.city}
        stateLong={user.stateLong}
        state={user.state}
        phone={user.phone}
        />;
    });

        return (

            <div className='App-container'>

            <h2>Potential Duplicates</h2>
        <div>
        {duplicateUsers}
        </div>

        <h2>Unique</h2>
        <div>
        {uniqueUsers}
        </div>

        </div>
    );
    }
}

export default App;
