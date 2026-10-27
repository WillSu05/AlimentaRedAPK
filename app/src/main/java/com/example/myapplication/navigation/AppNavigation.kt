package com.example.myapplication.navigation

import com.example.prestamo.ui.screens.HomeScreen
import com.example.prestamo.ui.screens.RegisterScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.screen.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(
                onLoginClick = { nombreUsuario ->
                    navController.navigate("home/$nombreUsuario") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onRegisterClick = { navController.popBackStack() },
                onBackToLoginClick = { navController.popBackStack() }
            )
        }

        composable(
            route = "home/{userName}",
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: "Usuario"

            HomeScreen(
                userName = userName,
                onPublicarLoteClick = { navController.navigate("publicar_lote") },
                onSolicitarLoteClick = { navController.navigate("solicitar_lote") },
                onMisSolicitudesClick = { navController.navigate("mis_solicitudes") },
                onNotificacionesClick = { navController.navigate("notificaciones") },
                onPerfilClick = { navController.navigate("perfil/$userName") }
            )
        }

        composable("publicar_lote") {
            PublicarLoteScreen(
                onBackClick = { navController.popBackStack() },
                onSubmitClick = { navController.popBackStack() }
            )
        }

        composable("mis_solicitudes") {
            MisSolicitudesScreen(
                onHomeClick = { navController.popBackStack() }
            )
        }

        composable("solicitar_lote") {
            SolicitarLoteScreen(
                onHomeClick = { navController.popBackStack() }
            )
        }

        composable("notificaciones") {
            NotificacionesScreen(
                onHomeClick = { navController.popBackStack() }
            )
        }

        composable(
            route = "perfil/{userName}",
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: "Usuario"
            PerfilScreen(
                userName = userName,
                onBackClick = { navController.popBackStack() },
                onLogoutClick = {
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}