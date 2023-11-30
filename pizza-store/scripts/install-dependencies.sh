rm -rf node_modules/
rm package-lock.json
npm cache clean --force
sudo npm install -g node-gyp
npm install
npm link bcrypt