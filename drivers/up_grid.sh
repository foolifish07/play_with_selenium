#!/bin/bash

here=$(cd $(dirname "${0}"); pwd)

docker-compose --project-name selenium_grid \
	--file ${here}/docker-compose.yml \
	down

docker-compose --project-name selenium_grid \
	--file ${here}/docker-compose.yml \
	up -d

unset here