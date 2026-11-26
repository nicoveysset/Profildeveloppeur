# Profil Développeur - Jetpack Compose

## 1. Description
Application Android développée avec Jetpack Compose présentant le profil d'un développeur (identité, disponibilité, compétences, projets et contact) à partir de données locales en Kotlin.

## 2. Composants Compose utilisés
- Text : titres, descriptions, badges et notes.
- Button : actions « Voir le projet » et « Me contacter ».
- Icon : icônes pour les notes (Star), l'email (Email) et la localisation (LocationOn).
- Image : avatar circulaire du développeur (CircleShape).
- Card : cartes uniformes (ProjectCard) pour chaque projet.
- OutlinedTextField : champs en lecture seule (readOnly = true) pour l'email et la localisation.

## 3. Layouts utilisés
- Column : structuration verticale des cartes et sections.
- Row : alignement horizontal de l'avatar avec l'identité, et du titre d'un projet avec sa note.
- Box : superposition de l'avatar et de la pastille de disponibilité (Alignment.BottomEnd).
- Spacer : espacements réguliers entre les éléments.
- Surface : conteneurs visuels cohérents avec le thème (en-tête, badges, contact).

## 4. Listes modernes (LazyColumn et LazyRow)
- LazyColumn : conteneur principal de l'écran regroupant les sections fixes (item) et la liste des projets (items avec clé stable key = project.id) affichés via ProjectCard.
- LazyRow : défilement horizontal des compétences techniques affichées avec le composable SkillBadge.

## 5. Choix de thème (Material Design 3)
- Couleurs (ColorScheme) : palettes LightColorScheme et DarkColorScheme basées sur un bleu professionnel (primary) avec un contraste adapté entre le fond (background) et les cartes (surface).
- Typographie (Typography) : styles personnalisés (ProfileTypography) pour hiérarchiser titres, sous-titres et descriptions.
- Formes (Shapes) : arrondis harmonisés (ProfileShapes de 8.dp à 32.dp) appliqués aux cartes, surfaces et badges.

## 6. Capture d'écran
<img width="608" height="817" alt="image" src="https://github.com/user-attachments/assets/fc7facba-34af-4464-be35-4b0dfef46050" />


## 7. Notions du chapitre Compose UI réutilisées
J'ai réutilisé le découpage en composables réutilisables avec paramètres et callbacks, les layouts de base (Column, Row, Box, Surface, Spacer), les listes optimisées LazyColumn et LazyRow, ainsi que la personnalisation Material Design 3 (ColorScheme, Typography, Shapes) avec support des modes clair et sombre via des previews dédiées.
