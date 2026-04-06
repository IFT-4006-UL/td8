# TD11

## Prérequis

* Java JDK (au minimum la version 18)
* [Maven 3](https://maven.apache.org/download.cgi)
* Node.js (au minimum la version 18.3) et npm (au minimum la version 10) (le téléchargement de Node.js inclut npm)

## Démarrer le projet

### 1. Démarrer le serveur backend

**Option A** — Depuis IntelliJ: exécutez la classe `Main`.

**Option B** — Depuis le terminal:
```bash
mvn clean install
mvn exec:java
```

Le serveur sera accessible à l'adresse suivante: [http://localhost:8181](http://localhost:8181).
Vous pouvez valider que le serveur est démarré avec [http://localhost:8181/health](http://localhost:8181/health).

### 2. Démarrer le serveur frontend

```bash
# Déplacez-vous dans le dossier `webapp`
cd webapp
# Installez les dépendances
npm install
# Démarrez le serveur
npm run dev
```

L'application web sera accessible à l'adresse suivante: [http://localhost:5173](http://localhost:5173)

## Rouler les tests

### Tests backend (Java)

Pour rouler tous les tests du backend, on exécute normalement la commande suivante:

```bash
mvn test
```

### Tests unitaires frontend (Vitest)

Pour rouler tous les tests unitaires de l'interface avec `Vitest`, exécutez la commande suivante:

```bash
cd webapp
npm run test:unit
```

### Tests bout en bout (Cypress)

Les tests Cypress nécessitent que le backend démarré en mode test (avec des bases de données en mémoire).

**Méthode 1 — Deux terminaux:**

Terminal 1 — démarrer le backend en mode test:
```bash
mvn exec:java "-Dexec.args=TEST"
```

Terminal 2 — builder le frontend et lancer Cypress (dans le dossier `webapp`):
```bash
cd webapp
npm run build
npm run test:e2e
```

**Méthode 2 — Script bash (Git Bash requis sur Windows):**

**Important (Windows):** `java` et `mvn` doivent être accessibles depuis le terminal (dans le PATH système).
IntelliJ inclut ses propres copies de Java et Maven, mais celles-ci ne sont pas accessibles depuis le terminal par défaut.
Voir la section [Configuration du PATH sur Windows](#configuration-du-path-sur-windows) si ces commandes ne sont pas reconnues.

```bash
bash run-e2e.sh
```

Ce script automatise la méthode 1: il démarre le backend en mode test, attend qu'il soit prêt, lance les tests Cypress, puis arrête le backend.

**Note:**
Si vous relancez les tests sans redémarrer le backend, vous pourriez obtenir des erreurs `BOOK_ALREADY_PRESENT`.
Arrêtez le backend (`Ctrl+C`) et relancez-le avant de réexécuter les tests.

## Configuration du PATH sur Windows

Si `java` ou `mvn` ne sont pas reconnus dans votre terminal, vous pouvez utiliser les versions incluses dans IntelliJ.

**Trouver le chemin du JDK:** Dans IntelliJ, allez dans File > Project Structure > SDKs et notez le chemin (ex: `C:\Users\VOTRE_USER\.jdks\ms-21.0.8`).

**Trouver le chemin de Maven:** Cherchez `mvn.cmd` dans votre installation IntelliJ (ex: `C:\Program Files\JetBrains\IntelliJ IDEA 2026.1\plugins\maven\lib\maven3\bin`).

**Ajouter au PATH (PowerShell en administrateur):**
```powershell
[Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Users\VOTRE_USER\.jdks\ms-21.0.8", "User")
[Environment]::SetEnvironmentVariable("Path", $env:Path + ";C:\Users\VOTRE_USER\.jdks\ms-21.0.8\bin", "User")
[Environment]::SetEnvironmentVariable("Path", $env:Path + ";C:\Program Files\JetBrains\IntelliJ IDEA 2026.1\plugins\maven\lib\maven3\bin", "User")
```

Fermez et réouvrez votre terminal, puis vérifiez avec `java -version` et `mvn --version`.

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