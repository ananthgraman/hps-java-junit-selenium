# hps-java-junit + Selenium

 [![Build Status](https://travis-ci.org/hiptest/hps-java-junit-selenium.svg?branch=master)](https://travis-ci.org/hiptest/hps-java-junit-selenium)

Hiptest publisher samples with Java/JUnit + Selenium

In this repository you'll find tests generated in Junit format from [Hiptest](https://hiptest.net), using [Hiptest publisher](https://github.com/hiptest/hiptest-publisher).

The goals are:

 * to show how tests are exported in JUnit.
 * to check exports work out of the box (well, with implemented actionwords in java)

Important note: if you are using this repository to try out [our CI in 5 minutes flat tutorial](https://hiptest.com/start/tutorials/ci-in-5-minutes-flat/), lease note that this specific hiptest-publisher sample project has some specificities, so please check the link [using this repository for the CI tutorial](#using-this-repository-for-the-ci-tutorial) and this end of this file.

System under test
------------------

The SUT is a (not that much) simple coffee machine. You start it, you ask for a coffee and you get it, sometimes. But most of times you have to add water or beans, empty the grounds. You have an automatic expresso machine at work or at home? So you know how it goes :-)

Update tests
-------------

To update the tests:

    hiptest-publisher -c junit-selenium.conf --only=tests

The tests are generated in [``src/test/java/com/coffeemachine/``](https://github.com/hiptest/hps-java-junit-selenium/tree/master/src/test/java/com/coffeemachine)

Run tests locally with Selenium
-------------------------------
[![Hiptest Status](https://app.hiptest.com/badges/test_run/169285)](https://app.hiptest.com/projects/1512/test-runs/169285/overview)

First, you must use [``maven``](http://maven.apache.org/) to build the project and run the tests:

    mvn package

The SUT implementation can be seen in [``src/web/coffee_machine.js``](https://github.com/hiptest/hps-java-junit-selenium/blob/master/java/src/web/coffee_machine.js)

By default test report is generated in ```target/surefire-reports/TEST-com.coffeemachine.*.xml```

Run tests using Selenium + Cross browser testing
------------------------------------------------
[![Hiptest Status](https://app.hiptest.com/badges/test_run/169284)](https://app.hiptest.com/projects/1512/test-runs/169284/overview)

You will first need an account on [Cross browser testing](https://crossbrowsertesting.com). Once this is done, you will need to export a few variables to ensure you are using SauceLabs driver:

```shell
export REMOTE_DRIVER_USERNAME=<your username on Cross Browser Testing>
export REMOTE_DRIVER_PASSWORD=<your access key on Cross Browser Testing>
export REMOTE_DRIVER_URL=hub.crossbrowsertesting.com:80/wd/hub
export USE_REMOTE_DRIVER=true
export USE_CBT=true
```

You will also need to specify where the system under test is located, for example:

```shell
export COFFEE_MACHINE_LOCATION=https://hiptest.github.io/hps-java-junit-selenium/src/web/coffee_machine.html
```

Run the tests as previously done:

    mvn package

This should now use CrossBrowserTesting to run the tests.

Run tests using Selenium + SauceLabs
------------------------------------
[![Hiptest Status](https://app.hiptest.com/badges/test_run/101683)](https://app.hiptest.com/projects/1512/test-runs/101683/overview)

You will first need an account on [SauceLabs](https://saucelabs.com). Once this is done, you will need to export a few variables to ensure you are using SauceLabs driver:

```shell
export REMOTE_DRIVER_USERNAME=<your username on SauceLabs>
export REMOTE_DRIVER_PASSWORD=<your access key on SauceLabs>
export REMOTE_DRIVER_URL=ondemand.saucelabs.com:443/wd/hub
export USE_REMOTE_DRIVER=true
export USE_SAUCELABS=true
```


You will also need to specify where the system under test is located, for example:

```shell
export COFFEE_MACHINE_LOCATION=https://hiptest.github.io/hps-java-junit-selenium/src/web/coffee_machine.html
```

Run the tests as previously done:

    mvn package

This should now use SauceLabs to run the tests

Using this repository for the CI tutorial
-----------------------------------------

The configuration for Travis-CI on this particular repository is a bit different than usual, as it runs on three environments (a local Selenium driver, SauceLabs and CrossBrowserTesting), so you will not be able to follow the CI steps just as the other ones.
If this is your first try at this tutorial, it may be a good idea to try it with the [hps-java-junit](https://github.com/hiptest/hps-java-junit) example first. It will be easier to handle the basics on Hiptest integration with a CI tool on this more simple example.

Now if you use this repository with Hiptest, you will have a few solutions on how to do it:
 * play only the tests with a local Selenium web driver
 * play the tests on SauceLabs or CrossBrowserTesting
 * play the tests on the three environments

 Let's cover the three use cases.

 ### Using Travis-CI and local webdriver

 First edit the .travis.yml file and replace this section:

     env:
      - USE_LOCAL_DRIVER=true TEST_RUN_ID=169285
      - USE_REMOTE_DRIVER=true USE_SAUCELABS=true REMOTE_DRIVER_URL=ondemand.saucelabs.com:80/wd/hub TEST_RUN_ID=101683
      - USE_REMOTE_DRIVER=true USE_CBT=true REMOTE_DRIVER_URL=hub.crossbrowsertesting.com:80/wd/hub TEST_RUN_ID=169284

By:

    env:
      USE_LOCAL_DRIVER=true

Then, replace ```--test-run-id=$TEST_RUN_ID```by ```--test-run-id=<if of the test run you created in Hiptest>```. Now you should be able to have your tests running and pushed back to Hiptest.

Note: we're still facing issues when trying to run the tests on TravisCI with a local Selenium Web Driver. So if your tests fail, it may not be due to an error you have made.

 ### Using Travis-CI and SauceLabs

 You will need you First edit the .travis.yml file and replace this section:

     env:
      - USE_LOCAL_DRIVER=true TEST_RUN_ID=169285
      - USE_REMOTE_DRIVER=true USE_SAUCELABS=true REMOTE_DRIVER_URL=ondemand.saucelabs.com:80/wd/hub TEST_RUN_ID=101683
      - USE_REMOTE_DRIVER=true USE_CBT=true REMOTE_DRIVER_URL=hub.crossbrowsertesting.com:80/wd/hub TEST_RUN_ID=169284

By:

    env:
      USE_REMOTE_DRIVER=true
      USE_SAUCELABS=true
      COFFEE_MACHINE_LOCATION=https://hiptest.github.io/hps-java-junit-selenium/src/web/coffee_machine.html

Also, remove the line ```source set_credentials.sh``` in the script section of the .travis.yml file.

Then, replace ```--test-run-id=$TEST_RUN_ID```by ```--test-run-id=<if of the test run you created in Hiptest>```.
Also, in the TravisCI project settings, set the REMOTE_DRIVER_USERNAME and REMOTE_DRIVER_PASSWORD environment variables with your credentials from SauceLabs (for the REMOTE_DRIVER_PASSWORD value, go to your profile on SauceLabs and copy your access key). Be sure to mark those variables as hidden in the build.

 ### Using Travis-CI and CrossBrowserTesting

 You will need you First edit the .travis.yml file and replace this section:

     env:
      - USE_LOCAL_DRIVER=true TEST_RUN_ID=169285
      - USE_REMOTE_DRIVER=true USE_SAUCELABS=true REMOTE_DRIVER_URL=ondemand.saucelabs.com:80/wd/hub TEST_RUN_ID=101683
      - USE_REMOTE_DRIVER=true USE_CBT=true REMOTE_DRIVER_URL=hub.crossbrowsertesting.com:80/wd/hub TEST_RUN_ID=169284

By:

    env:
      USE_REMOTE_DRIVER=true
      USE_CBT=true
      COFFEE_MACHINE_LOCATION=https://hiptest.github.io/hps-java-junit-selenium/src/web/coffee_machine.html

Also, remove the line ```source set_credentials.sh``` in the script section of the .travis.yml file.

Then, replace ```--test-run-id=$TEST_RUN_ID```by ```--test-run-id=<if of the test run you created in Hiptest>```.
Also, in the TravisCI project settings, set the REMOTE_DRIVER_USERNAME and REMOTE_DRIVER_PASSWORD environment variables with your credentials from SauceLabs (for the REMOTE_DRIVER_PASSWORD value, go to your profile on SauceLabs and copy the Authkey). Be sure to mark those variables as hidden in the build.

### Using TravisCI and all three environments

First, you will need three different test runs in your Hiptest project. Note the IDs of each test runs and replace accordingly in the .travis.yml file, in this section:

     env:
      - USE_LOCAL_DRIVER=true TEST_RUN_ID=169285
      - USE_REMOTE_DRIVER=true USE_SAUCELABS=true REMOTE_DRIVER_URL=ondemand.saucelabs.com:80/wd/hub TEST_RUN_ID=101683
      - USE_REMOTE_DRIVER=true USE_CBT=true REMOTE_DRIVER_URL=hub.crossbrowsertesting.com:80/wd/hub TEST_RUN_ID=169284

On the first line, use the test run you want to dedicate to local Selenium webdriver. On the second one, use the ID of the SauceLabs test run and the last one if for the CrossBrowserTesting test run.

Then, in the Travis project settings, declare the four following environment variables:

    CBT_USERNAME
    CBT_PASSWORD
    SAUCELABS_USERNAME
    SAUCELABS_PASSWORD

Once the next build, you should have results pushed to your three test runs.
