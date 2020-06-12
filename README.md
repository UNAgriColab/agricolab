# agricolab
AgriColab\
Ingeniería de Software II\
Universidad Nacional de Colombia.

### API documentation:
Each entity has it's own endpoint to send or retrieve instances.

- [User](#user)
  - [Create User](#create-user)
  - [Read User](#read-user)
  - [Update User](#update-user)
  - [Delete User](#delete-user)
  - [Read all Users](#read-all-users)
- [Order](#order)
  - [Create Order](#create-order)
  - [Read order](#read-order)
  - [Read all orders](#read-all-orders)
  - [Read all orders from an offerReference](#read-all-orders-from-an-offerreference)

- [Offer](#offer)
  - [Create Offer](#create-offer)
  - [Read all Offers](#read-all-offers)
  - [Read all Offers from User](#read-all-offers-from-user)
- [dev notes](#dev-notes)
- [DEPRECATED](#deprecated)
  - [Update order as Buyer](#update-order-for-buyer)
  - [Update order as Seller](#update-order-for-seller)

Entity classes:
- User
- Request
- Offer


### API endpoints:
Each entity has it's own API to send or retrieve instances.

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
```
GET /api/v1/user/samoralespu@unal.edu.co
```
```JSON
{
    "email":"samoralespu@unal.edu.co",
    "name":"Santiago",
    "password":"12345",
    "age":18,
    "seller":false
}
```
##### Update User:
```JSON
{

}
```
##### Delete User:
```JSON
{

}
```
##### Read all Users:
```
GET /api/v1/user
```
```JSON
// Sample response:
[
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
#### Order
##### Create Order:
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
##### Read Order:
```
GET /api/v1/order/3
```
```JSON
//Sample Response
{
    "offerReference": "s2naUii68eBbMjxikNfZ",
    "userEmail": "test2@unal.edu.co",
    "unit": 4,
    "numberOfUnits": 18,
    "totalPrice": 345.7,
    "description": "segunda prueba de crear un order",
    "id": "3",
    "state": 0
}
```
##### Read all Orders:
```
GET /api/v1/order
```
```JSON
[
    {
        "offerReference": "s2naUii68eBbMjxikNfZ",
        "userEmail": "test2@unal.edu.co",
        "unit": 4,
        "numberOfUnits": 18,
        "totalPrice": 345.7,
        "description": "segunda prueba de crear un order",
        "id": "1",
        "state": 0
    },
    {
        "offerReference": "12",
        "userEmail": "test3@unal.edu.co",
        "unit": 4,
        "numberOfUnits": 18,
        "totalPrice": 246.024,
        "description": "state",
        "id": "12",
        "state": 0
    },
    {
        "offerReference": "7",
        "userEmail": "qwerty",
        "unit": 3,
        "numberOfUnits": 20,
        "totalPrice": 273.36,
        "description": "holii",
        "id": "14",
        "state": 15
    },
    {
        "offerReference": "3",
        "userEmail": "test2@unal.edu.co",
        "unit": 4,
        "numberOfUnits": 18,
        "totalPrice": 345.7,
        "description": "pidiendo oferta 3",
        "id": "5",
        "state": 0
    },
    {
        "offerReference": "4",
        "userEmail": "test3@unal.edu.co",
        "unit": 4,
        "numberOfUnits": 18,
        "totalPrice": 345.7,
        "description": "state",
        "id": "9",
        "state": 0
    }
]
```
##### Read all Orders from Buyer:
```
GET /api/v1/order/user/{email}
```
```$xslt
GET /api/v1/request/user/ayuda@unal.edu.co
```
```JSON
[
    {
        "offerReference": "s2naUii68eBbMjxikNfZ",
        "userEmail": "test2@unal.edu.co",
        "unit": 4,
        "numberOfUnits": 18,
        "totalPrice": 345.7,
        "description": "segunda prueba de crear un order",
        "id": "1",
        "state": 0
    },
    {
        "offerReference": "s2naUii68eBbMjxikNfZ",
        "userEmail": "test2@unal.edu.co",
        "unit": 4,
        "numberOfUnits": 18,
        "totalPrice": 345.7,
        "description": "segunda prueba de crear un order",
        "id": "3",
        "state": 0
    },
    {
        "offerReference": "3",
        "userEmail": "test2@unal.edu.co",
        "unit": 4,
        "numberOfUnits": 18,
        "totalPrice": 345.7,
        "description": "pidiendo oferta 3",
        "id": "5",
        "state": 0
    }
]
```
##### Read all Orders from Seller:
```
GET /api/v1/order/seller/{email}
```
```$xslt
GET /api/v1/request/user/ayuda@unal.edu.co
```
```JSON
[
    {
        "offerReference": "12",
        "userEmail": "test3@unal.edu.co",
        "unit": 10,
        "numberOfUnits": 18,
        "totalPrice": 246.024,
        "description": "state",
        "id": "11",
        "state": 6
    },
    {
        "offerReference": "3",
        "userEmail": "test3@unal.edu.co",
        "unit": 4,
        "numberOfUnits": 18,
        "totalPrice": 345.7,
        "description": "pidiendo oferta 3",
        "id": "6",
        "state": 0
    },
    {
        "offerReference": "7",
        "userEmail": "qwerty",
        "unit": 3,
        "numberOfUnits": 14,
        "totalPrice": 191.35199999999998,
        "description": "qweyutrusfn qiuweguiybf",
        "id": "13",
        "state": 0
    }
]
```
#####Read all orders from an OfferReference
```
GET /api/v1/order/offer/{offerId}
```
```JSON
{

}
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
    "userEmail" : "samoralespu@unal.edu.co",
    "productName" : "recien subido",
    "presentation": 10,
    "pricePresentation":13.668,
    "minQuantity": 2,
    "description":"Aun no ha cambiado"
}
```
##### Read all Offers:
```
GET /api/v1/offer
```
```JSON
// Sample Response
[
    {
        "productName": "Papa",
        "userEmail": "sanhmon@unal.edu.co",
        "presentation": 0.0,
        "pricePresentation": 0.0,
        "minQuantity": 0,
        "description": "El mejor arroz de la sabana de bogotá.",
        "id": "1"
    },
    {
        "productName": "recien subido",
        "userEmail": "samoralespu@unal.edu.co",
        "presentation": 10.0,
        "pricePresentation": 13.668,
        "minQuantity": 2,
        "description": "Aun no ha cambiado",
        "id": "10"
    },
    {
        "productName": "recien subido",
        "userEmail": "samoralespu@unal.edu.co",
        "presentation": 10.0,
        "pricePresentation": 13.668,
        "minQuantity": 2,
        "description": "Aun no ha cambiado",
        "id": "11"
    },
    {
        "productName": "recien subido",
        "userEmail": "samoralespu@unal.edu.co",
        "presentation": 4.0,
        "pricePresentation": 13.668,
        "minQuantity": 2,
        "description": "Aun no ha cambiado",
        "id": "12"
    },
    {
        "productName": "ACELGA",
        "userEmail": "luis@unal.edu.co",
        "presentation": 1.0,
        "pricePresentation": 3500.0,
        "minQuantity": 14,
        "description": "descripcion product",
        "id": "13"
    },
    {
        "productName": "AGUACATE HASS",
        "userEmail": "qweuqwrtry",
        "presentation": 4.0,
        "pricePresentation": 3800.0,
        "minQuantity": 2,
        "description": "hola soy un aguacate",
        "id": "14"
    },
    {
        "productName": "AGUACATE HASS",
        "userEmail": "qweuqwrtry",
        "presentation": 4.0,
        "pricePresentation": 3800.0,
        "minQuantity": 2,
        "description": "hola soy un aguacate",
        "id": "15"
    },
    {
        "productName": "AGUACATE HASS",
        "userEmail": "qweuqwrtry",
        "presentation": 4.0,
        "pricePresentation": 3800.0,
        "minQuantity": 2,
        "description": "hola soy un aguacate",
        "id": "16"
    }
]
```
##### Read all Offers from User:
```
GET /api/v1/offer/user/{email}
```
```JSON
/api/v1/offer/user/ayuda@unal.edu.co
```
```JSON
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
```JSON
```
----
#### dev notes:
```
Authentication Method: JWT Token
Path: /auth
POST Method
```
```JSON
{
	"email": "test@test.edu.co",
	"password": "12345"
}
```

----
#### DEPRECATED:
#####Update Order for Buyer:
```
PUT /api/v1/order/buyer
```
```JSON
// Sample Update request:
{
    "canceled": false,
    "orderId": "9"
}
```
#####Update Order for Seller:
```
PUT /api/v1/order/buyer
true = Update order
false = Cancel order
```
```JSON
// Sample Update request:
{
    "canceled": false,
    "orderId": "9"
}
```