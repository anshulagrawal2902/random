JAR=target/paymentAPI-1.0-SNAPSHOT.jar
DB_CONTAINER=mysqldb
DB_ROOT_PASSWORD=rootpassword
DB_NAME=mydb

.PHONY: build run db db-init stop clean

build:
	mvn package

run: build
	java -jar $(JAR)

db:
	docker run -d \
		--name $(DB_CONTAINER) \
		-e MYSQL_ROOT_PASSWORD=$(DB_ROOT_PASSWORD) \
		-e MYSQL_DATABASE=$(DB_NAME) \
		-p 3306:3306 \
		mysql:8

db-init:
	echo "CREATE TABLE webhooks ( \
	    id BIGINT AUTO_INCREMENT PRIMARY KEY, \
	    url VARCHAR(500), \
	    active BOOLEAN DEFAULT TRUE, \
	    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP \
	); \
	CREATE TABLE payments ( \
	    id BIGINT AUTO_INCREMENT PRIMARY KEY, \
	    first_name VARCHAR(100), \
	    last_name VARCHAR(100), \
	    zip_code VARCHAR(20), \
	    encrypted_card_number TEXT, \
	    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP \
	);" | docker exec -i $(DB_CONTAINER) mysql -uroot -p$(DB_ROOT_PASSWORD) $(DB_NAME) -h 127.0.0.1

stop:
	docker stop $(DB_CONTAINER) || true
	docker rm $(DB_CONTAINER) || true

clean:
	rm -rf target