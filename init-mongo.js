const menuData = [{
    "_id": {
      "$oid": "656867bea86441b2bb493364"
    },
    "name": "Margherita Pizza",
    "weight": 300,
    "price": 9.99
  },
  {
    "_id": {
      "$oid": "656867d2a86441b2bb493365"
    },
    "name": "Chicken Alfredo Pasta",
    "weight": 400,
    "price": 11.99
  },
  {
    "_id": {
      "$oid": "656867dca86441b2bb493366"
    },
    "name": "Caesar Salad",
    "weight": 200,
    "price": 7.99
  },
  {
    "_id": {
      "$oid": "656867f0a86441b2bb493367"
    },
    "name": "Classic Cheeseburger",
    "weight": 250,
    "price": 8.99
  },
  {
    "_id": {
      "$oid": "657092c235a1509d416c66da"
    },
    "name": "Coca Cola",
    "weight": 500,
    "price": 2.55
  }
]

db = new Mongo().getDB("dronora");

db.createCollection('menuitems', { capped: false });

db.menuitems.insert(menuData);
