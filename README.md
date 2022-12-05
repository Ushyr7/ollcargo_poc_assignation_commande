# ollcargo_poc_assignation_commande

Une preuve de concet pour le projet Ollcargo, pour le micro service d'assignation de commande à un livreur
## Utilisation

On utilise postgreSQL comme base de données, pour démarrer plus rapidement, il est recommandé d'utiliser l'image docker officielle avec la commande:

*docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres*

Ensuite, il faut utiliser le script livreurs.sql pour peupler la base de données.

On utilise aussi Kafka, des fiches techniques détaillant l'installation et l'utilisation sont disponibles sur le drive de l'équipe PoC du projet ollcargo

Enfin, pour lancer le projet, il suffit d'utiliser:

*mvn spring-boot:run*