#!/bin/bash
# COLLECTOR_IP为pinpoint的ip,如果需要配置其它的pinpoint参数，则参照sed进行匹配修改
# applicationName和agentId为pinpoint上的配置
# 如果设置pinpoint为false，则不使用pinpoint
if [ $DEBUG'' = "true" ]; then
    set -x
fi

set -e

if [ $pinpoint'' == "true" ]; then
  sed -i "/profiler.collector.ip=/ s/=.*/=$COLLECTOR_IP/" /root/pinpoint-agent/pinpoint.config
  java $JAVA_OPTS -javaagent:/root/pinpoint-agent/pinpoint-bootstrap-1.8.3.jar \
  -Dpinpoint.applicationName=$applicationName \
  -Dpinpoint.agentId=$agentId \
  -jar /app/app.jar
else
 java $JAVA_OPTS -jar /app/app.jar
fi

exec "$@"
