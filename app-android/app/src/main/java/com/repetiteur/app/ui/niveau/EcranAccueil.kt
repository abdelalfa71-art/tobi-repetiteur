package com.repetiteur.app.ui.niveau

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.repetiteur.app.data.model.Matiere
import com.repetiteur.app.data.model.Niveau
import com.repetiteur.app.ui.mascotte.EtatMascotte
import com.repetiteur.app.ui.mascotte.Mascotte

@Composable
fun EcranAccueil(
    prenomEnfant: String,
    niveau: Niveau,
    matieres: List<Matiere>,
    onMatiereChoisie: (Matiere) -> Unit,
    onCapturerCahier: () -> Unit,
    onParametres: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(16.dp))

        Mascotte(etat = EtatMascotte.EXPLAINING)

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Salut $prenomEnfant ! On révise quoi aujourd'hui ?",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Text(
            text = "Niveau ${niveau.nom}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(24.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(matieres) { matiere ->
                CarteMatiere(matiere = matiere, onClick = { onMatiereChoisie(matiere) })
            }
        }

        Spacer(Modifier.height(20.dp))

        OutlinedButton(onClick = onCapturerCahier, modifier = Modifier.fillMaxWidth()) {
            Text("📸 Montrer mon cahier à Tobi")
        }

        TextButton(onClick = onParametres) { Text("⚙️ Paramètres (clavier ou ardoise)") }
    }
}

@Composable
private fun CarteMatiere(matiere: Matiere, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.aspectRatio(1.3f)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = matiere.nom,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}
