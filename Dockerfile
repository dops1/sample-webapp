FROM tomcat:9.0.0

ADD target/sample-webapp*.war /usr/local/tomcat/webapps/app.war

EXPOSE 8080

CMD ["bin/catalina.sh", "run"]