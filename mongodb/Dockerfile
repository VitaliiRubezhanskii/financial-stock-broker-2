FROM mongo:4.1.13-bionic
VOLUME ["/data/db"]
WORKDIR /
ADD mongodb/replicate.js /replicate.js
ADD mongodb/setup.sh /setup.sh
CMD ["/setup.sh"]