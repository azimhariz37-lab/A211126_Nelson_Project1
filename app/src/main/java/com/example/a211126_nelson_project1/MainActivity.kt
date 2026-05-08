package com.example.a211126_nelson_project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a211126_nelson_project1.ui.theme.A211126_Nelson_Project1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            A211126_Nelson_Project1Theme {

                val navController = rememberNavController()
                val viewModel: EcoBuyViewModel = viewModel()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavBar(navController) }
                ) { padding ->

                    NavHost(
                        navController = navController,
                        startDestination = "product",
                        modifier = Modifier.padding(padding)
                    ) {

                        composable("product") {
                            EcoBuyApp(viewModel)
                        }

                        composable("favorite") {
                            favouriteScreen(viewModel)
                        }

                        composable("scanner") {
                            ScannerScreen(navController)
                        }

                        composable("notification") {
                            NotiScreen()
                        }

                        composable("profile") {
                            ProfileScreen(viewModel)
                        }

                        composable("product_detail") {
                            ProductDetailScreen(
                                EcoProduct("Bamboo Toothbrush", "Personal Care", R.drawable.bamboo)
                            )
                        }
                    }
                }
            }
        }
    }
}
