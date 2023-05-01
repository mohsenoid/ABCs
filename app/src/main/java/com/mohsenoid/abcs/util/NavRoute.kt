package com.mohsenoid.abcs.util

sealed class NavRoute(val route: String) {

    object SelectorScreen : NavRoute(route = "selector_screen")

    object AlphabetsScreen : NavRoute(route = "alphabets_screen")

    object NumbersScreen : NavRoute(route = "numbers_screen")

    object ShapesScreen : NavRoute(route = "shapes_screen")

    object ColorsScreen : NavRoute(route = "colors_screen")

    object DialScreen : NavRoute(route = "dial_screen")
}