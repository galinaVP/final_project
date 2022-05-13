# FINAL PROJECT

## Running tests

To run the tests with maven, enter the command in the terminal:

``` mvn clean test ```

To parameterize test run, enter the command:
```
mvn clean test -Dsuite=testng -Dbrowser=chrome -DthreadCount=2 -DbrowserWidth=1920 -DbrowserHeight=1080
```
### Parameters:

* **-Dsuite=testng** - where "***testng***" is the name of the XML file to run


* **-Dbrowser=chrome** - where "***chrome***" is the browser in which the tests will open.

    The following parameters are possible:
  * chrome (default)
  * firefox
  * edge


* **-DthreadCount=2** - where "***2***" is the number of threads.  The default value is "1".


* **-DbrowserWidth=1920** - where "***1920***" is the width of the browser window. Default "1920"


* **-DbrowserHeight=1080** - where "***1080***" is the height of the browser window. The default is "1080".

## Running Reports
To generate a report, enter the command:

``` mvn allure::serve ```

> ***Слава Україні!*** 
