FROM node:16 as develop-stage
WORKDIR /app
COPY package*.json ./
RUN yarn
COPY . .

# build stage
FROM develop-stage as build-stage
RUN yarn run build

# production stage
FROM nginx:alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 90
CMD ["nginx", "-g", "daemon off;"]