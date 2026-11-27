package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.profildeveloppeur.model.sampleDeveloperProfile
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme
import com.example.profildeveloppeur.ux.profile.ProfileContainer
import com.example.profildeveloppeur.ux.profile.ProfileScreen
import com.example.profildeveloppeur.ux.profile.ProfileUiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDeveloppeurTheme {
                // Point d'entrée unique vers le Container relié au ViewModel
                ProfileContainer(
                    onContactClick = {},
                    onProjectClick = {}
                )
            }
        }
    }
}

// Preview globale directement accessible depuis MainActivity (utilise ProfileScreen stateless)
@Preview(
    showBackground = true,
    heightDp = 1280,
    name = "MainActivity - Aperçu Profil"
)
@Composable
fun MainActivityProfilePreview() {
    val profile = sampleDeveloperProfile()
    ProfilDeveloppeurTheme {
        ProfileScreen(
            uiState = ProfileUiState(
                name = profile.name,
                role = profile.role,
                bio = profile.bio,
                email = profile.email,
                location = profile.location,
                isAvailable = profile.isAvailable,
                skills = profile.skills,
                projects = profile.projects
            ),
            onSearchQueryChange = {},
            onToggleFavoriteFilter = {},
            onFavoriteClick = {},
            onResetFilters = {},
            onContactClick = {},
            onProjectClick = {}
        )
    }
}