FROM registry.cn-shenzhen.aliyuncs.com/openquan/tools:openjdk-8
VOLUME /tmp
RUN mkdir -p /app
ADD ${appName}-${version}.jar /app/app.jar
ADD runboot.sh /app/
RUN bash -c 'touch /app/app.jar'
WORKDIR /app
RUN chmod a+x runboot.sh
CMD /app/runboot.sh
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezoneorg
