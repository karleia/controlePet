package com.example.controlepet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.controlepet.base.Navigation
import com.example.controlepet.ui.theme.ControlePetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ControlePetTheme {
                Navigation().Create();
            }
        }
    }
}

/*
@Composable
fun MainApp() {
    val navController = rememberNavController()

    val context = LocalContext.current
    val app = context.applicationContext as Application
    val db = AppDatabase.getDatabase(app)
    val factory = AppViewModelFactory(OfflineUserRepository(db.UserDAO()))

    NavGraph(navController = navController, factory = factory)
}
*/
