package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = bio,
            style = MaterialTheme.typography.bodyMedium
        )
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
    // Remplacement de la Column statique par une LazyColumn pour supporter tout l'écran
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ProfileHeader(
                name = profile.name,
                role = profile.role,
                bio = profile.bio
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