# Office Break Example Project

## Overview
This project demonstrates an example scenario related to office breaks. It includes functionalities such as starting the office process, simulating events, and triggering conditional starts.

## Getting Started
To get started with the project, follow the instructions below.

### Starting the Office Process
To start the office process, make a POST request to the following endpoint:
- POST: http://localhost:8080/startOffice
**Request Body**:
- 
  {
    "name": "Kumar"
}
### To simulate an event-based scenario, use the following endpoint:
- POST: http://localhost:8080/engine-rest/message

**Request Body**:
- {
    "messageName": "OpponentAbsent"
}
### To trigger a conditional start, use the following endpoint:
- POST: http://localhost:8080/engine-rest/condition

**Request Body**:
- {
    "variables": {
        "chessAvailable": {"value": "no", "type": "String"}
    }
}
