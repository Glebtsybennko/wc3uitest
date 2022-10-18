FROM gradle:4.7.0-jdk8-alpine
USER root
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN apk add --no-cache bash

COPY runer.sh /app/
ENTRYPOINT ["/bin/sh"]
CMD ["runer.sh"]
