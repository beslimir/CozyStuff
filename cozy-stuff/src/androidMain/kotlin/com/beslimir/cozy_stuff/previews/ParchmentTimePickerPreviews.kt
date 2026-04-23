package com.beslimir.cozy_stuff.previews

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beslimir.cozy_stuff.composables.MinuteInterval
import com.beslimir.cozy_stuff.composables.ParchmentTimePicker
import com.beslimir.cozy_stuff.theme.ParchmentTheme

@Preview(showBackground = true)
@Composable
private fun ParchmentTimePicker12hPreview() {
    ParchmentTheme {
        var hour by remember { mutableIntStateOf(10) }
        var minute by remember { mutableIntStateOf(30) }

        ParchmentTimePicker(
            hour = hour,
            minute = minute,
            onHourChange = { hour = it },
            onMinuteChange = { minute = it },
            use24Hour = false,
            minuteStep = MinuteInterval.EVERY_15,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ParchmentTimePicker24hPreview() {
    ParchmentTheme {
        var hour by remember { mutableIntStateOf(14) }
        var minute by remember { mutableIntStateOf(0) }

        ParchmentTimePicker(
            hour = hour,
            minute = minute,
            onHourChange = { hour = it },
            onMinuteChange = { minute = it },
            use24Hour = true,
            minuteStep = MinuteInterval.EVERY_5,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ParchmentTimePickerEveryMinutePreview() {
    ParchmentTheme {
        var hour by remember { mutableIntStateOf(0) }
        var minute by remember { mutableIntStateOf(0) }

        ParchmentTimePicker(
            hour = hour,
            minute = minute,
            onHourChange = { hour = it },
            onMinuteChange = { minute = it },
            use24Hour = false,
            minuteStep = MinuteInterval.EVERY_MINUTE,
            modifier = Modifier.padding(16.dp),
        )
    }
}
