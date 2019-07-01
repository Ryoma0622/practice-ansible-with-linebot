FROM centos:latest
COPY nginx.repo /etc/yum.repos.d/nginx.repo
RUN set -x && \
  yum update -y && \
  : "Install nginx." && \
  yum install -y nginx
COPY ./conf.d /etc/nginx/conf.d
EXPOSE 80
CMD ["/usr/sbin/nginx", "-g", "daemon off;"]