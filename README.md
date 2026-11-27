# Profil Développeur Interactif

## 1. Description de l'application
Application Android développée avec **Jetpack Compose** et **Material Design 3** permettant de consulter un profil développeur et ses projets de manière interactive : recherche par mot-clé (titre, technologie, description), gestion des favoris, filtrage des favoris et compteur dynamique des projets affichés.

## 2. États gérés par l'application
L'état principal est regroupé dans `ProfileUiState` :
* `searchQuery` : texte saisi dans la barre de recherche.
* `showOnlyFavorites` : booléen activant le filtre « Favoris uniquement ».
* `favoriteProjectIds` : liste des identifiants des projets favoris.
* `projects` : liste des projets filtrés actuellement visibles à l'écran (sert aussi au compteur).

## 3. État local
**Quel état avez-vous conservé directement dans un composable et pourquoi ?**
L'état `isExpanded` (`rememberSaveable { mutableStateOf(false) }`) dans `ContactSection`, qui affiche ou masque les coordonnées de contact. C'est un état purement visuel et local qui ne concerne pas la logique métier du `ViewModel`, tout en survivant à la rotation de l'écran.

## 4. State Hoisting
**Pourquoi votre composable d'écran principal peut-il être considéré comme stateless ?**
`ProfileScreen` ne stocke aucun état : il reçoit `uiState` en paramètre et remonte toutes les actions utilisateur via des callbacks (`onSearchQueryChange`, `onToggleFavoriteFilter`, `onFavoriteClick`, `onResetFilters`).

## 5. ViewModel
**Quel est le rôle de votre ViewModel dans cette application ?**
`ProfileViewModel` détient la liste complète des projets, centralise l'état dans `ProfileUiState` et applique les règles de filtrage/favoris lors des actions utilisateur en produisant de nouvelles copies immuables via `.update` et `.copy(...)`.

## 6. StateFlow
**Comment l'interface est-elle informée lorsqu'un état change ?**
`ProfileViewModel` modifie `_uiState` (`MutableStateFlow`) et expose `uiState` (`StateFlow`). `ProfileContainer` observe ce flux avec `collectAsState()`, ce qui déclenche automatiquement la recomposition de `ProfileScreen` à chaque mise à jour.

## 7. Organisation du code
* `DeveloperProfileModels.kt` : modèles `ProjectUi`, `DeveloperProfileUi` et données locales.
* `ProfileUiState` : représentation immuable de l'état de l'écran.
* `ProfileViewModel` : gestion de l'état, de la recherche et des favoris via `StateFlow`.
* `ProfileContainer` : collecte le `StateFlow` et relie le `ViewModel` à l'interface.
* `ProfileScreen` : écran principal stateless et ses `@Preview` multi-états.
* `ProjectCard` : carte affichant un projet et son bouton favori.

## 8. Capture d'écran
<img width="446" height="632" alt="image" src="https://github.com/user-attachments/assets/46f3423a-4a04-4b39-8739-5ad6c2b053f5" /><img width="427" height="676" alt="image" src="https://github.com/user-attachments/assets/31f67f4f-42de-479a-aed3-a0b1c5dcf9a4" />


