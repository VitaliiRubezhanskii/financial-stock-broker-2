#FROM mongo:4.1.13-bionic
FROM mongo
VOLUME ["/data/db"]
WORKDIR /
COPY /user.json /user.json
COPY /authClientDetails.json /authClientDetails.json
COPY /mongoAccessToken.json /mongoAccessToken.json
COPY /mongoRefreshToken.json /mongoRefreshToken.json
EXPOSE 27017
ADD /setup.sh /setup.sh
CMD ["/setup.sh"]



