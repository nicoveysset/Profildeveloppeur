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

// Preview 1 : État initial (recherche vide, tous les projets affichés, aucun favori, thème clair)
@Preview(
    showBackground = true,
    heightDp = 1280,
    name = "Preview 1 - État initial (Light)"
)
@Composable
fun MainActivityInitialPreview() {
    val profile = sampleDeveloperProfile()
    ProfilDeveloppeurTheme(darkTheme = false) {
        ProfileScreen(
            uiState = ProfileUiState(
                name = profile.name,
                role = profile.role,
                bio = profile.bio,
                email = profile.email,
                location = profile.location,
                isAvailable = profile.isAvailable,
                skills = profile.skills,
                projects = profile.projects,
                searchQuery = "",
                showOnlyFavorites = false,
                favoriteProjectIds = emptyList()
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

// Preview 2 : Recherche active + filtre favoris activé + plusieurs favoris (thème sombre)
@Preview(
    showBackground = true,
    heightDp = 1100,
    name = "Preview 2 - Recherche et Favoris (Dark)"
)
@Composable
fun MainActivityFilteredFavoritesPreview() {
    val profile = sampleDeveloperProfile()
    val favoriteIds = listOf(1, 3)
    ProfilDeveloppeurTheme(darkTheme = true) {
        ProfileScreen(
            uiState = ProfileUiState(
                name = profile.name,
                role = profile.role,
                bio = profile.bio,
                email = profile.email,
                location = profile.location,
                isAvailable = profile.isAvailable,
                skills = profile.skills,
                projects = profile.projects.filter { it.id in favoriteIds },
                searchQuery = "Kotlin",
                showOnlyFavorites = true,
                favoriteProjectIds = favoriteIds
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

// Preview 3 : Aucun résultat trouvé (recherche sans correspondance + bouton de réinitialisation)
@Preview(
    showBackground = true,
    heightDp = 900,
    name = "Preview 3 - Aucun résultat (Light)"
)
@Composable
fun MainActivityEmptyResultPreview() {
    val profile = sampleDeveloperProfile()
    ProfilDeveloppeurTheme(darkTheme = false) {
        ProfileScreen(
            uiState = ProfileUiState(
                name = profile.name,
                role = profile.role,
                bio = profile.bio,
                email = profile.email,
                location = profile.location,
                isAvailable = profile.isAvailable,
                skills = profile.skills,
                projects = emptyList(),
                searchQuery = "Swift",
                showOnlyFavorites = false,
                favoriteProjectIds = listOf(1)
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