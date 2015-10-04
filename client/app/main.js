import React from "react";

export default React.createClass({
  getInitialState: function() {
    return {
      value: '',
      json: {}
    };
  },

  handleChange: function(evt) {
    this.setState({
      value: evt.target.value
    });
  },

  handleClick: function() {
    $.getJSON(this.props.source, {value: this.state.value}, function(json){
      this.setState({
        json: JSON.stringify(json, null, 2)
      });
    }.bind(this));
  },

  render: function() {
    return (
      <div>
        <p>{this.state.json}</p>
        <textarea name="description" onChange={this.handleChange} />
        <input type="button" value="Parse" onClick={this.handleClick} />
      </div>
    );
  }
});
