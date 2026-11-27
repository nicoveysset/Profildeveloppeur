package com.example.profildeveloppeur.ux.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.profildeveloppeur.model.ProjectUi

// Passerelle observant le StateFlow du ViewModel et alimentant l'écran stateless ProfileScreen
@Composable
fun ProfileContainer(
    onContactClick: () -> Unit,
    onProjectClick: (ProjectUi) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel()
) {
    // Collecte réactive de l'état complet exposé par le ViewModel
    val uiState by viewModel.uiState.collectAsState()

    ProfileScreen(
        uiState = uiState,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onToggleFavoriteFilter = viewModel::onToggleFavoriteFilter,
        onFavoriteClick = viewModel::onFavoriteClick,
        onResetFilters = viewModel::onResetFilters,
        onContactClick = onContactClick,
        onProjectClick = onProjectClick,
        modifier = modifier
    )
}