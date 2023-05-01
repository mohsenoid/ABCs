package com.mohsenoid.abcs

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohsenoid.abcs.alphabets.AlphabetsScreen
import com.mohsenoid.abcs.alphabets.AlphabetsViewModel
import com.mohsenoid.abcs.colors.ColorsScreen
import com.mohsenoid.abcs.colors.ColorsViewModel
import com.mohsenoid.abcs.dial.DialScreen
import com.mohsenoid.abcs.dial.DialViewModel
import com.mohsenoid.abcs.numbers.NumbersScreen
import com.mohsenoid.abcs.numbers.NumbersViewModel
import com.mohsenoid.abcs.selector.SelectorScreen
import com.mohsenoid.abcs.selector.model.SelectorItem
import com.mohsenoid.abcs.shapes.ShapesScreen
import com.mohsenoid.abcs.shapes.ShapesViewModel
import com.mohsenoid.abcs.theme.ABCsTheme
import com.mohsenoid.abcs.util.NavRoute
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ABCsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = NavRoute.SelectorScreen.route,
                    ) {
                        composable(
                            route = NavRoute.SelectorScreen.route
                        ) {
                            SelectorScreen(
                                modifier = Modifier.fillMaxSize(),
                                selectorItems = listOf(
                                    SelectorItem(
                                        text = "ABCs",
                                        icon = R.drawable.ic_abc,
                                        onClick = { navController.navigate(NavRoute.AlphabetsScreen.route) }
                                    ),
                                    SelectorItem(
                                        text = "123s",
                                        icon = R.drawable.ic_123,
                                        onClick = { navController.navigate(NavRoute.NumbersScreen.route) }
                                    ),
                                    SelectorItem(
                                        text = "Shapes",
                                        icon = R.drawable.ic_shape,
                                        onClick = { navController.navigate(NavRoute.ShapesScreen.route) }
                                    ),
                                    SelectorItem(
                                        text = "Colors",
                                        icon = R.drawable.ic_color,
                                        onClick = { navController.navigate(NavRoute.ColorsScreen.route) }
                                    ),
                                    SelectorItem(
                                        text = "Dial",
                                        icon = R.drawable.ic_dial,
                                        onClick = { navController.navigate(NavRoute.DialScreen.route) }
                                    ),
                                )
                            )
                        }

                        composable(
                            route = NavRoute.AlphabetsScreen.route
                        ) {
                            val viewModel = koinViewModel<AlphabetsViewModel>()
                            val state by viewModel.uiState.collectAsStateWithLifecycle()

                            AlphabetsScreen(
                                state = state,
                                modifier = Modifier.fillMaxSize(),
                                onModeClicked = viewModel::onModeClicked,
                                onClicked = viewModel::onClicked,
                                onBackClicked = navController::navigateUp
                            )
                        }

                        composable(
                            route = NavRoute.NumbersScreen.route
                        ) {
                            val viewModel = koinViewModel<NumbersViewModel>()
                            val state by viewModel.uiState.collectAsStateWithLifecycle()

                            NumbersScreen(
                                state = state,
                                modifier = Modifier.fillMaxSize(),
                                onMaxClicked = viewModel::onMaxSelectorClicked,
                                onClicked = viewModel::onClicked,
                                onBackClicked = navController::navigateUp
                            )
                        }

                        composable(
                            route = NavRoute.ShapesScreen.route
                        ) {
                            val viewModel = koinViewModel<ShapesViewModel>()
                            val state by viewModel.uiState.collectAsStateWithLifecycle()

                            ShapesScreen(
                                state = state,
                                modifier = Modifier.fillMaxSize(),
                                onClicked = viewModel::onClicked,
                                onBackClicked = navController::navigateUp
                            )
                        }

                        composable(
                            route = NavRoute.ColorsScreen.route
                        ) {
                            val viewModel = koinViewModel<ColorsViewModel>()
                            val state by viewModel.uiState.collectAsStateWithLifecycle()

                            ColorsScreen(
                                state = state,
                                modifier = Modifier.fillMaxSize(),
                                onModeClicked = viewModel::onModeClicked,
                                onClicked = viewModel::onClicked,
                                onBackClicked = navController::navigateUp
                            )
                        }

                        composable(
                            route = NavRoute.DialScreen.route
                        ) {
                            val viewModel = koinViewModel<DialViewModel>()

                            DialScreen(
                                modifier = Modifier.fillMaxSize(),
                                onClicked = viewModel::onClicked,
                                onBackClicked = navController::navigateUp
                            )
                        }
                    }
                }
            }
        }

        hideSystemUI()

        keepScreenOn()

    }

    private fun keepScreenOn() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            @Suppress("DEPRECATION")
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }
}
