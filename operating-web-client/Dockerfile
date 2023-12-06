FROM node:19

WORKDIR /usr/app

COPY package*.json .

RUN npm install
RUN npm install -g serve

COPY . /usr/app/

EXPOSE 3000

RUN npm run build

CMD [ "serve", "-s", "build" ]