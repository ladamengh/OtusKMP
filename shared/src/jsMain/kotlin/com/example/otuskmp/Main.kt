package com.example.otuskmp

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable("root") {
        val viewModel = remember { StopwatchViewModel() }
        var uiState by remember {
            mutableStateOf(viewModel.uiState.value)
        }
        DisposableEffect(viewModel) {
            val observer = viewModel.uiState.collect { state ->
                uiState = state
            }

            onDispose {
                observer.cancel()
                viewModel.onDestroy()
            }
        }

        Div(
            attrs = {
                style {
                    width(100.vw)
                    height(100.vh)
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Column)
                    justifyContent(JustifyContent.Center)
                    alignItems(AlignItems.Center)
                    gap(24.px)
                }
            }
        ) {
            Div(
                attrs = {
                    style {
                        fontSize(48.px)
                        color(Color("#2D2D34"))
                    }
                }
            ) {
                Text(uiState.formattedTime)
            }

            Div(
                attrs = {
                    style {
                        display(DisplayStyle.Flex)
                        gap(12.px)
                    }
                }
            ) {
                Button(
                    attrs = {
                        style {
                            padding(12.px, 24.px)
                            borderRadius(24.px)
                            property("border", "none")
                            backgroundColor(Color.transparent)
                            color(Color("#6750A4"))
                            fontSize(16.px)
                        }
                        onClick {
                            viewModel.onStartClicked()
                        }
                    }
                ) {
                    Text("Start")
                }

                Button(
                    attrs = {
                        style {
                            padding(12.px, 24.px)
                            borderRadius(24.px)
                            property("border", "none")
                            backgroundColor(Color.transparent)
                            color(Color("#6750A4"))
                            fontSize(16.px)
                        }
                        onClick {
                            viewModel.onStopClicked()
                        }
                    }
                ) {
                    Text("Stop")
                }
            }
        }
    }
}
