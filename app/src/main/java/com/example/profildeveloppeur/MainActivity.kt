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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
    // Conteneur visuel principal de l'en-tête
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        tonalElevation = 3.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Alignement horizontal de l'avatar et des informations principales
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Box permettant de superposer le badge de statut sur l'avatar
                Box(
                    modifier = Modifier.size(76.dp)
                ) {
                    Image(
                        painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
                        contentDescription = "Avatar de $name",
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .padding(8.dp)
                    )
                    // Badge superposé en bas à droite de l'image
                    Surface(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        shape = MaterialTheme.shapes.small,
                        color = if (isAvailable) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.error
                        },
                        contentColor = if (isAvailable) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.onError
                        }
                    ) {
                        Text(
                            text = if (isAvailable) "Dispo" else "Occupé",
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = role,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = bio,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun SkillBadge(
    skillName: String,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        Text(
            text = "• $skillName",
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun ProfileScreen(
    profile: DeveloperProfileUi,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
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
            Button(
                onClick = onContactClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Me contacter")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenAvailablePreview() {
    MaterialTheme {
        ProfileScreen(
            profile = sampleDeveloperProfile(),
            onContactClick = {}
        )
    }
}