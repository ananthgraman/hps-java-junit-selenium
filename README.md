# hps-java-junit + Selenium

 [![Build Status](https://travis-ci.org/hiptest/hps-java-junit-selenium.svg?branch=master)](https://travis-ci.org/hiptest/hps-java-junit-selenium)

Hiptest publisher samples with Java/JUnit + Selenium

In this repository you'll find tests generated in Junit format from [Hiptest](https://hiptest.net), using [Hiptest publisher](https://github.com/hiptest/hiptest-publisher).

The goals are:

 * to show how tests are exported in JUnit.
 * to check exports work out of the box (well, with implemented actionwords in java)

System under test
------------------

The SUT is a (not that much) simple coffee machine. You start it, you ask for a coffee and you get it, sometimes. But most of times you have to add water or beans, empty the grounds. You have an automatic expresso machine at work or at home? So you know how it goes :-)

Update tests
-------------

To update the tests:

    hiptest-publisher -c junit-selenium.conf --only=tests

The tests are generated in [``src/test/java/com/coffeemachine/``](https://github.com/hiptest/hps-java-junit-selenium/blob/master/java/src/test/java/com/coffeemachine/)

Run tests locally with Selenium
-------------------------------

First, you must use [``maven``](http://maven.apache.org/) to build the project and run the tests:

    mvn package

The SUT implementation can be seen in [``src/web/coffee_machine.js``](https://github.com/hiptest/hps-java-junit-selenium/blob/master/java/src/web/coffee_machine.js)

By default test report is generated in ```target/surefire-reports/TEST-com.coffeemachine.*.xml```

Run tests using Selenium + SauceLabs
------------------------------------

You will first need an account on [SauceLabs](https://saucelabs.com). Once this is done, you will need to export a few variables to ensure you are using SauceLabs driver:

```shell
export REMOVE_DRIVER_USERNAME=<your username on SauceLabs>
export REMOVE_DRIVER_PASSWORD=<your access key on SauceLabs>
export REMOTE_DRIVER_URL=@ondemand.saucelabs.com:443/wd/hub
export USE_REMOTE_DRIVER=true
```

Run the tests as previously done:

    mvn package

This should now use SauceLabs to run the tests

