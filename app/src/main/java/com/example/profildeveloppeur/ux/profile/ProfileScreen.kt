package com.example.profildeveloppeur.ux.profile

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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildeveloppeur.model.ProjectUi
import com.example.profildeveloppeur.model.sampleDeveloperProfile
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

// En-tête du profil développeur (stateless)
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

// Badge individuel de compétence (stateless)
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

// Liste horizontale des compétences techniques (stateless)
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

// Carte d'un projet avec bouton favori interactif (stateless)
@Composable
fun ProjectCard(
    project: ProjectUi,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
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
                Spacer(modifier = Modifier.width(4.dp))
                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector = if (isFavorite) {
                            Icons.Default.Favorite
                        } else {
                            Icons.Outlined.FavoriteBorder
                        },
                        contentDescription = if (isFavorite) {
                            "Retirer ${project.title} des favoris"
                        } else {
                            "Ajouter ${project.title} aux favoris"
                        },
                        tint = if (isFavorite) {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }
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

// Section contact conservant un état local UI avec rememberSaveable (afficher/masquer les coordonnées)
@Composable
fun ContactSection(
    email: String,
    location: String,
    isAvailable: Boolean,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // État local d'interface conservé à la rotation d'écran
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Contact & Informations",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
                TextButton(onClick = { isExpanded = !isExpanded }) {
                    Text(text = if (isExpanded) "Masquer" else "Afficher")
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = if (isExpanded) {
                            Icons.Default.KeyboardArrowUp
                        } else {
                            Icons.Default.KeyboardArrowDown
                        },
                        contentDescription = if (isExpanded) {
                            "Masquer les informations de contact"
                        } else {
                            "Afficher les informations de contact"
                        }
                    )
                }
            }

            if (isExpanded) {
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

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (isAvailable) {
                        "Statut : Disponible pour une opportunité"
                    } else {
                        "Statut : Actuellement en poste"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
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
}

// Écran principal 100 % stateless : reçoit l'état immuable et remonte tous les événements
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onSearchQueryChange: (String) -> Unit,
    onToggleFavoriteFilter: () -> Unit,
    onFavoriteClick: (Int) -> Unit,
    onResetFilters: () -> Unit,
    onContactClick: () -> Unit,
    onProjectClick: (ProjectUi) -> Unit,
    modifier: Modifier = Modifier
) {
    val projectCountText = if (uiState.projects.size <= 1) {
        "${uiState.projects.size} projet affiché"
    } else {
        "${uiState.projects.size} projets affichés"
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Profil Développeur") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ProfileHeader(
                    name = uiState.name,
                    role = uiState.role,
                    bio = uiState.bio,
                    isAvailable = uiState.isAvailable
                )
            }

            item {
                SkillsSection(skills = uiState.skills)
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Projets réalisés",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    OutlinedTextField(
                        value = uiState.searchQuery,
                        onValueChange = onSearchQueryChange,
                        singleLine = true,
                        label = { Text(text = "Rechercher un projet ou une techno") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Rechercher"
                            )
                        },
                        trailingIcon = {
                            if (uiState.searchQuery.isNotEmpty()) {
                                IconButton(onClick = { onSearchQueryChange("") }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Effacer la recherche"
                                    )
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        FilterChip(
                            selected = uiState.showOnlyFavorites,
                            onClick = onToggleFavoriteFilter,
                            label = { Text(text = "Favoris uniquement") },
                            leadingIcon = {
                                Icon(
                                    imageVector = if (uiState.showOnlyFavorites) {
                                        Icons.Default.Favorite
                                    } else {
                                        Icons.Outlined.FavoriteBorder
                                    },
                                    contentDescription = null
                                )
                            }
                        )

                        Text(
                            text = projectCountText,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            if (uiState.projects.isEmpty()) {
                item {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.large,
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(24.dp)
                        ) {
                            Text(
                                text = "Aucun projet ne correspond à votre recherche.",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            OutlinedButton(onClick = onResetFilters) {
                                Text(text = "Réinitialiser les filtres")
                            }
                        }
                    }
                }
            } else {
                items(
                    items = uiState.projects,
                    key = { project -> project.id }
                ) { project ->
                    ProjectCard(
                        project = project,
                        isFavorite = uiState.favoriteProjectIds.contains(project.id),
                        onFavoriteClick = { onFavoriteClick(project.id) },
                        onClick = { onProjectClick(project) }
                    )
                }
            }

            item {
                ContactSection(
                    email = uiState.email,
                    location = uiState.location,
                    isAvailable = uiState.isAvailable,
                    onContactClick = onContactClick
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
fun ProfileScreenInitialPreview() {
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
fun ProfileScreenFilteredFavoritesPreview() {
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