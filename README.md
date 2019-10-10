#WORDS&COUNTRIES

## PROGRAMMING STACK
Java 12, Maven, Gson, Rest API

##DESCRIPTION
Program provides 3 different options.
-learning english words,
-game-quiz about countries,
-show curiosity about today's date.

###Learning english words
User can learn english words using the Sebastian Letiner's method. In this particular case we use 5 json files:

**-firstPartition.json**
**-secondPartition.json**
**-thirdPartition.json**
**-fourthPartition.json**
**-fifthPartition.json**

And 2 additional files:
**-learnedWords.json**
**-translatedWords.json**

Initially some of the English words put into the firstPartition.json file will be drawn. The program will load the translations of the drawn words from the translatedWords.json file or if nothing is found it will download the translation using the SYSTRAN.io API. Then user will have to translate these words from polish language to english. If the answer is correct, this word goes to the next file. Otherwise, the word remains in the firstPartition.json file or returns to it.

The word that has gone through all 5 files goes into the learnedWords.json file.

To start the program it is necessary to paste the key obtained on [this](https://rapidapi.com) page in the indicated place in the HttpSystranService class.

if the translation obtained from SYSTRAN.io is not satisfactory then you can put your own translation in a file translatedWords.json.


![](https://drive.google.com/uc?id=1vSvmoEGydFSZsW7yXcP5soa_u9sYegC9)

###Country game-quiz

The program draws several ISO 3166-1 country codes (2 letters or 3 letters) from the countryCodes.json file. Then, using REST Countries v2, data about the countries are collected.  A further 2 randomly selected country data (like country name, capital, region, subregion) are displayed and another 2 must be specified by the user.

To start the program it is necessary to paste the key obtained on [this](https://rapidapi.com) page in the indicated place in the HttpCountriesService class.

###Curiosity about today date

The program simply connects to the Numbers API and gets an curiosity about today's date.

To start the program it is necessary to paste the key obtained on [this](https://rapidapi.com) page in the indicated place in the HttpNumbersService class.

###Example of file contents

firstPartition.json
```json
{
   "words": [
       "abandon",
       "ability",
       "able"
	   ]
}
```

translatedWords.json
```json
{
  "translatedWords": {
    "size": "rozmiar",
    "zone": "strefa",
    "environment": "środowisko"
	}
}
```

countryCodes.json
```json
{
   "codes": [
       "pl",
	   "en",
	   "de"
	   ]
}

```

###Install

To run program use maven command: **mvn install** from main directory. Then go to menu directory and use command **mvn clean compile assembly:single**. Thanks it in directory menu\target you will find jar with program. Run it using command **java -cp menu-1.0-SNAPSHOT-jar-with-dependencies.jar menu.App**. Remember to fill in missing fields with key obtained from given links.