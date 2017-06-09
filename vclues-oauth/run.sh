#!/bin/sh
# Accquire token from the OAuth server with our client credentials
TOKEN=`curl -s -u "7CH9aSS@ADs$:&UjsQx6$GnVx" -X POST http://localhost:8081/oauth2/token\?grant_type=client_credentials | egrep -o '[a-f0-9-]{20,}'`
echo "Got token $TOKEN"
curl localhost:8080/oauth2/token -H "Authorization: Bearer $TOKEN"
