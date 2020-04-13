# agricolab
AgriColab, Ingeniería de Software II. Universidad Nacional de Colombia.


## Documentation

Entity classes:
- User
- Request
- Offer


### API endpoints:
Each entity has it's own API to send or retrieve instances.

----
#### User
##### Create User:
```
POST /api/v1/user
```
```JSON
// Sample User object
{
    "email":"test@unal.edu.co",
    "name":"Santiago",
    "password":"12345",
    "age":18
}
```
##### Read User:
```
GET /api/v1/user/{email}
```
```JSON

```

##### Read all Users:
```
GET /api/v1/user
```
```JSON
// Sample response:
: [
    {
        "email": "TestIntegralCommit@Test.com",
        "name": "Integral",
        "password": "password",
        "age": 29,
        "seller": false
    },
    {
        "email": "asdasd",
        "name": "asdasd",
        "password": "asdasdasd",
        "age": 3,
        "seller": false
    },
    {
        "email": "qwerty",
        "name": "qwerty",
        "password": "qwerty",
        "age": 16,
        "seller": false
    },
    {
        "email": "test@unal.edu.co",
        "name": "Santiago",
        "password": "12345",
        "age": 18,
        "seller": false
    }
]
```



----
#### Request
##### Create Request:
```
POST /api/v1/request
```
```JSON
// Sample Request object:
{
    "productName":"Arroz",
    "userEmail":"sanhernandezmon@unal.edu.co",
    "presentation":4,
    "pricePresentation":1342,
    "minQuantity":2,
    "description":"Arroz común y corriente."
}
```

##### Read all Requests:
```
GET /api/v1/request
```
```JSON
// Sample response
```
##### Read all Request from User:
```
GET /api/v1/request/{email}
```
```JSON
// Sample response
```

----
#### Offer
##### Create Offer:
```
POST /api/v1/offer
```
```JSON
// Sample Offer object:
{
    "productName" : "Papa",
    "userEmail" : "sanhmon@unal.edu.co",
    "unit" : 4,
    "numeberOfUnits": 10,
    "totalPrice":13.668,
    "description": "El mejor arroz de la sabana de bogotá."
}
```
##### Read all Offers:
```
GET /api/v1/offer
```
```JSON
// Sample response
```
##### Read all Offers from User:
```
GET /api/v1/offer/{email}
```
```JSON
// Sample response
```

----
#### dev notes: