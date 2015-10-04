import React from "react";
import Main from "./main";

React.render(
  <Main source="http://localhost:8081/api/parse_costs.json" />,
  document.body
);
