#!/bin/bash
# 【skywalking agent】
# 如果设置skywalking为true，表示开启skywalking接入
# SW_AGENT_NAME: skywalking中显示的服务名,请确保此环境下唯一
# SW_GRPC_LOG_SERVER_HOST: 日志上报地址，默认是asoco-skywalking, 如果你和skywalking在一个k8s集群下，则无需配置，否则需要配置可访问的ip地址
# SW_GRPC_LOG_SERVER_PORT: 日志上报端口，和SW_GRPC_LOG_SERVER_HOST是一对，默认是11800，同样，如果和skywalking不在一个集群，则必须是skywalking后端11800映射出的端口
# SW_AGENT_COLLECTOR_BACKEND_SERVICES: 链路追踪的地址(ip+port)，默认是asoco-skywalking:11800,和日志上报是同一个地址。因此同样和skywalking不在一个集群时，需配置ip+端口

if [ $DEBUG'' = "true" ]; then
    set -x
fi

set -e

cmd="java $JAVA_OPTS "

if [ $skywalking'' == "true" ]; then
  cmd="$cmd -javaagent:/root/skywalking-agent/skywalking-agent.jar \
  -Dskywalking.agent.service_name=${SW_AGENT_NAME:-"risk-monitor-warning"} \
  -Dskywalking.plugin.toolkit.log.grpc.reporter.server_host=${SW_GRPC_LOG_SERVER_HOST:-"asoco-skywalking"} \
  -Dskywalking.plugin.toolkit.log.grpc.reporter.server_port=${SW_GRPC_LOG_SERVER_PORT:-"11800"} \
  -Dskywalking.collector.backend_service=${SW_AGENT_COLLECTOR_BACKEND_SERVICES:-"asoco-skywalking:11800"} "
fi

cmd="$cmd -jar /app/app.jar"
echo "execute command: $cmd"
eval "$cmd"

exec "$@"
