## REST service for a merchant to add offers on their products.

#### To run the service:
```
./gradlew bootRun
```

#### To list all offers:
```
curl -X GET http://localhost:8080/offer' 
```

#### To list one offer:
```
curl -X GET http://localhost:8080/offer/<ID>'
```

#### To list all products:
```
curl -X GET http://localhost:8080/product' 
```

#### To get one product:
```
curl -X GET http://localhost:8080/product/<ID>'
```

#### To add a product:
```
curl -X POST \
  http://localhost:8080/product \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F 'name=A product' \
  -F currency=GBP \
  -F price=100
```

#### To add an offer:
```
curl -X POST \
  http://localhost:8080/offer \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F productId=1 \
  -F 'description=New offer' \
  -F currency=GBP \
  -F price=100 \
  -F startTime=2018-01-21 \
  -F endTime=2029-01-10 \
```

#### To list offers by product ID:
```
curl -X GET http://localhost:8080/product/<ID>/offers
```

#### To cancel an offer:
```
curl -X DELETE http://localhost:8080/offer/<ID>
```
