# Disseny d'Estructures de Dades - Pràctica 1

  
## Autor

David Borrega Borrella

## Introducció

Aquest projecte inclou la definició, implementació i el joc de proves de l'aplicació **Sport4EventsClub** amb l'objectiu principal de gestionar els esdeveniments esportius que organitza el club.

Per a poder desenvolupar el projecte, s'han elaborat les següents tasques d'acord amb la solució de la **PAC1**:

 - Implementació de **SportEvents4ClubImpl** de la interfície **SportEvents4Club**.
 - Definició i implementació de les diferents entitats.
 - Definició i implementació de les excepcions.
 - Definició i implementació de nous TADS.
 - Definició i implementació del joc de proves **SportEvents4ClubPR1Test**.
 - Definició i implementació de joc de proves adicionals.
  
Tota la implementació s'ha realizat conforme la signatura inicial de la interfície proposada.

## Estructura
El projecte s'estructura en dues principals parts:

 - **src**: estructura principal del projecte.
 - **test**: estructura del joc de proves.
  

### Interfície
La interfície a implementar és **SportEvents4Club**. Per tal de respectar la definició inicial i la signatura dels diferents mètodes que l'inclouen, no s'ha fet cap modificació sobre aquesta.

Al llarg del projecte s'han definit alguns comentaris sobre el codi amb l'etiqueta *ToDo* proposant alguna modificació de cara a pròximes versions de l'aplicació.

### Models
Els models que s'han implementat són els que s'han proposat en l'enunciat de la pràctica:

 - **Player**: entitat corresponent al jugador.
 - **OrganizingEntity**: entitat corresponent als organitzadors d'esdeveniments.
 - **SportEvent**: entitat corresponent als esdeveniments esportius.
 - **File**: entitat corresponent a la fitxa de l'esdeveniment.
 - **Rating**: entitat corresponent a la valoració d'un esdeveniment.

Totes aquestes entitats tenen en comú:

 - Definició d'un constructor accessible, on s'hi passen tots els camps per paràmetre.
 - Definició de getters i setters.
 - Mètodes públics i privats adicionals per satisfer diferents necessitats del projecte.
#### Player
Emmagatzema una llista encadenada d'esdeveniments esportius.
#### OrganizingEntity
Emmagatzema una llista encadenada d'esdeveniments esportius.
#### SportEvents
 - Emmagatzema una cua d'inscripcions de jugadors.
 - Emmagatzema una llista encadenada de valoracions.

### Tipus de TADS utilitzats
  Per al desenvolupament correcte de la implementació **SportEvents4ClubImpl** és necessari utilitzar la llibreria de *TADS* que ofereix l'assignatura. 
  Per una banda, s'utilitzen els següents TAD ja existents en la llibreria:
   - **LinkedList** *<edu.uoc.ds.adt.sequential>*: per a la implementació de llistes encadenades.
   - **QueryArrayImpl** <*edu.uoc.ds.adt.sequential*>: per a la implementació de les cues.

D'una altra, s'han creat nous TAD:

 - **OrderedVector** *<uoc.ds.pr.util>*: per a la implementació de vector ordenat per a gestionar els millors esdeveniments esportius. *Implementa els TAD: FiniteContainer.*
 - **OrderedVectorDictionary** *<uoc.ds.pr.util>*: per a la implementació de vector ordenat per a gestionar els esdeveniments esportius. *Implementa els TAD: DictionaryArrayImpl, FiniteContainer.*


### Excepcions

  
## Change Log
  
## Joc de proves

## Annex