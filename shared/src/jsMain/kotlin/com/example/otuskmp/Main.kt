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

private val accentColor = Color("#6750A4")

private fun StyleScope.actionButtonStyle() {
    padding(12.px, 24.px)
    borderRadius(24.px)
    property("border", "none")
    backgroundColor(Color.transparent)
    color(accentColor)
    fontSize(16.px)
}

private fun StyleScope.copyButtonStyle() {
    marginTop(4.px)
    padding(8.px)
    borderRadius(24.px)
    property("border", "none")
    backgroundColor(Color.transparent)
    color(accentColor)
    fontSize(24.px)
    lineHeight("1")
}

fun main() {
    renderComposable("root") {
        val viewModel = remember { StopwatchViewModel(JsClipboard()) }
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
                            actionButtonStyle()
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
                            actionButtonStyle()
                        }
                        onClick {
                            viewModel.onStopClicked()
                        }
                    }
                ) {
                    Text("Stop")
                }
            }

            Button(
                attrs = {
                    attr("aria-label", "Скопировать")
                    attr("title", "Скопировать")
                    style {
                        copyButtonStyle()
                    }
                    onClick {
                        viewModel.onCopyClicked()
                    }
                }
            ) {
                Text("⧉")
            }
        }
    }
}
