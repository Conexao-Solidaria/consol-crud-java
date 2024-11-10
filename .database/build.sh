#!/bin/bash

if [ -f .env ]; then

  export $(grep -v '^#' .env | sed 's/\r$//' | xargs)
fi 

docker buildx build --build-arg ROOT_PASSWORD=$ROOT_PASSWORD -t consol-db .
