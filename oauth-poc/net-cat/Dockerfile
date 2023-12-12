FROM alpine:3.14
RUN apk update --no-cache
COPY http /home
COPY nc-server /usr/local/bin
CMD [ "nc-server" ]
#ENTRYPOINT ["sh"]