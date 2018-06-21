#!/bin/bash

if [ "$USE_LOCAL_DRIVER" = true ]; then
  /sbin/start-stop-daemon --start --quiet --pidfile /tmp/custom_xvfb_99.pid --make-pidfile --background --exec /usr/bin/Xvfb -- :99 -ac -screen 0 1280x1024x16;
  echo "Started XFVB";
  cat /tmp/custom_xvfb_99.pid;
fi
