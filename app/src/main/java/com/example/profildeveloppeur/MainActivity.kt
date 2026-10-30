package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// COMPOSABLES

/**
 * en tete du profil
 */
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
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = role,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = bio
        )
    }
}

/**
 * skill badge
 */
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

/**
 * Bouton d action
 */
@Composable
fun ActionButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clickable(
                role = Role.Button,
                onClick = onClick
            )
            .semantics {
                contentDescription = "Action : $label"
            }
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        )
    }
}

/**
 * Assemblage des composants
 */
@Composable
fun ProfileCard(
    name: String,
    role: String,
    bio: String,
    skills: List<String>,
    isAvailable: Boolean,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp) // Padding INTERNE de la fiche
        ) {
            // En-tête du profil
            ProfileHeader(
                name = name,
                role = role,
                bio = bio,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Disponibilité
            Text(
                text = if (isAvailable) "Statut : Disponible pour de nouveaux projets" else "Statut : Indisponible actuellement",
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Liste des compétences
            Text(
                text = "Compétences clés :",
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                skills.forEach { skill ->
                    SkillBadge(
                        skillName = skill,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }

            // Bouton d'action
            ActionButton(
                label = "Me contacter",
                onClick = onContactClick,
                modifier = Modifier.fillMaxWidth() // Chaînage de Modifier
            )
        }
    }
}

/**
 * Ecran principal
 */
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    ProfileCard(
        name = "Nicolas",
        role = "Développeur Mobile & Embarqué",
        bio = "Passionné par l'architecture logicielle, Kotlin et Jetpack Compose.",
        skills = listOf("Kotlin", "Jetpack Compose", "Docker", "Python"),
        isAvailable = true,
        onContactClick = {
            // Callback d'événement sans logique applicative avancée
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp) // Padding EXTERNE appliqué par le parent
    )
}

// PREVIEWS

/**
 * Profil
 */
@Preview(showBackground = true)
@Composable
fun ProfileScreenAvailablePreview() {
    MaterialTheme {
        ProfileCard(
            name = "Nicolas Veysset",
            role = "Développeur Android Junior",
            bio = "Spécialisé en Python.",
            skills = listOf("Java", "Python", "Git"),
            isAvailable = true,
            onContactClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

/**
 * profil indisponible
 */
@Preview(showBackground = true)
@Composable
fun ProfileScreenUnavailablePreview() {
    MaterialTheme {
        ProfileCard(
            name = "Alexandre Dupont",
            role = "Développeur Full-Stack",
            bio = "En poste actuellement. Recherche de nouvelles opportunités.",
            skills = listOf("React", "Node.js", "PostgreSQL", "Docker"),
            isAvailable = false,
            onContactClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}