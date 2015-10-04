import React from "react";

export default React.createClass({
  getInitialState: function() {
    return {
      data: ''
    };
  },
  componentDidMount: function() {
    $.getJSON(this.props.source, function(json){
      this.setState({
        data: JSON.stringify(json, null, 2)
      });
    }.bind(this));
  },
  render: function() {
    return (
      <div>
        {this.state.data}
      </div>
    );
  }
});
