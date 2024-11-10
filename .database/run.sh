#!/bin/bash

if [[ "$1" == "-d" ]]; then

  echo "Running detached..."

  output=$(docker run --name consol-db -d -p 3306:3306 -i consol-db 2>/dev/null)
  exit_code=$?
  
  if [[ "$exit_code" -ne "0" ]]; then

    echo "Container already exists. Retrying..."

    docker rm -f consol-db

    docker run --name consol-db -d -p 3306:3306 -i consol-db
  fi

  exit 0
fi

  output=$(docker run --name consol-db -d -p 3306:3306 -i consol-db 2>/dev/null)
  exit_code=$?
  
  if [[ "$exit_code" -ne "0" ]]; then
  echo "Container already exists. Retrying..."

  docker rm -f consol-db

  docker run --name consol-db -p 3306:3306 -i consol-db
fi
