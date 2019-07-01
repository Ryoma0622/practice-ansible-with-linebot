# syntax = docker/dockerfile:experimental
FROM openjdk:latest

RUN set -x && \
    mkdir /root/.ssh/ && \
    yum -y update && \
    yum -y install git && \
    : "make sure your domain is accepted" && \
    touch /root/.ssh/known_hosts && \
    ssh-keyscan -t rsa github.com >> /root/.ssh/known_hosts
RUN --mount=type=secret,id=ssh,dst=/root/.ssh/id_rsa git clone git@github.com:Ryoma0622/line-bot.git

WORKDIR ./line-bot
RUN ./gradlew build -x test
