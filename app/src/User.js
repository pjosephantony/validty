import React, { Component } from 'react';

class User extends Component{
    render(){
        return(
            <li>{this.props.id},
                {this.props.firstName},
                {this.props.lastName},
                {this.props.company},
                {this.props.email},
                {this.props.address1},
                {this.props.address2},
                {this.props.zip},
                {this.props.city},
                {this.props.stateLong},
                {this.props.state},
                {this.props.phone},
            </li>
        );
    }
}

export default User;
