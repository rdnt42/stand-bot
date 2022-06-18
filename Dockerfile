FROM openjdk:17-oracle
WORKDIR /opt
ENV PORT 8083
EXPOSE 8083
COPY target/stand-bot.jar /opt/stand-bot.ja
ENTRYPOINT exec java $JAVA_OPTS -jar stand-bot.ja