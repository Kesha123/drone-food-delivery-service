FROM node:19

WORKDIR /usr/src/app

COPY package.json ./

RUN npm cache clean --force
RUN npm install -g node-gyp
RUN npm install
RUN npm link bcrypt

COPY . .

RUN npm run build

EXPOSE 3000

CMD [ "npm", "run", "start:prod" ]