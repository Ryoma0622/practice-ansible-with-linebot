FROM centos:latest
RUN set -x && \
    : "Install wget" && \
    yum -y install wget && \
    : "Install OpenSSH" && \
    yum -y install passwd openssh-server initscripts && \
    yum -y install openssh-clients

CMD ["/sbin/init"]