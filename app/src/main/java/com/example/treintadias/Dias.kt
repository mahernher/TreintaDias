package com.example.treintadias

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.treintadias.ui.theme.TreintaDiasTheme

/**
 * Entry point.
 */
@Composable
fun MainScreen() {
    TreintaDiasTheme {
        DaysHome(lastDays = DiasFalsos.onlyDays)
    }
}

/**
 * Composable that displays all the content in the screen.
 * A LazyColumn will be used to display the entire item view on the screen.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DaysHome(
    lastDays: List<Dia>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            DaysTopBar()
        }
    ) {
        LazyColumn(
            modifier = modifier.background(
                MaterialTheme.colors.background
            )
        ) {
            items(lastDays) { days ->
                DaysCard(daysInformation = days)
            }
        }
    }
}

@Composable
fun DaysCard(
    daysInformation: Dia,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                DaysAndTitle(
                    numberText = daysInformation.days,
                    titleText = daysInformation.titleDays
                )
                Spacer(modifier = Modifier.weight(1f))
                ExpandedButton(
                    onClick = {
                        expanded = !expanded },
                    expanded = expanded
                )
            }
            if (expanded) {
                DaysImage(
                    illustrativeImage = daysInformation.imageDays
                )
                DescriptionDays(
                    descriptionText = daysInformation.descriptionDays
                )
            }
        }
    }
}

@Composable
fun DaysAndTitle(
    @StringRes numberText: Int,
    @StringRes titleText: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(
                top = 10.dp,
                start = 10.dp,
                bottom = 10.dp
            )
    ) {
        Text(
            text = stringResource(id = numberText),
            style = MaterialTheme.typography.h2,
            //color = MaterialTheme.colors.onPrimary,
            modifier = modifier.padding(top = 8.dp)
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )
        Text(
            text = stringResource(id = titleText),
            //color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
fun DaysImage(
    @DrawableRes illustrativeImage: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = illustrativeImage),
        // Image description is null as it is for illustrative purposes.
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
    )
}
@Composable
fun ExpandedButton(
    onClick: () -> Unit,
    expanded: Boolean,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            tint = MaterialTheme.colors.onPrimary,
            contentDescription = null
        )
    }
}

@Composable
fun DescriptionDays(
    @StringRes descriptionText: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(
                top = 20.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 20.dp
            )
    ) {
        Text(
            text = stringResource(id = descriptionText),
            style = MaterialTheme.typography.body1,
            //color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun DaysTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.top_bar),
            //color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h1,
            modifier = modifier
                .padding(
                    start = 10.dp,
                    bottom = 10.dp,
                    top = 10.dp,

                    )
        )
    }
}

@Preview
@Composable
fun AppPreviewDark() {
    TreintaDiasTheme(darkTheme = true) {
        MainScreen()
    }
}

@Preview
@Composable
fun AppPreview() {
    TreintaDiasTheme() {
        MainScreen()
    }
}
