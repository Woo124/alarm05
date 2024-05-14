package ui.popup

import androidx.compose.runtime.Composable

/*
@Composable
fun AlarmPopup(
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (showBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet.value = false
            },
            sheetState = sheetState
        ) {
            Surface(Modifier.padding(20.dp, 0.dp, 20.dp, 20.dp)) {
                title()
            }

            BoxWithConstraints(Modifier.fillMaxWidth()) {
                val columnLength = if (isHorizontal()) 4 else 2
                Column(Modifier.fillMaxWidth(), Arrangement.spacedBy(20.dp)) {
                    for (i in 0 until ceil(targetStringList.size/columnLength.toDouble()).toInt()) {
                        Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp), Arrangement.spacedBy(20.dp)) {
                            for (j in 0 until columnLength) {
                                val item = i * columnLength + j
                                val desc = targetStringList.getOrNull(item)
                                if (desc != null) {
                                    NeuPulsateEffectFlatButton(
                                        onClick = {
                                            selectionState.value = item
                                        },
                                        modifier = Modifier.weight(1f),
                                        contentPadding = PaddingValues(4.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            if (selectionState.value == item) MaterialTheme.colorScheme.onTertiary
                                            else appColorSet.listSelectionButtonBackground)
                                    ) {
                                        Row(Modifier.fillMaxWidth(), Arrangement.Start, Alignment.CenterVertically) {
                                            targetIconList[item](desc, Modifier.padding(10.dp).size(48.dp))
                                            BodyText(desc, Modifier.padding(end = 12.dp), fontSize = headTextFontSize, color
                                            = if (selectionState.value == item) MaterialTheme.colorScheme.tertiary
                                            else appColorSet.listSelectionButtonForeground,
                                                resizer = remember { mutableStateOf(1f) })
                                        }
                                    }
                                } else {
                                    Box(Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }

            Row(Modifier.fillMaxWidth(), Arrangement.Center) {
                NeuPulsateEffectFlatButton(
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet.value = false
                            }
                        }
                    },
                    modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 30.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onTertiary),
                    contentPadding = PaddingValues(24.dp, 16.dp)
                ) {
                    StatusText("Close", color = MaterialTheme.colorScheme.tertiary)
                }
            }
        }
    }
}
*/
