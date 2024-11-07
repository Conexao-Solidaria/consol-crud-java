FROM mysql

ARG ROOT_PASSWORD

ENV MYSQL_ROOT_PASSWORD=$ROOT_PASSWORD
ENV MYSQL_DATABASE='consol'

COPY ./scripts/ /docker-entrypoint-initdb.d/

