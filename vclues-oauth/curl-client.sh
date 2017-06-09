#!/bin/sh
# Accquire token from the OAuth server with our client credentials
TOKEN=`curl -s -u 7CH9aSS1ADs7:UUjsQx68GnVx -X POST localhost:8081/oauth/token\?grant_type=client_credentials | egrep -o '[a-f0-9-]{20,}'`
echo "Got token $TOKEN"
curl http://localhost:8080/disputes/1/1 -H "Authorization: Bearer $TOKEN"
