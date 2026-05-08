package com.example.a211126_nelson_project1

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.navigation.NavController
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.a211126_nelson_project1.ui.theme.BebasNeue
import com.example.a211126_nelson_project1.ui.theme.BricolageGrotesque

class EcoBuyScreen {
}

@Composable
fun EcoBuyApp(viewModel: EcoBuyViewModel){
    val userName = viewModel.userName.value
    val isVerified = viewModel.isVerified.value
    var searchText by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.ecobuymodel),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = if (isVerified) "$userName's EcoBuy" else "EcoBuy",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                fontFamily = BebasNeue
            )

            Spacer(modifier = Modifier.height(16.dp))

            SearchSection(
                searchText,
                { searchText = it },
                userName,
                { viewModel.updateName(it) },
                isVerified,
                { viewModel.verifyUser() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProductList(searchText, viewModel)
        }
    }
}

@Composable
fun SearchSection(
    searchText: String,
    onSearchChange: (String) -> Unit,
    userName: String,
    onNameChange: (String) -> Unit,
    isVerified: Boolean,
    onVerify: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {
            TextField(
                value = searchText,
                onValueChange = onSearchChange,
                label = { Text("Search Product") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
            )

        }
    }
}

data class EcoProduct(val name: String, val category: String, val imageRes: Int)

val ecoProducts = listOf(
    EcoProduct("Bamboo Toothbrush", "Personal Care", R.drawable.bamboo),
    EcoProduct("Reusable Straw Set", "Kitchen", R.drawable.reusable_straw_set),
    EcoProduct("Eco Tote Bag", "Lifestyle", R.drawable.eco_bags),
    EcoProduct("Organic Soap", "Personal Care", R.drawable.organic_soap),
    EcoProduct("Glass Water Bottle", "Lifestyle", R.drawable.bottle),
    EcoProduct("Bamboo Toothbrush", "Personal Care", R.drawable.bamboo),
    EcoProduct("Reusable Straw Set", "Kitchen", R.drawable.reusable_straw_set),
    EcoProduct("Eco Tote Bag", "Lifestyle", R.drawable.eco_bags),
    EcoProduct("Organic Soap", "Personal Care", R.drawable.organic_soap),
    EcoProduct("Glass Water Bottle", "Lifestyle", R.drawable.bottle)
)

@Composable
fun ProductItem(product: EcoProduct, viewModel: EcoBuyViewModel) {

    var expanded by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),

        shape = RoundedCornerShape(
            topStart = 15.dp,
            topEnd = 50.dp,
            bottomStart = 50.dp,
            bottomEnd = 15.dp
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {

                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontFamily = BricolageGrotesque,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = product.category,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Row {

                    IconButton(
                        onClick = { viewModel.toggleFavorite(product) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = if (viewModel.favoriteProducts.contains(product))
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurface
                        )
                    }

                    IconButton(
                        onClick = { expanded = !expanded }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ExpandMore,
                            contentDescription = "Expand",
                            modifier = Modifier.rotate(rotation)
                        )
                    }
                }
            }

            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Eco-friendly product 🌱",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
fun ProductList(searchText: String, viewModel: EcoBuyViewModel) {

    val filteredList = if (searchText.isBlank()) {
        ecoProducts
    } else {
        ecoProducts.filter {
            it.name.contains(searchText, ignoreCase = true)
        }
    }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(filteredList) {
            ProductItem(it, viewModel)
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        BottomItem(Icons.Default.Home) {
            navController.navigate("product")
        }

        BottomItem(Icons.Default.Favorite) {
            navController.navigate("favorite")
        }

        BottomItem(Icons.Default.QrCodeScanner) {
            navController.navigate("scanner")
        }

        BottomItem(Icons.Default.Notifications) {
            navController.navigate("notification")
        }

        BottomItem(Icons.Default.Person) {
            navController.navigate("profile")
        }
    }
}

@Composable
fun BottomItem(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier.clickable { onClick() },
        tint = MaterialTheme.colorScheme.onPrimary
    )
}


