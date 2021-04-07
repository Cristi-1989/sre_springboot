.PHONY: build

clean:
	./gradlew clean
jar:
	./gradlew build -x test
build:
	docker build -t crististoica/ratings .
push:
	docker push crististoica/ratings
deploy:
	kubectl get deployment ratings -o yaml | kubectl delete -f -
	kubectl apply -f ratings.yaml
expose:
	kubectl get svc ratings -o yaml | kubectl delete -f -
	kubectl expose deploy ratings --port=8082 --target-port=8082
ingress:
	kubectl apply -f ratings-ingress.yaml

all: clean jar build  push deploy expose ingress