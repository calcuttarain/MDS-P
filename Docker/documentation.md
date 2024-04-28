## Crearea si rularea containerului
```bash
docker-compose up -d
```

## Conectarea la baza de date de pe serverul MySQL
```bash
docker exec -it mds_container mysql -u root -p db_mds
```