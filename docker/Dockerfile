FROM mongo:4.1.13-bionic
VOLUME ["/data/db"]
WORKDIR /
ADD ./replicate.js /replicate.js
ADD ./setup.sh /setup.sh
CMD ["/setup.sh"]