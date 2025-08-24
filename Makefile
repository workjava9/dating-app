.PHONY: build test run docker-build docker-up seed helm-install
build:
	./gradlew clean build
test:
	./gradlew clean test
run:
	docker compose up -d --build
docker-build:
	docker build -t geomax/dating-app:local .
docker-up:
	docker compose up -d
seed:
	bash scripts/seed.sh
helm-install:
	helm upgrade --install geomax-dating deploy/helm/dating-app --set image.repository=geomax/dating-app --set image.tag=local
