#!/bin/bash

if [ "$USE_CBT" = true ]; then
  export REMOTE_DRIVER_USERNAME=$CBT_USERNAME;
  export REMOTE_DRIVER_PASSWORD=$CBT_PASSWORD;
  export COFFEE_MACHINE_LOCATION=https://hiptest.github.io/hps-java-junit-selenium/src/web/coffee_machine.html

  echo "Running test on CBT, using account; $REMOTE_DRIVER_USERNAME";
fi

if [ "$USE_SAUCELABS" = true ]; then
  export REMOTE_DRIVER_USERNAME=$SAUCELABS_USERNAME;
  export REMOTE_DRIVER_PASSWORD=$SAUCELABS_PASSWORD;
  export COFFEE_MACHINE_LOCATION=https://hiptest.github.io/hps-java-junit-selenium/src/web/coffee_machine.html

  echo "Running test on SauceLABS, using account; $REMOTE_DRIVER_USERNAME";
fi
