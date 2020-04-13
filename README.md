# agricolab
AgriColab\
Ingeniería de Software II\
Universidad Nacional de Colombia.

### API documentation:
Each entity has it's own endpoint to send or retrieve instances.

- [User](#user)
  - [Create User](#create-user)
  - [Read User](#read-user)
  - [Read all Users](#read-all-users)
- [Request](#request)
  - [Create Request](#create-request)
  - [Read all Requests](#read-all-requests)
  - [Read all Requests from User](#read-all-requests-from-user)
- [Offer](#offer)
  - [Create Offer](#create-offer)
  - [Read all Offers](#read-all-offers)
  - [Read all Offers from User](#read-all-offers-from-user)
- [dev notes](#dev-notes)

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
/api/v1/user/samoralespu@unal.edu.co
{
    "email":"samoralespu@unal.edu.co",
    "name":"Santiago",
    "password":"12345",
    "age":18,
    "seller":false
}
```

##### Read all Users:
```
GET /api/v1/user
```
```JSON
[
    {
        "email": "TestIntegralCommit@Test.com",
        "name": "Integral",
        "password": "password",
        "age": 29,
        "seller": false
    }, ... ,
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
[
    {
        "productName": "Papa",
        "userEmail": "sanhmon@unal.edu.co",
        "unit": 4,
        "numberOfUnits": 0,
        "totalPrice": 13.668,
        "description": "el mejor arroz de la sabana de bogotá"
    }, ... ,
    {
        "productName": "Pitaya",
        "userEmail": "1@unal.edu.co",
        "unit": 4,
        "numberOfUnits": 0,
        "totalPrice": 13.668,
        "description": "el mejor arroz de la sabana de bogotá"
    }
]
```
##### Read all Requests from User:
```
GET /api/v1/request/{email}
```
```JSON
/api/v1/request/ayuda@unal.edu.co
 [
    {
        "productName": "Yuca",
        "userEmail": "ayuda@unal.edu.co",
        "unit": 4,
        "numberOfUnits": 0,
        "totalPrice": 13.668,
        "description": "el mejor arroz de la sabana de bogotá"
    },
    {
        "productName": "Platano",
        "userEmail": "ayuda@unal.edu.co",
        "unit": 4,
        "numberOfUnits": 0,
        "totalPrice": 13.668,
        "description": "el mejor arroz de la sabana de bogotá"
    },
    {
        "productName": "Papa",
        "userEmail": "ayuda@unal.edu.co",
        "unit": 4,
        "numberOfUnits": 0,
        "totalPrice": 13.668,
        "description": "el mejor arroz de la sabana de bogotá"
    }
]
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
[
    {
        "productName": "Yuca",
        "userEmail": "ayuda@unal.edu.co",
        "presentation": 4,
        "pricePresentation": 1342.0,
        "minQuantity": 2,
        "description": "esto es arroz"
    }, ... ,
    {
        "productName": "Papa",
        "userEmail": "saduquebe@unal.edu.co",
        "presentation": 5,
        "pricePresentation": 500.0,
        "minQuantity": 5,
        "description": "Se vende papa por bulto WAOOOO"
    }
]
```
##### Read all Offers from User:
```
GET /api/v1/offer/{email}
```
```JSON
/api/v1/offer/ayuda@unal.edu.co
[
    {
        "productName": "Yuca",
        "userEmail": "ayuda@unal.edu.co",
        "presentation": 0.0,
        "pricePresentation": 0.0,
        "minQuantity": 0,
        "description": "el mejor arroz de la sabana de bogotá"
    },
    {
        "productName": "Platano",
        "userEmail": "ayuda@unal.edu.co",
        "presentation": 0.0,
        "pricePresentation": 0.0,
        "minQuantity": 0,
        "description": "el mejor arroz de la sabana de bogotá"
    },
    {
        "productName": "Papa",
        "userEmail": "ayuda@unal.edu.co",
        "presentation": 0.0,
        "pricePresentation": 0.0,
        "minQuantity": 0,
        "description": "el mejor arroz de la sabana de bogotá"
    }
]
```

----
#### dev notes: