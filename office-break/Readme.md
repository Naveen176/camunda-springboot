This Project Consists of Office Break Example.
Starting the Office Process:
  POST : http://localhost:8080/startOffice
Request body :{
                  "name":"Kumar"
              }
Demo Url for Event based 
POST : http://localhost:8080/engine-rest/message
Request body :{
                  "messageName":"OpponentAbsent"
              }
Demo URL for Conditional Start
POST: http://localhost:8080/engine-rest/condition
Request body :  {
              "variables":{
                  "chessAvailable" : {"value":"no","type":"String"}
              }
          }
