FROM mongo:4.1.13-bionic
VOLUME ["/data/db"]
WORKDIR /
COPY mongodb/user.json /user.json
COPY mongodb/authClientDetails.json /authClientDetails.json
COPY mongodb/mongoAccessToken.json /mongoAccessToken.json
COPY mongodb/mongoRefreshToken.json /mongoRefreshToken.json
ADD mongodb/replicate.js /replicate.js
ADD mongodb/setup.sh /setup.sh
CMD ["/setup.sh"]


