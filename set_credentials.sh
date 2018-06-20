#!/bin/bash

if $USE_CBT; then
  export REMOTE_DRIVER_USERNAME=$CBT_USERNAME;
  export REMOTE_DRIVER_PASSWORD=$CBT_PASSWORD;

  echo "Running test on CBT, using account; $REMOTE_DRIVER_USERNAME";
fi

if $USE_SAUCELABS; then
  export REMOTE_DRIVER_USERNAME=$SAUCELABS_USERNAME;
  export REMOTE_DRIVER_PASSWORD=$SAUCELABS_PASSWORD;

  echo "Running test on SauceLABS, using account; $REMOTE_DRIVER_USERNAME";

fi
