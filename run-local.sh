#!/bin/bash

./clean-docker.sh

docker-compose -f docker-compose.yml up -d
