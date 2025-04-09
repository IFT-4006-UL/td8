# TD11

## Prérequis

* Java JDK (au minimum la version 18)
* [Maven 3](https://maven.apache.org/download.cgi)
* Node.js (au minimum la version 18.3) et npm (au minimum la version 10) pour l'interface utilisateur du TP4 (le téléchargement de Node.js inclut npm)

## Démarrer le projet

### Démarrer le serveur backend
Démarrer le serveur backend en exécutant `Main`.

Vous pouvez aussi démarrer le serveur via maven:

```bash
mvn clean install
mvn exec:java
```

Le serveur sera accessible à l'adresse suivante: [http://localhost:8181](http://localhost:8181).
Vous pouvez valider que le serveur est démarré avec [http://localhost:8181/health](http://localhost:8181/health).

### Démarrer le serveur frontend

Pour démarrer le serveur frontend, exécutez les commandes suivantes:

1. Déplacez-vous dans le dossier `webapp`:
```bash
cd webapp
```

2. Installez les dépendances:
```bash
npm install
```

3. Démarrez le serveur:
```bash
npm run dev
```

L'application web sera accessible à l'adresse suivante: [http://localhost:5173](http://localhost:5173)

## Rouler les tests

Pour rouler tous les tests du backend, exécuter la commande suivante:

```bash
mvn test
```

Pour rouler tous les tests unitaires de l'interface, exécuter la commande suivante:
```bash
npm run test:unit
```

Pour rouler les tests bout en bout avec Cypress, exécuter la commande suivante:

Avec Windows:
```bash
./run-e2e.sh
```
Ou avec Linux:
```bash
source run-e2e.sh
```

Ce script démarre le serveur backend avec les bases de données en mémoire, puis démarre le serveur frontend et finalement exécute les tests Cypress.

Une fenêtre de navigateur s'ouvrira pour exécuter les tests.

## Exercices

L'application fournie permet d'ajouter des livres à une librairie.

### 1. Implémenter les tests unitaires de l'interface avec Vitest

Utiliser Vitest pour implémenter les tests unitaires suivants:
- Le titre et l'auteur sont réinitialisés si l'ajout est un succès
- Le titre et l'auteur ne sont pas réinitialisés si l'ajout échoue
- Un message de succès est affiché avec le nom du livre si l'ajout est un succès

### 1. Implémenter les tests bout-en-bouts avec Cypress

Utiliser Cypress pour implémenter le test bout-en-bout suivant:
- Lors de l'ajout réussi d'un livre, le nombre total est augmenté de 1.