# ExploringRailwayProgramming

Kata permettant d'explorer ROP aka Railway Oriented Programming.

Largemement repompé du Kata MaitreD de Scott Wlaschin pour être passé en Java.

Objectifs : 

## 1- Identifier et maitriser les effets de bords
Identifier les types d'erreurs et choisir une façon de les gérer(String, enum, Record,...)
Créer les types Result/Success/Failure utilisant ces erreurs.
Dans le code, passer par des if instance of pour identifier les différents cas.

## 2- Passer sur du Railway
Implémenter dans Result les méthodes map/flatMap pour permettre de simplifier le code métier
Constater les éventuelles méthodes manquantes

## 3- S'outiller côté tests
Créer des méthodes spécifiques pour checker Success et Failure.
