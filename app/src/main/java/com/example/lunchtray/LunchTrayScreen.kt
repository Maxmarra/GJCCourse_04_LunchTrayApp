package com.example.lunchtray

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel


import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.*

// TOD: Screen enum
enum class Screen(@StringRes val title: Int){
    Start(R.string.start_order),
    Entree(R.string.choose_entree),
    Side(R.string.choose_side_dish),
    Accompaniment(R.string.choose_accompaniment),
    Checkout(R.string.order_checkout)
}

// TOD: AppBar
@Composable
fun LunchTrayAppBar(
    currentScreen: Screen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun LunchTrayApp(modifier: Modifier = Modifier) {
    // TOD: Create Controller and initialization
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.Start.name
    )

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            // TOD: AppBar
            LunchTrayAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )

        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        // TOD: Navigation host
        NavHost(
            navController = navController,
            startDestination = Screen.Start.name,
            modifier = modifier.padding(innerPadding)
        ){
            composable(route = Screen.Start.name){
                    StartOrderScreen(onStartOrderButtonClicked = { navController.navigate(Screen.Entree.name) })
            }

            composable(route = Screen.Entree.name){
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onNextButtonClicked = { navController.navigate(Screen.Side.name) },
                    onSelectionChanged = {entreeItem -> viewModel.updateEntree(entreeItem) }
                )
            }
            composable(route = Screen.Side.name){
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onNextButtonClicked = { navController.navigate(Screen.Accompaniment.name) },
                    onSelectionChanged = {sideItem -> viewModel.updateSideDish(sideItem) }
                )
            }
            composable(route = Screen.Accompaniment.name){
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onNextButtonClicked = { navController.navigate(Screen.Checkout.name) },
                    onSelectionChanged = {accompanimentItem -> viewModel.updateAccompaniment(accompanimentItem) }
                )
            }
            composable(route = Screen.Checkout.name){
                val context = LocalContext.current
                CheckoutScreen(
                    orderUiState = uiState,

                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onSendButtonClicked = { subject: String, summary: String ->
                        shareOrder(context, subject, summary)}
                )

            }
        }
    }
}

private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(Screen.Start.name, inclusive = false)
}

private fun shareOrder(context: Context, subject: String, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)

    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.new_lunchtray_order)
        )
    )

}




































