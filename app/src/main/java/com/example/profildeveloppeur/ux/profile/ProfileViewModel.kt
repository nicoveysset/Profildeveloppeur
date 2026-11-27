package com.example.profildeveloppeur.ux.profile

import androidx.lifecycle.ViewModel
import com.example.profildeveloppeur.model.DeveloperProfileUi
import com.example.profildeveloppeur.model.ProjectUi
import com.example.profildeveloppeur.model.sampleDeveloperProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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
}