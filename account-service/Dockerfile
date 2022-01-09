FROM adoptopenjdk/openjdk11
WORKDIR /
ADD build/libs/account-service.jar account-service.jar
ADD /setup.sh setup.sh
ENTRYPOINT ["/setup.sh"]
CMD ["echo", "!!!!!!!! Container --account-- is available now !!!!!!!!"]