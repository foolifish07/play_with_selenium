#!/bin/bash

selenium_container=selenium-chrome-standalone

docker kill ${selenium_container}
docker rm ${selenium_container}

docker run -d \
	--publish 4444:4444 \
	--publish 5900:5900 \
	--name ${selenium_container} \
	--volume /dev/shm:/dev/shm \
	selenium/standalone-chrome-debug:latest

unset selenium_container