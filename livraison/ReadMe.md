# Projet d'Analyse des Algorithmes de Tri

Ce projet vise à explorer et analyser différents algorithmes de tri, en mettant l'accent sur leur efficacité en fonction du niveau de désordre des données en entrée. Le projet comprend les éléments suivants :

## Objectif

L'objectif principal de ce projet est d'implémenter et d'analyser une collection d'algorithmes de tri, tout en évaluant leur efficacité en termes de nombre de comparaisons, d'accès aux données et de temps d'exécution total, en fonction du niveau de désordre des données en entrée.

## Fonctionnalités

- Implémentation d'un générateur de tableaux non triés permettant de spécifier un niveau de désordre (quantité et répartition) des données en entrée.
- Implémentation de 12 d'algorithmes de tri.
- Visualisation des algorithmes de tri pour mieux comprendre leur fonctionnement et leur performance.
- Expérimentation des algorithmes de tri en utilisant le générateur de données paramétré pour en tirer une analyse comparative.

## Contenu du Projet

- **Algorithme de Tri :** Ce dossier contient l'implémentation des différents algorithmes de tri.
- **Générateur de Données :** Ce dossier contient le code du générateur de tableaux non triés, permettant de spécifier un niveau de désordre des données en entrée.
- **Visualisation :** Ce dossier contient les fichiers nécessaires à la visualisation des algorithmes de tri.
- **Rapport :** Ce dossier contient le rapport détaillé de l'analyse des performances des algorithmes de tri en fonction du niveau de désordre des données en entrée.


## Commandes utiles

- Représentation visuelle des tris : **$ ant run**

- Tests unitaires : **$ ant run-tests**

- Calculs des temps d'éxécution de tous les algorithms et création csv 
( **ATTENTION** : prend environ 15min et ne doit pas etre interrompu ! (Les fichiers csv sont déja remplis)) :
**$ ant run-analyse**

- Graphique des temps d'éxécution sur différentes listes : **$ ant run-gstats**
*(Dans l'interface python, nous conseillons de cliquer sur la loupe en bas, puis selectionner une petite zone ne comprenant pas les statistiques de Stooge, car ceux-ci impactent la lisibilite des autres algorithmes.)*

- Graphique des temps d'éxécution sur différentes tailles de liste pour un algorithme : **$ ant run-stats**
## Requirement

### Doivent-etre installés sur votre machine :
- **Python 3**

    sudo apt install python3-pip


- **Matplotlib**

    pip install matplotlib

    
- **Pandas**

    pip install pandas
    
    
## Licence


