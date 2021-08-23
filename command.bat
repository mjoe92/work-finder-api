docker network create work-finder-api_network
docker run --rm --name work-finder-api_postgres -e POSTGRES_PASSWORD=Monkey92 -e POSTGRES_USER=postgres -e POSTGRES_DB=work_finder_api_db -p 5432:5432 -d --network work-finder-api_network postgres
docker run --rm --name work-finder-api -p 8080:8080 --network work-finder-api_network work-finder-api