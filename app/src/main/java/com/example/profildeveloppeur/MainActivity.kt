package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileScreen(
                        profile = sampleDeveloperProfile(),
                        onContactClick = {},
                        onProjectClick = {},
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Modèle de données pour un projet ou une expérience
data class ProjectUi(
    val id: Int,
    val title: String,
    val techStack: String,
    val description: String,
    val rating: Double
)

// Modèle complet du profil développeur
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

// Jeu de données local basé sur ton profil initial
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
                description = "Application catalogue de produits locaux avec listes modernes et thème clair/sombre.",
                rating = 4.8
            ),
            ProjectUi(
                id = 2,
                title = "Planning Médical Mobile",
                techStack = "React Native • Expo • TypeScript",
                description = "Application mobile de gestion d'emplois du temps destinée aux praticiens.",
                rating = 4.6
            ),
            ProjectUi(
                id = 3,
                title = "Banc de Test Automatisé",
                techStack = "Python • Docker • Robot Framework",
                description = "Infrastructure conteneurisée de validation logicielle pour systèmes embarqués.",
                rating = 4.9
            )
        )
    )
}

@Composable
fun ProfileHeader(
    name: String,
    role: String,
    bio: String,
    isAvailable: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 3.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Box contenant l'avatar circulaire et un indicateur d'état superposé
                Box(
                    modifier = Modifier.size(80.dp)
                ) {
                    Image(
                        painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
                        contentDescription = "Avatar de $name",
                        modifier = Modifier
                            .size(76.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .padding(12.dp)
                    )
                    // Pastille de statut superposée sans masquer l'icône centrale
                    Surface(
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.BottomEnd),
                        shape = CircleShape,
                        color = if (isAvailable) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.error
                        },
                        tonalElevation = 4.dp
                    ) {}
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = role,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    // Badge textuel de disponibilité lisible
                    Surface(
                        shape = MaterialTheme.shapes.small,
                        color = if (isAvailable) {
                            MaterialTheme.colorScheme.primaryContainer
                        } else {
                            MaterialTheme.colorScheme.errorContainer
                        },
                        contentColor = if (isAvailable) {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        } else {
                            MaterialTheme.colorScheme.onErrorContainer
                        }
                    ) {
                        Text(
                            text = if (isAvailable) "Disponible" else "Indisponible",
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = bio,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun SkillBadge(
    skillName: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary
    ) {
        Text(
            text = skillName,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun SkillsSection(
    skills: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Compétences techniques",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(skills) { skill ->
                SkillBadge(skillName = skill)
            }
        }
    }
}

@Composable
fun ProjectCard(
    project: ProjectUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = project.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Note du projet",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${project.rating}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = project.techStack,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = project.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = onClick) {
                Text(text = "Voir le projet")
            }
        }
    }
}

@Composable
fun ContactSection(
    email: String,
    location: String,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Contact & Informations",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Icône Email"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = location,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Localisation") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Icône Localisation"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onContactClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Me contacter")
            }
        }
    }
}

@Composable
fun ProfileScreen(
    profile: DeveloperProfileUi,
    onContactClick: () -> Unit,
    onProjectClick: (ProjectUi) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ProfileHeader(
                name = profile.name,
                role = profile.role,
                bio = profile.bio,
                isAvailable = profile.isAvailable
            )
        }
        item {
            SkillsSection(skills = profile.skills)
        }
        item {
            Text(
                text = "Projets réalisés",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        items(
            items = profile.projects,
            key = { project -> project.id }
        ) { project ->
            ProjectCard(
                project = project,
                onClick = { onProjectClick(project) }
            )
        }
        item {
            ContactSection(
                email = profile.email,
                location = profile.location,
                onContactClick = onContactClick
            )
        }
    }
}

@Preview(
    showBackground = true,
    heightDp = 960,
    name = "Profil Light Theme"
)
@Composable
fun ProfileScreenLightPreview() {
    MaterialTheme {
        ProfileScreen(
            profile = sampleDeveloperProfile(),
            onContactClick = {},
            onProjectClick = {}
        )
    }
}