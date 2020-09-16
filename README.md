#防火墙启动和关闭（重启后失效）
#service iptables start/stop
#防火墙启动和关闭（重启后永久性生效）
#chkconfig iptables on/off
#linux直接进入mysql命令
#mysql -uroot -p
#mysql启动/关闭
#service mysql start/stop
#nacos启动
#./startup.sh -m standalone
#查看有几个nacos启动
#ps -ef|grep nacos|grep -v grep | wc -l
#es启动
#./elasticsearch -d