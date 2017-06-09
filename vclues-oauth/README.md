OAuth Server
============
This subproject is the OAuth authentication and authorization server.

The OAuth server allows users to revoke access they granted to clients.

OAuth admins can edit and add client applications as well.

Need data from src/main/resources/db/migration

Eclipse tries to access DB schema with Uppercase word

Command line passes; mysql "oauth" database is corrupt and can't be recreated, hence using "oauth2"

http://localhost:8081/oauth/authorize?client_id=curl-client&redirect_uri=http://localhost:8080&return_type=token&response_type=code&grant_type=authorization_code&client_secret=client_credentials
http://localhost:8081/login?logout
http://localhost:8081/oauth/authorize?client_id=curl-client&redirect_uri=http://localhost:8080&return_type=token&response_type=code

abc@gmail.com = client-id
123455678 = client-secret
curl -s -u abc@gmail.com:123455678 -X POST localhost:8081/oauth/token\?grant_type=client_credentials

https://oauth2server.com/auth?response_type=code&
  client_id=CLIENT_ID&redirect_uri=REDIRECT_URI&scope=photos&state=1234zyx