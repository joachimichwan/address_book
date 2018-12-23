FROM alpine:edge
RUN apk add --no-cache python3 && \
python3 -m ensurepip && \
rm -r /usr/lib/python*/ensurepip && \
pip3 install --upgrade pip setuptools && \
if [ ! -e /usr/bin/pip ]; then ln -s pip3 /usr/bin/pip ; fi && \
if [[ ! -e /usr/bin/python ]]; then ln -sf /usr/bin/python3 /usr/bin/python; fi && \
rm -r /root/.cache
RUN mkdir /code
WORKDIR /code
ADD requirements.txt /code/
RUN pip install -r requirements.txt
CMD python3 manage.py makemigrations && python3 manage.py migrate
ADD . /code/
ENTRYPOINT ["/usr/bin/python3"]
CMD ["/code/manage.py", "runserver", "8080"]
EXPOSE 8080