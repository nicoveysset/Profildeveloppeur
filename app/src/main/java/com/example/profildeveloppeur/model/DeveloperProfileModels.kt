package com.example.profildeveloppeur.model

// Modèle représentant un projet ou une expérience du développeur
data class ProjectUi(
    val id: Int,
    val title: String,
    val techStack: String,
    val description: String,
    val rating: Double
)

// Modèle regroupant les informations générales et la liste des projets du profil
data class DeveloperProfileUi(
    val name: String,
    val role: String,
    val bio: String,
    val email: String,
    val location: String,
    val isAvailable: Boolean,
    val skills: List<String>,
    val projects: List<ProjectUi>
)

// Jeu de données local du profil développeur
fun sampleDeveloperProfile(): DeveloperProfileUi {
    return DeveloperProfileUi(
        name = "Nicolas Veysset",
        role = "Développeur Mobile & Embarqué",
        bio = "Passionné par l'architecture logicielle, Kotlin et Jetpack Compose.",
        email = "nicolas.veysset@exemple.fr",
        location = "Bordeaux, France",
        isAvailable = true,
        skills = listOf("Kotlin", "Jetpack Compose", "Docker", "Python", "Git"),
        projects = listOf(
            ProjectUi(
                id = 1,
                title = "Product Explorer",
                techStack = "Kotlin • Jetpack Compose • Material 3",
                description = "Application catalogue de produits locaux avec recherche dynamique, filtres et thème clair/sombre.",
                rating = 4.8
            ),
            ProjectUi(
                id = 2,
                title = "Planning Médical Mobile",
                techStack = "React Native • Expo • TypeScript",
                description = "Application mobile de gestion d'emplois du temps destinée aux praticiens de santé.",
                rating = 4.6
            ),
            ProjectUi(
                id = 3,
                title = "Banc de Test Automatisé",
                techStack = "Python • Docker • Robot Framework",
                description = "Infrastructure conteneurisée de validation logicielle pour systèmes embarqués.",
                rating = 4.9
            ),
            ProjectUi(
                id = 4,
                title = "Simulateur de Vol 3D",
                techStack = "C# • Unity • Physique",
                description = "Simulation interactive de vol d'oiseaux avec gestion de trajectoires en temps réel.",
                rating = 4.5
            )
        )
    )
}