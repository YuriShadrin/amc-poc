# AMC POC

## Build
Before start you must have the JDK 1.7+ and Maven 3.3.3+ installed.

* Download ERC dependency as ZIP: https://github.com/YuriShadrin/easy-rest-client/archive/master.zip
* Unzip ERC, go to ERC directory (```easy-rest-client-master```)
* Build ERC as: ```mvn clean install```
* Download AMC POC project as ZIP: https://github.com/YuriShadrin/amc-poc/archive/master.zip
* Unzip AMC POC, go to project directory (```amc-poc-master```)
* Build project as: ```mvn clean install```

## Run

## Configuration

### Facebook

Change configuration in: src/main/resources/keys/facebook_keys.properties
```properties
app.id=APP_ID
app.secret=APP_SECRET
```

* Register as developer on https://developers.facebook.com
* Go to "My Apps -> Add a New App" or "My Apps -> choose the existing App" 
* Get App ID, App Secret
* If you need to get AUTH TOKEN, goto https://developers.facebook.com/tools/explorer/

--------------- 

### Twitter
Change configuration in: src/main/resources/keys/twitter_keys.properties
```properties
consumer.key=CONSUMER_KEY
consumer.secret=CONSUMER_SECRET
access.token=ACCESS_TOKEN
access.token.secret=ACCESS_TOKEN_SECRET
```

* Register as developer on https://dev.twitter.com/ Go to
* https://apps.twitter.com/ Create New App or choose the existing Look for
* Consumer Key, click "manage keys and access tokens" link Generate Customer
* Key and Secret Generate Access Token and Token Secret

--------------- 

### Youtube
Change configuration in: src/main/resources/keys/youtube_keys.properties
```properties
api.key=API_KEY
```

* Create Google account
* Go to https://console.developers.google.com/
* Create new project (click "Enable and manage APIs" link) or choose the existing
* Go to "APIS & auth -> APIs"
* Look for "YouTube Data API" link, click it
* Click "Enable API" button 
* Go to "APIS & auth -> Credentials"
* Add credentials -> API key -> Server key
* Click "Create" button, get API key from opened popup window  

--------------- 

### Google+
Change configuration in: src/main/resources/keys/googleplus_keys.json
```json
{
  "installed": {
    "client_id": "CLIENT_ID",
    "client_secret":"CLIENT_SECRET"
  }
}
```

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

