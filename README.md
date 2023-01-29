
# Clipboard Health

Rest service serving basic get, post & delete endpoints for adding employee details, displaying employees details and generating statistics summary based on contract, department and subdepartment level.

### API Reference
All apis are authenticated

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. Your API key |
| `password` | `string` | **Required**. Your API key |

### Employee Details
1. #### Get all employees

```http
  GET /employees
```
e.g.
Response
[{
"employeeId": "04b40489-2464-4d67-b713-1475284ea687",
"name": "Anurag",
"salary": "90000",
"currency": "USD",
"department": "Banking",
"subDepartment": "Loan",
"onContract": false
},
{
"employeeId": "6ffaf546-fc40-40c6-b1c0-8e92dc847258",
"name": "Guljit",
"salary": "30",
"currency": "USD",
"department": "Administration",
"subDepartment": "Agriculture",
"onContract": false
}]
#### Success response:
    Code: 200 OK
    Content: Record added successfully

#### Error response:
    Code: 400 BAD REQUEST
    Content: Error retrieving records: [error message]

2. #### Add Employees

```http
  POST /employees
```
Takes list of employee details as input.

REQUEST : [
{
"name": "Raginasasi",
"salary": "30",
"currency": "USD",
"department": "Engineering",
"subDepartment": "App"
},
{
"name": "Ndsdsikhil",
"salary": "110000",
"currency": "USD",
"on_contract": "true",
"department": "Engineering",
"subDepartment": "BackendApp"
}
]

#### Success response:
    Code: 201 CREATED
    Content: Record added successfully

#### Error response:
    Code: 400 BAD REQUEST
    Content: Please pass mandatory value
    
    Code: 400 BAD REQUEST
    Content: Error adding record: [error message]

3. #### Delete Employee

```http
  DELETE /employee/{id}
```
Only admin can use this api.

e.g. /employee/07da7c21-99e6-4997-875b-eca357653a64
#### Success response:
    Code: 200 OK
    Content: Record deleted successfully



### Summary Statistics Details
1. #### Get summary for all employees

```http
  GET /summary
```
e.g.
Response
{
"mean": 90000.0,
"min": 90000.0,
"max": 90000.0
}

2. #### Get Summary for onContract Employees

```http
  GET /summary/onContract
```


3. #### Get Summary for onContract Employees

```http
  GET /summary/onContract
```

e.g. if Employees exists
```
{
    "Data :": {
        "mean": 110000.0,
        "min": 110000.0,
        "max": 110000.0
    }
} 
Otherwise, 
{
    "No OnContract Employee Found": {
        "mean": 0.0,
        "min": 0.0,
        "max": 0.0
    }
}
```
4. #### Get Summary Department

```http
  GET /summary/summary/department
```
e.g.
Response
{
"Engineering": {
"mean": 55015.0,
"min": 30.0,
"max": 110000.0
}
}

5. #### Get Summary Department and SubDepartment

```http
  GET /summary/summary/department/subdepartment
```
e.g.
Response
{
"Engineering": {
"App": {
"mean": 30.0,
"min": 30.0,
"max": 30.0
},
"BackendApp": {
"mean": 110000.0,
"min": 110000.0,
"max": 110000.0
}
}
}
### Features
1. Different authentication, employee, statistics flow for apis.
2. All basic test cases added.
3. Username - user & password - password works for ROLE - USER
4. Username - admin & password - password works for ROLE - ADMIN
5. Error handling & basic validation added.
6. In-memory data structure added for persisting data.
7. Used third party apache common library for calculations.
8. For running test, you can use any IDE and run the class as any other java class. 
9. To start the service 2 options are available :
   - Build the docker image first using command - docker build -t clipboardhealth-exercise-app:1 .
   - Run the command to bring the service up - docker-compose up
   - Alternatively, you can also run command to do both in a single step - docker-compose up --build

### Scope of Improvement
1. Global exception handler should be added.
2. Test coverage should be increased.
3. Adapter design pattern can be used to changing DTOs.
4. Different POJOs for request & response can be added.
5. Different environment can be added.
6. Basic logging can be added for tracing.
7. APIs error message can be further categorized intro retryable and non-retrybale.
8. Error message can be split into two parts, one for clients and one for developers.
9. Amount/salary data can be changed from double to big decimal for precision. 
10. Get employees api needs to be paginated. 
11. Passwords can be protected using salt and better hashing algo.

### Best practices
I believe in the thought process of "standing on the  shoulders of giants", so I follow guidelines from giants of tech industry.
Few of the examples that I follow are:

1. java best practices 
   https://google.github.io/styleguide/javaguide.html
2. code review
   https://google.github.io/eng-practices/review/
3. prioritizing technical decisions
   https://about.gitlab.com/handbook/engineering/development/principles/#prioritizing-technical-decisions
4. Branch naming and work classification
   https://about.gitlab.com/handbook/engineering/metrics/#work-type-classification

