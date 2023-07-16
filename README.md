# rimbatest
Technical Test Rimba

## Vue
port: 8080
>src/frontend/ run `vue serve`

## Java
port: 8081
>connect to mysql, change the application.properties

### Api:  
  **Product**
  - GET: `/api/products`
  - POST: `api/products/upsert  `
  
  **Transacation**
  - GET: `/api/transaction`
  - GET: `/api/transaction/{id}`
  - POST: `/api/createtransaction`
