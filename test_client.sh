#! /bin/bash

HOSTNAME=$1
PORT=$2

./gradlew installDist
./client/build/install/client/bin/client $HOSTNAME $PORT
