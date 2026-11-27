package com.example.profildeveloppeur.ux.profile

import androidx.lifecycle.ViewModel
import com.example.profildeveloppeur.model.DeveloperProfileUi
import com.example.profildeveloppeur.model.ProjectUi
import com.example.profildeveloppeur.model.sampleDeveloperProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// État immuable complet de l'écran Profil développeur observé par l'interface
data class ProfileUiState(
    val name: String = "",
    val role: String = "",
    val bio: String = "",
    val email: String = "",
    val location: String = "",
    val isAvailable: Boolean = true,
    val skills: List<String> = emptyList(),
    val projects: List<ProjectUi> = emptyList(),
    val searchQuery: String = "",
    val showOnlyFavorites: Boolean = false,
    val favoriteProjectIds: List<Int> = emptyList()
)

class ProfileViewModel : ViewModel() {

    // Données sources complètes encapsulées en privé dans le ViewModel
    private val initialProfile: DeveloperProfileUi = sampleDeveloperProfile()
    private val allProjects: List<ProjectUi> = initialProfile.projects

    // Flux d'état interne modifiable uniquement par le ViewModel
    private val _uiState = MutableStateFlow(
        ProfileUiState(
            name = initialProfile.name,
            role = initialProfile.role,
            bio = initialProfile.bio,
            email = initialProfile.email,
            location = initialProfile.location,
            isAvailable = initialProfile.isAvailable,
            skills = initialProfile.skills,
            projects = allProjects
        )
    )

    // Flux d'état public en lecture seule observé par Compose via collectAsState()
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    // Fonction pure calculant la liste des projets visibles selon la recherche et les favoris
    private fun filterProjects(
        searchQuery: String,
        showOnlyFavorites: Boolean,
        favoriteProjectIds: List<Int>
    ): List<ProjectUi> {
        return allProjects.filter { project ->
            val matchesSearch =
                project.title.contains(searchQuery, ignoreCase = true) ||
                        project.techStack.contains(searchQuery, ignoreCase = true) ||
                        project.description.contains(searchQuery, ignoreCase = true)

            val matchesFavorites =
                !showOnlyFavorites || favoriteProjectIds.contains(project.id)

            matchesSearch && matchesFavorites
        }
    }

    // Met à jour la saisie de recherche et recalcule la liste des projets affichés
    fun onSearchQueryChange(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = newValue,
                projects = filterProjects(
                    searchQuery = newValue,
                    showOnlyFavorites = currentState.showOnlyFavorites,
                    favoriteProjectIds = currentState.favoriteProjectIds
                )
            )
        }
    }

    // Active ou désactive le filtre affichant uniquement les projets favoris
    fun onToggleFavoriteFilter() {
        _uiState.update { currentState ->
            val newShowOnlyFavorites = !currentState.showOnlyFavorites
            currentState.copy(
                showOnlyFavorites = newShowOnlyFavorites,
                projects = filterProjects(
                    searchQuery = currentState.searchQuery,
                    showOnlyFavorites = newShowOnlyFavorites,
                    favoriteProjectIds = currentState.favoriteProjectIds
                )
            )
        }
    }

    // Ajoute ou retire un projet des favoris et met à jour la liste si le filtre est actif
    fun onFavoriteClick(projectId: Int) {
        _uiState.update { currentState ->
            val newFavoriteIds = if (currentState.favoriteProductIdsContains(projectId)) {
                currentState.favoriteProjectIds - projectId
            } else {
                currentState.favoriteProjectIds + projectId
            }
            currentState.copy(
                favoriteProjectIds = newFavoriteIds,
                projects = filterProjects(
                    searchQuery = currentState.searchQuery,
                    showOnlyFavorites = currentState.showOnlyFavorites,
                    favoriteProjectIds = newFavoriteIds
                )
            )
        }
    }

    // Réinitialise la recherche et le filtre favoris
    fun onResetFilters() {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = "",
                showOnlyFavorites = false,
                projects = filterProjects(
                    searchQuery = "",
                    showOnlyFavorites = false,
                    favoriteProjectIds = currentState.favoriteProjectIds
                )
            )
        }
    }

    // Fonction utilitaire privée vérifiant si un projet est déjà en favori
    private fun ProfileUiState.favoriteProductIdsContains(projectId: Int): Boolean {
        return favoriteProjectIds.contains(projectId)
    }
}