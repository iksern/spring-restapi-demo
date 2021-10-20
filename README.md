# spring-restapi-demo
 This is a demo application that implements the use of batch processing (read txt file and write to MSSQL database), REST API (GET to return custom query data), basic authentication, and pagination.

Test with the following examples with an API tester (e.g. POSTMAN):
1. localhost:8080/accountTransactions/showAll?pageSize=5&pageNo=0
2. localhost:8080/accountTransactions/account/8872838283?pageSize=5&pageNo=1
3. localhost:8080/accountTransactions/customer/222?pageSize=10&pageNo=2
4. localhost:8080/accountTransactions/description/FUND%20TRANSFER?pageSize=10&pageNo=0
