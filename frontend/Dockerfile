FROM node:18
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm install -g @angular/cli@17
RUN npm run build --prod
# Instala um servidor HTTP estático
RUN npm install -g http-server

# Expõe a porta que o servidor HTTP vai usar
EXPOSE 8080

# Comando para rodar o servidor HTTP estático
CMD ["http-server", "dist/minhacasa", "-p", "8080"]