# Micronaut for Spring Example

Example showing the problem that custom spring formatters
are not available in micronaut-spring.

Launch the application via 

```bash
./gradlew bootRun
```

Then in a 2nd shell send the following http request

```bash
http ":8080/greet?tenantId=7364b7d8-490d-49b9-8e18-7b96f41734e8"
HTTP/1.1 400 Bad Request
Date: Sun, 2 Dec 2018 09:16:43 GMT
connection: close
content-length: 184
content-type: application/json

{
    "_links": {
        "self": {
            "href": "/greet?tenantId=7364b7d8-490d-49b9-8e18-7b96f41734e8",
            "templated": false
        }
    },
    "message": "Required argument [TenantId tenantId] not specified",
    "path": "/tenantId"
}
```
