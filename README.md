# Profil Développeur Compose

## Description
Application Android développée avec Jetpack Compose affichant la fiche de profil d'un développeur. L'interface présente l'en-tête du profil, le statut de disponibilité, une liste de compétences ainsi qu'une zone d'action interactive.

## Notions utilisées
- Fonctions Composables
- Passages de paramètres
- Modifiers et chaînage de Modifiers
- Distinction entre padding externe et padding interne
- Callbacks d'événements
- Previews d'interface avec données variables
- Accessibilité de base

## Questions

1. - `ProfileHeader` (nom, rôle, description)
   - `SkillBadge` (badge d'une compétence)
   - `ActionButton` (bouton d'action avec accessibilité)
   - `ProfileCard` (conteneur assemblant le profil)
   - `ProfileScreen` (écran principal)

2.  - Le composable `ProfileScreen`.

3.  - Dans `ProfileScreen`

4.  - Dans `ProfileCard`et dans les sous-composants comme `SkillBadge` et `ActionButton`.

5.  - `ActionButton` reçoit le callback, transmis depuis `ProfileCard` puis `ProfileScreen`.

6.  - L'utilisation du modificateur `.semantics { contentDescription = "Action : $label" }`.

7.  - Ils reçoivent leurs données via des paramètres et acceptent un paramètre `modifier: Modifier = Modifier`.
  
<img width="807" height="481" alt="image" src="https://github.com/user-attachments/assets/d83144a1-71f6-4902-9c82-29863889edc5" />
