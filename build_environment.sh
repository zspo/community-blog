mkdir -p /data/services/CommunityFrontendServer
mkdir -p /data/services/CommunityServer
mkdir -p /data/images/
mkdir -p /data/resource/
########################################################################
yum install java-1.8.0-openjdk-devel.x86_64
########################################################################
yum install mysql
yum install mysql-devel

cd /data/resource/
wget http://dev.mysql.com/get/mysql80-community-release-el7-5.noarch.rpm
rpm -ivh mysql80-community-release-el7-5.noarch.rpm
yum install mysql-community-server

service mysqld restart

cat /var/log/mysqld.log | grep password
mysql -u root -p

alter user 'root'@'localhost' identified by '这里填你要的密码';

vim /etc/my.cnf
[mysql]
default-character-set =utf8

GRANT ALL PRIVILEGES ON *.* TO 'root'@localhost WITH GRANT OPTION;
FLUSH PRIVILEGES;

service mysqld restart


1、执行登陆MySQL mysql -u root -p 密码
2、执行use mysql;
3、执行update user set host = '%' where user = 'root';
4、执行FLUSH PRIVILEGES;
########################################################################
sudo yum -y install nginx
sudo systemctl enable nginx
sudo service nginx start

systemctl restart nginx
