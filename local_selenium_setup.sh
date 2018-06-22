#!/bin/bash

if [ "$USE_LOCAL_DRIVER" = true ]; then
  /sbin/start-stop-daemon --start --quiet --pidfile /tmp/custom_xvfb_99.pid --make-pidfile --background --exec /usr/bin/Xvfb -- :99 -ac -screen 0 1280x1024x16;
  echo "Started XFVB";
  cat /tmp/custom_xvfb_99.pid;

  wget -N https://chromedriver.storage.googleapis.com/2.35/chromedriver_linux64.zip -P ~/
  unzip ~/chromedriver_linux64.zip -d ~/
  rm ~/chromedriver_linux64.zip
  sudo mv -f ~/chromedriver /usr/local/share/
  sudo chmod +x /usr/local/share/chromedriver
  sudo ln -s /usr/local/share/chromedriver /usr/local/bin/chromedriver
  google-chrome-stable --headless --disable-gpu --remote-debugging-port=9222 http://localhost &
fi
