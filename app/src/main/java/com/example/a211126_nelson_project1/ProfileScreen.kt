package com.example.a211126_nelson_project1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.a211126_nelson_project1.ui.theme.BebasNeue
import com.example.a211126_nelson_project1.ui.theme.BricolageGrotesque

@Composable
fun ProfileScreen(viewModel: EcoBuyViewModel) {

    val userName = viewModel.userName.value
    val isVerified = viewModel.isVerified.value
    val isLoggedIn = viewModel.isLoggedIn.value
    val imageRes = viewModel.profileImage.value ?: R.drawable.hariz

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        if (!isLoggedIn) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Welcome to EcoBuy",
                    style = MaterialTheme.typography.headlineMedium,
                    fontFamily = BebasNeue
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = userName,
                    onValueChange = { viewModel.updateName(it) },
                    label = { Text("Enter your name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = viewModel.email.value,
                    onValueChange = { viewModel.updateEmail(it) },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = viewModel.phone.value,
                    onValueChange = { viewModel.updatePhone(it) },
                    label = { Text("Phone Number") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = viewModel.password.value,
                    onValueChange = { viewModel.updatePassword(it) },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { viewModel.login() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login / Sign Up")
                }
            }

        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(40.dp))

                Card(
                    shape = RoundedCornerShape(100.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(100.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = if (userName.isBlank()) "Guest" else userName,
                    style = MaterialTheme.typography.headlineSmall,
                    fontFamily = BricolageGrotesque
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Email: ${viewModel.email.value}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Phone: ${viewModel.phone.value}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (isVerified) "Verified User ✅" else "Not Verified ❌",
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = { viewModel.verifyUser() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Verify Account")
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = { viewModel.logout() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Logout")
                }
            }
        }
    }
}