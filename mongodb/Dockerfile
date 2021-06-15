FROM mongo:4.1.13-bionic
VOLUME ["/data/db"]
WORKDIR /
COPY /user.json /user.json
COPY /authClientDetails.json /authClientDetails.json
COPY /mongoAccessToken.json /mongoAccessToken.json
COPY /mongoRefreshToken.json /mongoRefreshToken.json
ADD /replicate.js /replicate.js
#ADD /setup.sh /setup.sh
#CMD ["/setup.sh"]


