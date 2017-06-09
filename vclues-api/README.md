#!/bin/sh
# Accquire token from the OAuth server with our client credentials
TOKEN=`curl -s -u 7CH9aSS@ADs$:&UjsQx6$GnVx -X POST localhost:8081/oauth/token\?grant_type=client_credentials | egrep -o '[a-f0-9-]{20,}'`
echo "Got token $TOKEN"
curl localhost:8080/disputes/1/1 -H "Authorization: Bearer $TOKEN"

https://oauth2server.com/auth?response_type=code&
  client_id=CLIENT_ID&redirect_uri=REDIRECT_URI&scope=photos&state=1234zyx

client_secret: UUjsQx68GnVx

http://localhost:8081/oauth/authorize?client_id=7CH9aSS1ADs7&&return_type=token&grant_type=client_credentials&client_secret=UUjsQx68GnVx&response_type=code&redirect_uri=http://localhost:8080



API = nordic-oauth allows nordic-api access to resource.
	  external users with api access sends a request and if allowed access by nordic-oauth, it will be allowed access via nordic-api

Configure nordic-api to be recognized by noridic-oauth
Configure for thrid party access via browser request to oauth then to nordic-api

partner-services

