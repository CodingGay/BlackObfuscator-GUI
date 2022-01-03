package cn.kaicity.common.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.kaicity.common.bean.InputBean
import cn.kaicity.common.platform.chooseFile
import cn.kaicity.common.platform.saveFile

/**
 * 首页
 *
 * @param modifier
 */
@Composable
fun MainView(modifier: Modifier, btnClick: (InputBean) -> Unit) {

    var input by remember { mutableStateOf("") }
    var inputIsError by remember { mutableStateOf(false) }
    var output by remember { mutableStateOf("") }
    var outputIsError by remember { mutableStateOf(false) }
    var depth by remember { mutableStateOf("1") }
    var depthIsError by remember { mutableStateOf(false) }
    var rule by remember { mutableStateOf("") }


    val textFieldModifier = Modifier.padding(12.dp).fillMaxWidth()


    Column(modifier = modifier) {

        OutlinedTextField(value = input,
            modifier = textFieldModifier,
            isError = inputIsError,
            trailingIcon = {
                Icon(Icons.Default.Search, "Menu", modifier = Modifier.clickable {
                    chooseFile {
                        input = it
                    }
                })
            },
            label = {
                Text("Input")
            },
            placeholder = {
                Text("Input Dex or Apk Path")
            },
            onValueChange = {
                input = it.replace("\n", "")
            })

        OutlinedTextField(value = output, modifier = textFieldModifier, isError = outputIsError,
            label = {
                Text("Output")
            }, trailingIcon = {
                Icon(Icons.Default.Search, "Menu", modifier = Modifier.clickable {
                    saveFile {
                        output = it
                    }
                })
            },
            placeholder = {
                Text("Output Dex or Apk Path")
            }, onValueChange = {
                output = it.replace("\n", "")
            })

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(0.3F)) {
                OutlinedTextField(value = depth,
                    modifier = textFieldModifier,
                    isError = depthIsError,
                    singleLine = true,
                    label = {
                        Text("Depth")
                    },
                    placeholder = {
                        Text("Depth")
                    },
                    onValueChange = {
                        depth = it
                    })

            }

            Box(modifier = Modifier.weight(0.7F)) {
                OutlinedTextField(value = rule, modifier = textFieldModifier,
                    label = {
                        Text("Rules")
                    },
                    placeholder = {
                        Text("Obfuscator Rules")
                    }, onValueChange = {
                        rule = it
                    })

            }
        }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Button(modifier = Modifier.padding(12.dp), onClick = {
                inputIsError = input.isEmpty()
                outputIsError = output.isEmpty()
                depthIsError = depth.isEmpty()
                //pkgIsError do not need check


                if (inputIsError || outputIsError || depthIsError) {
                    return@Button
                }

                btnClick.invoke(InputBean(input, output, depth, rule))
            }) {
                Text("Obfuscator")
            }
        }
    }

}
