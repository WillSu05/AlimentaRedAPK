package com.example.myapplication.navigation

import com.example.prestamo.ui.screens.HomeScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.AppViewModel
import com.example.myapplication.screen.*
import com.example.myapplication.R
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: AppViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {

        // 1. Login (Página 1)
        composable("login") {
            LoginScreen(
                onLoginClick = { nombreUsuario ->
                    viewModel.userName = nombreUsuario
                    navController.navigate("home/$nombreUsuario") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = { navController.navigate("register") }
            )
        }

        // 2. Registro (Página 2)
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { nombre ->
                    viewModel.userName = nombre
                    viewModel.agregarNotificacion("Usuario creado", "La creación de tu usuario ($nombre) se realizó con éxito.")
                    navController.navigate("home/$nombre") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onBackToLoginClick = { navController.popBackStack() }
            )
        }

        // 3. Home / Menú Principal (Página 3)
        composable(
            route = "home/{userName}",
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: viewModel.userName

            HomeScreen(
                userName = userName,
                onPublicarLoteClick = { navController.navigate("publicar_lote") },
                onSolicitarLoteClick = { navController.navigate("solicitar_lote") },
                onMisSolicitudesClick = { navController.navigate("mis_solicitudes") },
                onNotificacionesClick = { navController.navigate("notificaciones") },
                onPerfilClick = { navController.navigate("perfil/$userName") },
                onDetalleLoteClick = { navController.navigate("detalle_lote") }
            )
        }

        // 4. Publicar Lote (Página 4)
        composable("publicar_lote") {
            PublicarLoteScreen(
                onBackClick = { navController.popBackStack() },
                onSubmitClick = {
                    // Esto se ejecutará automáticamente cuando el ViewModel confirme el éxito en la API
                    navController.popBackStack()
                }
            )
        }

        // 5. Solicitar Lote (Página 5)
        composable("solicitar_lote") {
            SolicitarLoteScreen(
                onBackClick = { navController.popBackStack() },
                onDetalleClick = { navController.navigate("detalle_lote") }
            )
        }

        // 6. Detalle del Lote (Página 6)
        composable("detalle_lote") {
            DetalleLoteScreen(
                onBackClick = { navController.popBackStack() },
                onRequestSuccess = {
                    viewModel.agregarNotificacion("Solicitud aceptada", "Tu solicitud para el lote ha sido registrada.")
                    // Redirige a Registrar Entrega pasando la categoría y el icono interactivo
                    navController.navigate("registrar_entrega/Frutas y verduras/${R.drawable.logo_alimentared}")
                }
            )
        }

        // 7. Registrar Entrega (Página 7) - Dinámica
        composable(
            route = "registrar_entrega/{categoriaLote}/{iconRes}",
            arguments = listOf(
                navArgument("categoriaLote") { type = NavType.StringType },
                navArgument("iconRes") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val categoriaLote = backStackEntry.arguments?.getString("categoriaLote") ?: "Frutas y verduras"
            val iconRes = backStackEntry.arguments?.getInt("iconRes") ?: R.drawable.logo_alimentared

            RegistrarEntregaScreen(
                categoriaLote = categoriaLote,
                iconRes = iconRes,
                onBackClick = { navController.popBackStack() },
                onEntregaSuccess = {
                    viewModel.agregarNotificacion("Entrega registrada", "La entrega del lote ($categoriaLote) se ha registrado correctamente.")
                    navController.navigate("mis_solicitudes")
                }
            )
        }

        // 8. Mis Solicitudes (Página 8)
        composable("mis_solicitudes") {
            MisSolicitudesScreen(
                onHomeClick = { navController.popBackStack() }
            )
        }

        // 9. Notificaciones Interactivas (Página 9)
        composable("notificaciones") {
            NotificacionesScreen(
                notificaciones = viewModel.notificaciones,
                onHomeClick = { navController.popBackStack() }
            )
        }

        // 10. Perfil (Página 10)
        composable(
            route = "perfil/{userName}",
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: viewModel.userName
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