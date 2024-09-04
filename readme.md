# Keycloak
## run
run for the first time
```bash
docker run -d --name Keycloak--Identity-and-Access-Management-for-Modern-Applications-2nd-Edition -p 8080:8080 \
        -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin \
        -v ./docker/keycloak/export:/opt/keycloak/data/import \
        quay.io/keycloak/keycloak:24.0.5 \
        start-dev --import-realm
```
start container
```bash
docker start Keycloak--Identity-and-Access-Management-for-Modern-Applications-2nd-Edition
```

http://localhost:8080


## export config

export specific realm
```bash
docker exec -u root -t -i Keycloak--Identity-and-Access-Management-for-Modern-Applications-2nd-Edition /bin/bash
mkdir /tmp/export
/opt/keycloak/bin/kc.sh export --file /tmp/export/myrealm-realm.json --users realm_file --realm myrealm
/opt/keycloak/bin/kc.sh export --file /tmp/export/third-party-provider-realm.json --users realm_file --realm third-party-provider
docker cp Keycloak--Identity-and-Access-Management-for-Modern-Applications-2nd-Edition:/tmp/export/ docker/keycloak/
```
export all 

```bash
docker exec -u root -t -i Keycloak--Identity-and-Access-Management-for-Modern-Applications-2nd-Edition /bin/bash

/opt/keycloak/bin/kc.sh export --dir /tmp/export --users realm_file
docker cp Keycloak--Identity-and-Access-Management-for-Modern-Applications-2nd-Edition:/tmp/export/ docker/keycloak/
```
# Chapter02
you should have Keycloak running with the realm created. In summary, what
you require before continuing is the following:
- Keycloak up and running
- A realm named myrealm
- A global role named myrole
- A user with the preceding role
- 
# Chapter04
you need Keycloak running, a realm with 
a user that you can log in with, and a client with the following configuration:
* Client ID: oidc-playground
* Client authentication: Off
* Authentication flow: Standard flow
* Valid Redirect URIs: http://localhost:8000/
* Web Origins: http://localhost:8000

## run
```bash
npm install
npm start
```
http://localhost:8000/

# Chapter05
have a realm with a user with the myrole global role and a client with the following configuration:
* Client type: OpenID Connect
* Client ID: oauth-playground
* Client authentication: Off
* Valid Redirect URIs: http://localhost:8000/
* Web Origins: http://localhost:8000
  
Validating access tokens
export SECRET=YoU7Myr2ayqiYufJxN21DiRkhYyU0D6b
export TOKEN=eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJvRzBxQmJsYWVYNVdyRkw5WGltYzRFWDVZamJUcm9GaThlSVo3MmVsYkhVIn0.eyJleHAiOjE3MjA2MDI0MzUsImlhdCI6MTcyMDYwMjEzNSwiYXV0aF90aW1lIjoxNzIwNjAyMTM1LCJqdGkiOiIyZjY2ZTdkZS1jNDJmLTQ5NzItOWRhYy02YzA5MDc3ZjE0ODciLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvcmVhbG1zL215cmVhbG0iLCJhdWQiOiJvYXV0aC1iYWNrZW5kIiwic3ViIjoiNzkyMDc4ZjItMjc1YS00MDE4LWJlMmQtNTEwOGVkMWVhMWU1IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoib2F1dGgtcGxheWdyb3VuZCIsInNlc3Npb25fc3RhdGUiOiI5YTU1MmJiZS1mYzk0LTQ5ZWUtODQ0Ni1hMDkyZWU2OWI4ZjMiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODAwMCJdLCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJzaWQiOiI5YTU1MmJiZS1mYzk0LTQ5ZWUtODQ0Ni1hMDkyZWU2OWI4ZjMiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJPbGEgTm9yZG1hbm4iLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJrZXljbG9hayIsImdpdmVuX25hbWUiOiJPbGEiLCJmYW1pbHlfbmFtZSI6Ik5vcmRtYW5uIiwiZW1haWwiOiJrZXljbG9ha0BrZXljbG9hay5vcmciLCJwaWN0dXJlIjoiaHR0cHM6Ly9jZG4uanNkZWxpdnIubmV0L2doL2Fsb2hlL2F2YXRhcnMvcG5nL3ZpYnJlbnRfMTEucG5nIn0.fsEXmtKv4Kbn9rXLEVd_MLHclV-FU1_h_r_LCEDda27Iyg4A73iNC8PzFUmAI9HoBgtwCs9gAB_vJmCH2l8HkG5GIqhDvGXKjXEY0C03nIu3d1_Ka-r0whm1NJDi6CxUaCIAXPepM4R1M0s8wJN5ITtJoHaob7jAazCl0IyLPBVvSfWYmwXEGtQdg04S-zmpcEAkglGhmRFMulLLMXk4DkbL1HfpRSQIh1yDxSxnXF_KEU2OZwXL6Uxubw9O8tWfGKWRwlqREGGB40j1kMuzfgRr9KIboLOP6nGLti0MV9UBFpV2BkfYBL_MT5AdoXQ5FLcmNyS8zQjCJYBL5LVOhA

curl --data "client_id=oauth-backend&client_secret=$SECRET&token=$TOKEN" \
http://localhost:8080/realms/myrealm/protocol/openid-connect/token/introspect | jq

# Chapter06
## example 1
Client on keycloak
* Client ID: cli
* Client authentication: Off
* Standard flow: Enabled
* Valid Redirect URIs: http://127.0.0.1/callback
  
npm install
node app.js

## example 2
Client on keycloak
* Client ID: tv
* Client authentication: Off
* OAuth 2.0 Device Authorization Grant: Enabled
* 
curl --data "client_id=tv" http://localhost:8080/realms/myrealm/protocol/openid-connect/auth/device | jq
{
  "device_code": "BNxxRnwG9mO9a5NTCE6SIa0-ykqujSsV1JLYd3zP0qw",
  "user_code": "CLDW-JOLS",
  "verification_uri": "http://localhost:8080/realms/myrealm/device",
  "verification_uri_complete": "http://localhost:8080/realms/myrealm/device?user_code=CLDW-JOLS",
  "expires_in": 600,
  "interval": 5
}

open http://localhost:8080/realms/myrealm/device?user_code=XPHS-PIDG

export CODE=BNxxRnwG9mO9a5NTCE6SIa0-ykqujSsV1JLYd3zP0qw

curl --data "grant_type=urn:ietf:params:oauth:grant-type:device_code" --data "client_id=tv" --data "device_code=$CODE" -X POST http://localhost:8080/realms/myrealm/protocol/openid-connect/token | jq

## example 3
Client on keycloak
* Client type: OpenID Connect
* Client ID: service
* Client authentication: On
* Standard flow: Unchecked
* Implicit Flow Enabled: Unchecked
* Direct Access Grants Enabled: Unchecked
* Service accounts roles: Checked

export SECRET=kvsXQ7qsHfpW3bo50XJ7jeoPubrQ7qR7

curl --data "client_id=service&client_secret=$SECRET&grant_type=client_credentials" http://localhost:8080/realms/myrealm/protocol/openid-connect/token | jq

# Chapter07
## mybrowserapp client
• Client ID: mybrowserapp
• Root URL: http://localhost:3000
• Valid Redirect URI: /*
• Web origins: +

cd chapter07/keycloak-js-adapter
npm install && npm start

## mywebapp client:
• Client ID: mywebapp
• Client authentication: ON
• Root URL: http://localhost:3000
• Valid Redirect URI: /*

### Spring App

cd chapter07/springboot/frontend/
./mvnw spring-boot:run

### NodeJS App
cd chapter07/springboot/frontend
npm install && npm start

## backend application:
• Client ID: mybackend
• Client authentication: ON
• Direct Access Grants: ON
• Root URL: http://localhost:3000

### Spring App
cd chapter07/springboot/backend/
./mvnw spring-boot:run

export access_token=$(curl -X POST http://localhost:8080/realms/myrealm/protocol/openid-connect/token \
 -d "client_id=mybackend&client_secret=bNN89hCvu3PdP5kO8CcwEdgkMvzExQNn" \
 -H "content-type: application/x-www-form-urlencoded" \
 -d "username=keycloak&password=keycloak&grant_type=password" \
 | jq --raw-output ".access_token" \
)

curl -X GET http://localhost:3000/hello  -H "Authorization: Bearer $access_token"

### NodeJS App
cd chapter07/nodejs/backend
npm install && npm start
export access_token=$(curl -X POST http://localhost:8080/realms/myrealm/protocol/openid-connect/token \
 -d "client_id=mybackend&client_secret=bNN89hCvu3PdP5kO8CcwEdgkMvzExQNn" \
 -H "content-type: application/x-www-form-urlencoded" \
 -d "username=keycloak&password=keycloak&grant_type=password" \
 | jq --raw-output ".access_token" \
)
curl -X GET http://localhost:3000/hello  -H "Authorization: Bearer $access_token"
## proxy-client

• Client ID: proxy-client
• Client authentication: ON
• Root URL: http://localhost:3000
• Valid Redirect URI: /*reverse proxy

cd chapter07/reverse-proxy
docker run --rm httpd:latest cat /usr/local/apache2/conf/httpd.conf > httpd.conf

docker build -t httpd-proxyenabled .
docker run -d  -p 3000:80 --name Keycloak--Identity-and-Access-Management-for-Modern-Applications-2nd-Edition-apache-proxy httpd-proxyenabled

cd chapter07/reverse-proxy/app
npm install && npm start

# Chapter08

Client ID: myclient-mapping-group
Username : alice

# Chapter09

openssl genrsa -out ./localcerts/key.pem
openssl req -new -key ./localcerts/key.pem -out ./localcerts/my.keycloak.org.pem
openssl x509 -req -days 365 -in ./localcerts/my.keycloak.org.pem -signkey ./localcerts/key.pem -out ./localcerts/my.keycloak.org.pem

cat ./localcerts/my.keycloak.org.pem ./localcerts/key.pem > ./localcerts/my.keycloak.org1.pem

cd chapter09

docker compose stop haproxy && docker compose rm  haproxy && docker compose up -d

## http
http://keycloak-admin-srv:8080/health
http://keycloak-admin-srv:8080/admin/master/console/

## Https

docker compose up -d

https://my.keycloak.org:8443/

https://my.keycloak.org/health
https://my.keycloak.org/admin/master/console/
https://my.keycloak.org/realms/master/.well-known/openid-configuration

## Cluster
docker compose -f docker-compose-cluster.yml up -d

https://my.keycloak.org/health
https://my.keycloak.org/admin/master/console/
https://my.keycloak.org/realms/master/.well-known/openid-configuration

# Chapter10
account console

http://localhost:8080/realms/myrealm/account

cd chapter10

docker compose up -d

phpldapadmin
https://localhost:6443/
Login DN: cn=admin,dc=mycompany,dc=com
Password: admin

KEYCLOAK_HOST_PORT=${1:-"localhost:8080"}
ADMIN_TOKEN=$(curl -s -X POST "http://$KEYCLOAK_HOST_PORT/realms/master/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=admin" \
  -d 'password=admin' \
  -d 'grant_type=password' \
  -d 'client_id=admin-cli' | jq -r '.access_token')

echo "ADMIN_TOKEN=$ADMIN_TOKEN"

LDAP_ID=$(curl -si -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/myrealm/components" \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '@docker/ldap/ldap-config.json' \
  | grep -oE '[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}')

## third-party-provider
docker start Keycloak--Identity-and-Access-Management-for-Modern-Applications-2nd-Edition

we are going to use another realm in the same Keycloak server to represent the third-party identity provider we are trying to integrate with
- create a realm in Keycloak called third-party-provider

- create a client in third-party-provider realm with the following settings:
   - Client ID: broker-app
   - Client authentication: ON
   - Root URL:http://localhost:8080/realms/myrealm/broker/oidc/endpoint

      Client Secret:WD5fHad1giQZpCDDsHeu430tWYpKLxvM

- create a user called third-party-user in the third-party-provider realm

  username:third-party-user

  pass:third-party-user
-  create a new identity provider in the myrealm realm
    - Discovery endpoint:http://localhost:8080/realms/third-party-provider/.well-known/openid-configuration
    -Display Name: My Third-Party Provider
    -Client Authentication: Client secret sent as post
    -Client ID: broker-app
    -Client Secret: WD5fHad1giQZpCDDsHeu430tWYpKLxvM
- open http://localhost:8080/realms/myrealm/account and Click on the My Third-Party Provider button
- enter third-party-user/third-party-user

## Integrating with social identity providers
- Create an OAuth app at https://github.com/settings/developers
    - Application name:keycloak-broker-idp
    - Homepage URL:http://localhost:8080/realms/myrealm/account
    - Application description:Integrating keycloak with social identity providers
    - Authorization callback URL:http://localhost:8080/realms/myrealm/broker/github/endpoint
- Add Github provider 
    - Redirect URI:ttp://localhost:8080/realms/myrealm/broker/github/endpoint
    - Client ID:Ov23li85H4EJacrOtk9n
    - Client Secret:c79a3ed0fc1128839bbda1f3e925d6a416ce6668
- open http://localhost:8080/realms/myrealm/account and Click on the My Third-Party Provider button
- Click on the GitHub button to be redirected to GitHub to authenticate
# Chapter11

http://localhost:8080/realms/myrealm/account
login with alice/alice

# Chapter13

cd chapter13/mytheme
docker cp target/mytheme.jar Keycloak--Identity-and-Access-Management-for-Modern-Applications-2nd-Edition:/opt/keycloak/providers
docker restart Keycloak--Identity-and-Access-Management-for-Modern-Applications-2nd-Edition

http://localhost:8080/realms/myrealm/account