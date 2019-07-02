FROM centos:latest
RUN set -x && \
    : "Install wget" && \
    yum -y install wget && \
    : "Add EPEL" && \
    wget https://dl.fedoraproject.org/pub/epel/epel-release-latest-7.noarch.rpm && \
    rpm -ivh epel-release-latest-7.noarch.rpm && \
    : "Install ansible" && \
    yum -y install ansible && \
    : "Install OpenSSH" && \
    yum -y install passwd openssh-server initscripts && \
    yum -y install openssh-clients

CMD ["/sbin/init"]