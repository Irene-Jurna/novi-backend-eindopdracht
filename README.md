# README - RESTful webservice Kapsalon De Smidse

## Inleiding

Deze README bevat installatie-instructies en een overzicht van de RESTful endpoints voor de backend van het kappers-systeem van Kapsalon De Smidse. Dit project is ontwikkeld als eindopdracht voor de backend-cursus aan de NOVI Hogeschool voor ICT.

Auteur: Irene Jurna\
Datum: Juni 2023

## Benodigdheden

Voordat je de applicatie kunt installeren en uitvoeren, moet je ervoor zorgen dat de volgende benodigdheden zijn geïnstalleerd:

1. Server of localhost: Zorg voor een server of lokale omgeving waar de applicatie kan draaien. Deze handleiding gaat ervan uit dat je de applicatie op localhost uitvoert.

2. Java Development Kit (JDK): Download en installeer JDK 8 of hoger. Dit is de runtime environment voor Java die nodig is om de applicatie te starten.

   Download JDK: [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) of [OpenJDK](https://adoptopenjdk.net/)

3. PostgreSQL en pgAdmin: De applicatie maakt gebruik van PostgreSQL als database en pgAdmin als databasebeheertool.

   Download PostgreSQL: [PostgreSQL Downloads](https://www.postgresql.org/download/)

   Download pgAdmin: [pgAdmin Downloads](https://www.pgadmin.org/download/)

4. Postman: Om API-verzoeken naar de applicatie te kunnen sturen, gebruiken we Postman. Download en installeer Postman of gebruik de webbrowser-versie.

   Download Postman: [Postman Download](https://www.postman.com/downloads/)

5. Integrated Development Environment (IDE): Het project is ontwikkeld in IntelliJ IDEA, maar je kunt ook je favoriete IDE gebruiken.

   Download IntelliJ IDEA: [IntelliJ IDEA Download](https://www.jetbrains.com/idea/)

## Gebruikersrollen en inloggegevens

Het systeem werkt met drie verschillende gebruikersrollen. Elke gebruiker heeft één rol toegewezen:

- ROLE_OWNER: Deze gebruiker heeft toegang tot alle methoden.
- ROLE_HAIRDRESSER: Deze gebruiker heeft toegang tot methoden voor klanten, kan kassabonnen maken en heeft toegang tot product- en behandelinformatie.
- ROLE_CUSTOMER: Deze gebruiker heeft toegang tot methoden die relevant zijn voor klanten, zoals het aanmaken van een profiel, het reserveren van een afspraak en het aanpassen of verwijderen van het eigen profiel.

Bij het aanmaken van een nieuwe gebruiker moet je een gebruikersnaam, wachtwoord en rol opgeven. Hieronder vind je de inloggegevens van de vooraf aangemaakte gebruikers:

Gebruikersnaam (username) | Wachtwoord (password) | Rol
--- | --- | ---
martinjurna | geheim123 | ROLE_OWNER
gerdinesmit | wachtwoord123 | ROLE_HAIRDRESSER
inakorrema | inlogcode1 | ROLE_HAIRDRESSER
daniellediesman | dd12345 | ROLE_HAIRDRESSER
mariannetimmer | liefdevoorschaatsen123 | ROLE_CUSTOMER
pietpieters | klompenpad4 | ROLE_CUSTOMER