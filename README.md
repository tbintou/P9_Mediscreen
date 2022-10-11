## Technologie et Prerequis

- Java 11 JDK
- Maven
- Spring boot
- MySQL
- MongoDB
- Dockerfile
- Docker compose



## L'application Mediscreen est composée principalement de 3 microservices et un front :

- **Microservice: Patient**
- **Microservice: Note**
- **Microservice: Rapport**
- **Microservice: Front**



## Pour démarrer le projet

- Importez d'abord les 4 backend.

# Créer vos base de donnée :
- MySQL (nom de BDD, password, et username, se trouve dans application.properties)
- MongDB (nom de BDD, password, et username, se trouve dans application.properties)


# Créer vos images docker:
- Ouvrir un terminal dans le repertoire ou se trouve chaque Dockerfile.
- Executer la commande docker pour créer vos images : docker build -t nom_image .


# Démarrer l'application avec docker-compose :
- Peut être que vous allez avoir besoin de changer certaines informations dans les services docker-compose :
     Environnement MySQL, Volume, et networks.

- Ouvrir le terminal dans la racine de l'application ou se trouve le fichier docker-compose.
- Exécuter cette commande pour démarrer l'application :  docker-compose up -d


## Vérifiez le projet sur l'URL :
      http://localhost:8080/login