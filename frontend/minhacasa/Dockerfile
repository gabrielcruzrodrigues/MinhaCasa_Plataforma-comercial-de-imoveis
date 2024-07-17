FROM node:18
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
CMD [ "ng", "serve", "--port", "9090", "--host", "0.0.0.0" ]