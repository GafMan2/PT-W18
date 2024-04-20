Mapping instructions for testing application in an ARC of your choice:

1. POST - Create a new owner - (you need to enter an email address only)
http://localhost:8080/wine_cellar/owners

{
  "username": "ChauncyGardner@bloom.com",
  "password": ""
}

2. POST - Create a wine cellar
http://localhost:8080/wine_cellar/owners/??/cellars (?? = enter owner id here)

{
  "cellarName": "Boatwright Wine Cellar",
  "location": "Beverly Hills Wine Store",
  "capacity": "
}

3. POST - add a wine to a cellar
http://localhost:8080/wine_cellar/owners/??/wines (?? = the owner's id)

{
  "wineName": "Cakebread Cellars",
  "vintageYear": "2014",
  "varietal": "Cabernet Sauvignon",
  "color": "red",
  "appellation": "Spring Mountain",
  "quantity": "8",
  "bottlePrice": "75.00",
  "tastingNote": "drink in 2028 too tight right now"
}

4. GET - wine cellar owners:
http://localhost:8080/wine_cellar/owners

5. PUT - update details about a wine - (you find by owner id and wine name)
http://localhost:8080/wine_cellar/owners/??/wines/??
{
  "wineName": "Hall",
  "vintageYear": "2018",
  "varietal": "Malbec",
  "color": "red",
  "appellation": "St. Helena",
  "quantity": "10",
  "bottlePrice": "65.00",
  "tastingNote": "Cigar box, great minerality"
}

6. GET - retrieve wines for an owner cellar
http://localhost:8080/wine_cellar/owners/??/wines  (the owner's id)

7. GET - a specific owner 
http://localhost:8080/wine_cellar/owners/1/

8. PUT - update an owner username
http://localhost:8080/wine_cellar/owners/??/ (the owner's id)

9. DELETE - delete an owner
http://localhost:8080/wine_cellar/owners/??  (the owner's id)

10. GET - retrieve a list of all cellars
http://localhost:8080/wine_cellar/cellars
