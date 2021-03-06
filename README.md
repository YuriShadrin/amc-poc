# AMC POC

## Build
Before start you must have the JDK 1.7+ and Maven 3.3.3+ installed.

* Download Easy Rest Client (ERC) as ZIP: https://github.com/nextlets/erc/archive/master.zip
* Unzip ERC, go to the ERC directory (```erc-master```)
* Build ERC as: ```mvn clean install```
* Download AMC POC project as ZIP: https://github.com/YuriShadrin/amc-poc/archive/master.zip
* Unzip AMC POC, go to the project directory (```amc-poc-master```)
* Build project as: ```mvn clean install```

## Run
:warning: Before running tests you must configure AMC POC as described below.

All tests are run using maven:
```
mvn exec:java  -P <profile> [-Dexec.args="params"]
```

Where profile - name of profile, one of the following:
* facebook
* twitter
* googleplus
* youtube
* wikipedia
* tumblr
* instagram

For example:
```
mvn exec:java  -P youtube
```

All tests have hardcoded default parameters, parameters can be redefined using ```-Dexec.args``` command line argument, for example:
```
mvn exec:java -P googleplus -Dexec.args="'Pink Floyd' 'Brit Floyd'"
```
or
```
mvn exec:java -P youtube -Dexec.args="YR5ApYxkU-U"
```
or
```
mvn exec:java -P wikipedia -Dexec.args="Britney_Spears"
```
or
```
mvn exec:java -P instagram -Dexec.args="ledzeppelin"
```

## Output
Result of all tests is output to stdout and to a file inside ```target/logs``` directory, name of result file is corresponded to the name of used profile, for example, for test
```
mvn exec:java -P youtube
```
result will be written to the file
```
target/logs/youtube.log
```

## Configuration

:warning: Warning: if configuration was changed, you need to rebuild AMC POC!

### Facebook
* Register as developer on https://developers.facebook.com
* Go to "My Apps -> Add a New App" or "My Apps -> choose the existing App" 
* Get App ID, App Secret
* If you need to get AUTH TOKEN, goto https://developers.facebook.com/tools/explorer/

Change configuration in: ```src/main/resources/keys/facebook_keys.properties```
```properties
app.id=APP_ID
app.secret=APP_SECRET
```

--------------- 

### Twitter
* Register as developer on https://dev.twitter.com/ Go to
* https://apps.twitter.com/ Create New App or choose the existing Look for
* Consumer Key, click "manage keys and access tokens" link Generate Customer
* Key and Secret Generate Access Token and Token Secret

Change configuration in: ```src/main/resources/keys/twitter_keys.properties```
```properties
consumer.key=CONSUMER_KEY
consumer.secret=CONSUMER_SECRET
access.token=ACCESS_TOKEN
access.token.secret=ACCESS_TOKEN_SECRET
```

--------------- 

### Youtube
* Create Google account
* Go to https://console.developers.google.com/
* Create new project (click "Enable and manage APIs" link) or choose the existing
* Go to "APIS & auth -> APIs"
* Look for "YouTube Data API" link, click it
* Click "Enable API" button 
* Go to "APIS & auth -> Credentials"
* Add credentials -> API key -> Server key
* Click "Create" button, get API key from opened popup window  

Change configuration in: ```src/main/resources/keys/youtube_keys.properties```
```properties
api.key=API_KEY
```

--------------- 

### Google+
* Create Google account
* Go to https://console.developers.google.com/
* Create new project (click "Enable and manage APIs" link) or choose the existing
* Go to "APIS & auth -> APIs"
* Look for "Google+ API" link, click it
* Click "Enable API" button 
* Look for "Google+ Domains API" link, click it
* Click "Enable API" button 
* Go to "APIS & auth -> Credentials"
* Add credentials -> API key -> Server key
* Click "Create" button, get API key from opened popup window  

Change configuration in: ```src/main/resources/keys/googleplus_keys.properties```
```properties
client.id=CLIENT_ID
client.secret=CLIENT_SECRET
```

:warning: During first run a default browser will be opened with request to provide access to your profile.

--------------- 

### Wikipedia
Configuration is not needed.
Data is received from http://stats.grok.se

### Tumblr
 * Goto https://www.tumblr.com/docs/en/api/v2 Look for "register an application" link, click it
 * Register your application
 * On page https://www.tumblr.com/docs/en/api/v2 click link "API Console" (or just goto https://api.tumblr.com/console/calls/user/info)
 * On API Console you'll see consumer.key, consumer.secret, access.token and access.token.secret

Change configuration in: ```src/main/resources/keys/tumblr_keys.properties```
```properties
consumer.key=CONSUMER_KEY
consumer.secret=CONSUMER_SECRET
access.token=ACCESS_TOKEN
access.token.secret=ACCESS_TOKEN_SECRET
```

:warning: Tumblr has a limitation for creation apps -- 1 per day.

:information_source: Tumblr API provides possibility to get info about current user (i.e. for user associated with provided tokens), how to get info about other users -- it is unknown.

### Instagram
 * Create an account in Instagram (via mobile app)
 * Goto https://instagram.com/developer
 * Register a new client
 * Generate access token (follow instruction on https://instagram.com/developer/authentication)

Change configuration in: ```src/main/resources/keys/instagram_keys.properties```
```properties
access.token=ACCESS_TOKEN
```

### Metacritic
:x: Unfortunately **Metacritic** requires subscription to API, even for the basic subscription you need to enter bank card requisites which issues in USA:

https://market.mashape.com/byroredux/metacritic/pricing

### Zap2it
:x: Unfortunately API for **Zap2it** was not found.
