**Aufgabe:** Notendatenbank
**Autoren:** Domenic Muskulus, Alexander Mersmann

## Installation

1. IntelliJ starten  
2. Im Quick Start "Check out from Version Control" wählen  
2.1. Git auswählen  
2.2. https://github.com/domu1de/IAAHausarbeit.git clonen und Dialog mit "yes" bestätigen
3. Event "Maven projects need to be imported" mit "Enable Auto-Import" bestätigen
4. Rechtsklick Projekt > Maven > Reimport
5. File > Project Structure > Facets  
5.1. Hibernate hinzufügen  
5.2. Struts2 hinzufügen  
5.3. Struts2 > File Sets > Alle vier hinzufügen  
5.4. Spring hinzufügen  
5.5. Spring > File Sets > Alle drei hinzufügen
6. File > Project Structure > Artifacts  
6.1. Unter naxexammgmt:war exploded > Available Libraries  
    "Maven__org_thymeleaf_extras_thymeleaf-extras-java8time_2_1_4-SNAPSHOT" per Doppelklick hinzufügen  
7. Run > Edit Configurations  
7.1. Lokalen Tomcat hinzufügen  
7.2. Deployment > Artifact hinzufügen > nakexammgmt:war wählen  
7.3. Application context setzen  
8. Fertig
