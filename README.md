# agricolab
AgriColab, Ingeniería de Software II. Universidad Nacional de Colombia.

user post method:
localhost:8080/api/v1/request
Body:
{
	"email":"sanhernandezmon@unal.edu.co",
	"name":"Santiago",
	"password":"12345",
	"seller":true ,
	"age":18	
}

offer post method:
localhost:8080/api/v1/offer
Body:
{
	"productName":"Arroz",
	"userEmail":"sanhernandezmon@unal.edu.co",
	"presentation":4,
	"pricePresentation":1342,
	"minQuantity":2,
	"description":"esto es arroz"
}

request post method:
localhost:8080/api/v1/request
Body:
{
	"productName" : "Papa",
	"userEmail" : "sanhmon@unal.edu.co",
	"unit" : 4,
	"numeberOfUnits": 10,
	"totalPrice":13.668,
	"descrpition": "el mejor arroz de la sabana de bogotá"
}