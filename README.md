# README #

This is an application that is used to exemplify a basic microservice architecture. The idea of the application is to be able to add reviews and ratings for some movies in the database. The architecture is summarised on the diagram ![Alt text](Movies_app_diagram.png?raw=true "Architecture")
There are three microservices, each exposing a rest interface:
- A Movies microservice (exposed locally on http://movies.info/api/movies)
- A Reviews microservice (exposed locally on http://reviews.info/api/reviews)
- A Ratings microservice (exposed locally on http://ratings.info/api/ratings)


### Building and running the application locally ###

* First, you need to have Kubernetes installed locally - either Docker for desktop or minikube
* Next, install the nginx controller in order to be able to expose the services externally - follow this for Minikube https://kubernetes.io/docs/tasks/access-application-cluster/ingress-minikube/
  or this for docker for desktop, or for a cloud provider (AWS EKS for example) https://kubernetes.github.io/ingress-nginx/deploy/#docker-for-mac
* Once you have a kubernetes cluster setup locally, and an ingress controller deployed, go to each folder (movies, reviews, ratings) and run 
```make all```, each makefile has the following targets that can be run individually 
  
```
.PHONY: build

clean:
./gradlew clean
jar:
./gradlew build -x test
build:
docker build -t crististoica/movies .
push:
docker push crististoica/movies
deploy:
kubectl get deployment movies -o yaml | kubectl delete -f -
kubectl apply -f movies.yaml
expose:
kubectl get svc movies -o yaml | kubectl delete -f -
kubectl expose deploy movies --port=8080 --target-port=8080

ingress:
kubectl apply -f movies-ingress.yaml

all: clean jar build  push deploy expose ingress```
```
**Be sure to update the to your own registry**

* Once deployed, each service will be exposed through their own ingress to their respective URLs, as shown on the first section



### How do I run the frontend? ###

* (Prerequisites) - install npm and angular; you can find the angular version in the package.json folder inside **angular_storefront**
* run ```ng build``` or ```npm run build``` inside angular_storefront
* run ```ng serve``` which will fire up the storefront on ```http://localhost:4200```
* Having the services exposed, you can now use the angular app locally to list and add reviews; each added review will trigger a new ratings calculation (a call will be made from the reviews microservice to the ratings microservice when a new rating is POSTed)

### How can I use this to play with SRE / monitoring principles? ###

* Install AppDynamics agents on all microservices to watch the flows between microservices and the APIs response times
* Use the ratings microservice to play with errors / generating high response times
* Install log aggregation forwarders, e.g. Splunk

