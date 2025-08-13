package org.mifos.fineract.client.sample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "fineract-client-sample",
    ) {
        App()
    }
}