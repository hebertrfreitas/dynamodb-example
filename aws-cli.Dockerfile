FROM amazon/aws-cli:2.4.16
RUN yum install nmap -y
ENTRYPOINT [ "/bin/sh", "-c" ]