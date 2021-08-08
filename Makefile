.PHONY: docker-seata-dubbo docker-seata-server product

docker-seata-dubbo:
	docker build -t slic/seata-dubbo:latest .

docker-seata-server:
	docker build --build-arg seataVersion=1.4.2 -t slic/seata-server:1.4.2 -f seata-server.Dockerfile .

local-m2-cache:
	docker run -ti --rm -v $(PWD):/seata-dubbo -v $(PWD)/.m2:/root/.m2 --entrypoint="" maven:ibmjava-alpine bash -c " cd seata-dubbo && mvn clean package"

docker-seata-dubbo-dev:
	docker build -t slic/seata-dubbo:dev -f Dockerfile.dev .

docker-seata-dubbo-dev-with-cache: local-m2-cache docker-seata-dubbo-dev

product: docker-seata-dubbo docker-seata-server
