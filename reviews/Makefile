.PHONY: build

clean:
	./gradlew clean
jar:
	./gradlew build -x test
build:
	docker build -t crististoica/reviews .
push:
	docker push crististoica/reviews
deploy:
	kubectl apply -f reviews.yaml
expose:
	kubectl get svc reviews -o yaml | kubectl delete -f -
	kubectl expose deploy reviews --port=8081 --target-port=8081

ingress:
	kubectl apply -f reviews-ingress.yaml

all: clean jar build  push deploy expose ingress