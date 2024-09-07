# How to run this

```
docker run --network=host --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres  
docker exec -it $(docker ps -q --filter "ancestor=postgres") psql -U postgres -c "CREATE DATABASE cake_factory_db;"

-javaagent:/home/lucas/dev/presentations/javaflameToolsInAction/javaflame.jar=filter:com.example.cakeFactory
```
