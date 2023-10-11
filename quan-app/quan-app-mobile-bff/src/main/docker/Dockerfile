FROM java:8
VOLUME /tmp
RUN mkdir -p /app
ADD quan-app-mobile-bff-1.0-SNAPSHOT-exec.jar /app/app.jar
ADD runboot.sh /app/
RUN bash -c 'touch /app/app.jar'
WORKDIR /app
RUN chmod a+x runboot.sh
CMD /app/runboot.sh
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezoneorg
