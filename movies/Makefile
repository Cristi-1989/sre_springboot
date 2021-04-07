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

all: clean jar build  push deploy expose ingress