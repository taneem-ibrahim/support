FROM RHEL7

MAINTAINER Taneem <taneem.ibrahim@gmail.com>

COPY /configuration/ /configuration/
COPY /modules/ /modules/
COPY /bin/ /bin/
RUN chmod -R +r /configuration
RUN chmod -R +r /modules
RUN chmod -R +r /bin

CMD ["/bin/sh"]
