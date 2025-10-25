package com.medify.app.presentation.dashboard

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.medify.app.R
import com.medify.app.designsystem.theme.BluePastel
import com.medify.app.designsystem.theme.CyanBright
import com.medify.app.designsystem.theme.GrayMedium
import com.medify.app.designsystem.theme.GrayUltraLight
import com.medify.app.designsystem.theme.GreenPastel
import com.medify.app.designsystem.theme.GreenPrimary
import com.medify.app.designsystem.theme.MedifyTheme
import com.medify.app.designsystem.theme.OrangeAccent
import com.medify.app.designsystem.theme.White
import com.medify.app.designsystem.theme.YellowSun
import com.medify.app.domain.model.HealthyProductItem
import com.medify.app.domain.model.HealthyServiceType
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val activity = LocalActivity.current

    val categories = remember { viewModel.getCategories() }

    val products = remember { viewModel.getProducts() }

    val serviceTypeCategories = remember { viewModel.getServiceTypeCategories() }

    val serviceTypes = remember { viewModel.getServiceTypes() }

    DashboardContent(
        uiState = uiState,
        categories = categories,
        products = products,
        serviceTypeCategories = serviceTypeCategories,
        serviceTypes = serviceTypes,
        onSearchValueChange = { search ->
            viewModel.onEvent(DashboardEvent.OnSearchValueChange(search))
        },
        onFeatureNotAvailable = {
            Toast.makeText(
                activity,
                activity?.resources?.getString(R.string.feature_not_available),
                Toast.LENGTH_SHORT
            ).show()
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardContent(
    uiState: DashboardUiState,
    categories: List<String>,
    products: List<HealthyProductItem>,
    serviceTypeCategories: List<String>,
    serviceTypes: List<HealthyServiceType>,
    onSearchValueChange: (String) -> Unit,
    onFeatureNotAvailable: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {},
                        content = {
                            Icon(
                                painter = painterResource(R.drawable.ic_humberger_menu),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    )
                },
                actions = {
                    IconButton(
                        onClick = onFeatureNotAvailable,
                        content = {
                            Icon(
                                painter = painterResource(R.drawable.ic_chart_menu),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    )

                    IconButton(
                        onClick = onFeatureNotAvailable,
                        content = {
                            Icon(
                                painter = painterResource(R.drawable.ic_notification_menu),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        val context = LocalContext.current

        val coroutineScope = rememberCoroutineScope()

        val productPagerState = rememberPagerState(pageCount = { categories.size })

        val serviceTypePagerState = rememberPagerState(pageCount = { serviceTypeCategories.size })

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(Modifier.padding(start = 24.dp, top = 40.dp, end = 24.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(16.dp),
                            spotColor = MaterialTheme.colorScheme.primary,
                            ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.24f)
                        )
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    White,
                                    GrayUltraLight,
                                    BluePastel
                                )
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ),
                ) {
                    ConstraintLayout(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 130.dp, bottom = 16.dp)) {
                        val (titleText, subTitleText, readMoreButton, indicatorRow) = createRefs()

                        Text(
                            modifier = Modifier.constrainAs(titleText) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                                width = Dimension.preferredWrapContent
                                horizontalBias = 0F
                            },
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = FontFamily(Font(R.font.gilroy_semi_bold)),
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 18.sp
                                    )
                                ) {
                                    append(stringResource(R.string.title_solution_prefix))
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = FontFamily(Font(R.font.gilroy_extra_bold)),
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 18.sp
                                    )
                                ) {
                                    append(stringResource(R.string.title_solution_suffix))
                                }
                            }
                        )

                        Text(
                            modifier = Modifier.constrainAs(subTitleText) {
                                start.linkTo(parent.start)
                                top.linkTo(titleText.bottom, margin = 10.dp)
                                end.linkTo(parent.end)
                                width = Dimension.preferredWrapContent
                                horizontalBias = 0F
                            },
                            text = stringResource(R.string.subtitle_health_info),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primaryContainer
                        )

                        Button(
                            modifier = Modifier.constrainAs(readMoreButton) {
                                start.linkTo(parent.start)
                                top.linkTo(subTitleText.bottom, margin = 12.dp)
                                end.linkTo(parent.end)
                                width = Dimension.preferredWrapContent
                                horizontalBias = 0F
                            },
                            shape = RoundedCornerShape(8.dp),
                            onClick = onFeatureNotAvailable,
                            content = {
                                Text(
                                    text = stringResource(R.string.button_more),
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        )

                        Row(
                            modifier = Modifier
                                .constrainAs(indicatorRow) {
                                    top.linkTo(readMoreButton.top)
                                    end.linkTo(parent.end, 20.dp)
                                    bottom.linkTo(readMoreButton.bottom)
                                    verticalBias = 1F
                                }
                                .offset(x = 120.dp)
                                .padding(bottom = 14.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(50.dp)
                                    .height(10.dp)
                                    .background(color = MaterialTheme.colorScheme.background, shape = CircleShape)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(color = MaterialTheme.colorScheme.background, shape = CircleShape)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(color = MaterialTheme.colorScheme.background, shape = CircleShape)
                            )
                        }
                    }
                }

                Icon(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(y = (-40).dp, x = 10.dp),
                    painter = painterResource(R.drawable.ic_calendar),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }

            Box(Modifier.padding(start = 24.dp, top = 60.dp, end = 24.dp)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(16.dp),
                            spotColor = MaterialTheme.colorScheme.outline,
                            ambientColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.24f)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    ConstraintLayout(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 130.dp, bottom = 16.dp)) {
                        val (titleText, subTitleText, readMoreButton) = createRefs()

                        Text(
                            modifier = Modifier.constrainAs(titleText) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                                width = Dimension.preferredWrapContent
                                horizontalBias = 0F
                            },
                            text = stringResource(R.string.special_service),
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.gilroy_semi_bold)),
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        )

                        Text(
                            modifier = Modifier.constrainAs(subTitleText) {
                                start.linkTo(parent.start)
                                top.linkTo(titleText.bottom, margin = 10.dp)
                                end.linkTo(parent.end)
                                width = Dimension.preferredWrapContent
                                horizontalBias = 0F
                            },
                            text = stringResource(R.string.covid_test_title),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primaryContainer
                        )

                        Button(
                            modifier = Modifier.constrainAs(readMoreButton) {
                                start.linkTo(parent.start)
                                top.linkTo(subTitleText.bottom, margin = 12.dp)
                                end.linkTo(parent.end)
                                width = Dimension.preferredWrapContent
                                horizontalBias = 0F
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            onClick = onFeatureNotAvailable,
                            contentPadding = PaddingValues.Zero,
                            content = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = stringResource(R.string.button_more),
                                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                        color = MaterialTheme.colorScheme.primary
                                    )

                                    Icon(
                                        modifier = Modifier.padding(start = 8.dp),
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        )
                    }
                }

                Icon(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(y = (-40).dp, x = (-14).dp),
                    painter = painterResource(R.drawable.ic_vaccine),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }

            Box(Modifier.padding(start = 24.dp, top = 60.dp, end = 24.dp)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(16.dp),
                            spotColor = MaterialTheme.colorScheme.outline,
                            ambientColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.24f)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    ConstraintLayout(modifier = Modifier.padding(start = 120.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)) {
                        val (titleText, subTitleText, readMoreButton) = createRefs()

                        Text(
                            modifier = Modifier.constrainAs(titleText) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                                width = Dimension.preferredWrapContent
                                horizontalBias = 0F
                            },
                            text = stringResource(R.string.track_examination_title),
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.gilroy_semi_bold)),
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        )

                        Text(
                            modifier = Modifier.constrainAs(subTitleText) {
                                start.linkTo(parent.start)
                                top.linkTo(titleText.bottom, margin = 10.dp)
                                end.linkTo(parent.end)
                                width = Dimension.preferredWrapContent
                                horizontalBias = 0F
                            },
                            text = stringResource(R.string.track_examination_subtitle),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primaryContainer
                        )

                        Button(
                            modifier = Modifier.constrainAs(readMoreButton) {
                                start.linkTo(parent.start)
                                top.linkTo(subTitleText.bottom, margin = 12.dp)
                                end.linkTo(parent.end)
                                width = Dimension.preferredWrapContent
                                horizontalBias = 0F
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            onClick = onFeatureNotAvailable,
                            contentPadding = PaddingValues.Zero,
                            content = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = stringResource(R.string.button_track),
                                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                        color = MaterialTheme.colorScheme.primary
                                    )

                                    Icon(
                                        modifier = Modifier.padding(start = 8.dp),
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        )
                    }
                }

                Icon(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(y = (-20).dp, x = 20.dp),
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }

            Row(modifier = Modifier.padding(start = 24.dp, top = 40.dp, end = 24.dp)) {
                Box(
                    modifier = Modifier
                        .size(55.dp)
                        .shadow(
                            elevation = 8.dp,
                            shape = CircleShape,
                            spotColor = MaterialTheme.colorScheme.outline,
                            ambientColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.24f)
                        )
                        .background(shape = CircleShape, color = MaterialTheme.colorScheme.background)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = onFeatureNotAvailable
                        )
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        painter = painterResource(R.drawable.ic_filter),
                        contentDescription = null
                    )
                }

                Spacer(Modifier.width(20.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(50.dp),
                            spotColor = MaterialTheme.colorScheme.outline,
                            ambientColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.24f)
                        ),
                    shape = RoundedCornerShape(50.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background),
                        value = uiState.search,
                        onValueChange = onSearchValueChange,
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.proximanova_bold)),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp,
                        ),
                        shape = RoundedCornerShape(8.dp),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.search_hint),
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.proximanova_regular)),
                                    color = MaterialTheme.colorScheme.outline,
                                    fontSize = 16.sp,
                                )
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            cursorColor = MaterialTheme.colorScheme.outline,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            errorContainerColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                    )
                }
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 24.dp),
            ) {
                itemsIndexed(categories) { index, category ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(30.dp),
                                spotColor = if (index == productPagerState.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                                ambientColor = if (index == productPagerState.currentPage) MaterialTheme.colorScheme.primary.copy(alpha = 0.24f) else
                                    MaterialTheme.colorScheme.outline.copy(alpha = 0.24f)
                            )
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = {
                                    coroutineScope.launch {
                                        productPagerState.animateScrollToPage(index)
                                    }
                                }
                            ),
                        colors = CardDefaults.cardColors(containerColor = if (index == productPagerState.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background),
                        shape = RoundedCornerShape(30),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                            text = category,
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = if (index == productPagerState.currentPage) FontWeight.Bold else FontWeight.SemiBold)
                        )
                    }
                }
            }

            HorizontalPager(
                state = productPagerState,
                beyondViewportPageCount = 1,
                verticalAlignment = Alignment.Top,
                userScrollEnabled = false,
            ) { page ->
                when (categories[page]) {
                    context.getString(R.string.all_product) -> ProductsContent(
                        modifier = Modifier.padding(top = 26.dp),
                        products = products
                    )

                    context.getString(R.string.health_services) -> ProductsContent(
                        modifier = Modifier.padding(top = 26.dp),
                        products = products
                    )

                    context.getString(R.string.medical_equipment) -> ProductsContent(
                        modifier = Modifier.padding(top = 26.dp),
                        products = products
                    )

                    context.getString(R.string.medicine_and_vitamins) -> ProductsContent(
                        modifier = Modifier.padding(top = 26.dp),
                        products = products
                    )

                    context.getString(R.string.mother_and_baby) -> ProductsContent(
                        modifier = Modifier.padding(top = 26.dp),
                        products = products
                    )

                    context.getString(R.string.beauty) -> ProductsContent(
                        modifier = Modifier.padding(top = 26.dp),
                        products = products
                    )
                }
            }

            Text(
                modifier = Modifier.padding(start = 24.dp, top = 40.dp, end = 24.dp),
                text = stringResource(R.string.choose_service_type),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 24.dp, top = 22.dp, end = 24.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(20.dp),
                        spotColor = MaterialTheme.colorScheme.outline,
                        ambientColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.24f)
                    )
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(4.dp),
            ) {
                serviceTypeCategories.forEachIndexed { index, type ->
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .background(
                                color = if (index == serviceTypePagerState.currentPage) CyanBright else Color.Transparent,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(vertical = 4.dp, horizontal = 12.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = {
                                    coroutineScope.launch {
                                        serviceTypePagerState.animateScrollToPage(index)
                                    }
                                }
                            ),
                        text = type,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(if (index == serviceTypePagerState.currentPage) R.font.proximanova_bold else R.font.proximanova_regular)),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }

            HorizontalPager(
                state = serviceTypePagerState,
                beyondViewportPageCount = 1,
                verticalAlignment = Alignment.Top,
                userScrollEnabled = false,
            ) { page ->
                when (serviceTypeCategories[page]) {
                    context.getString(R.string.unit) -> ServiceTypeContent(
                        modifier = Modifier.padding(start = 24.dp, top = 10.dp, end = 24.dp),
                        serviceTypes = serviceTypes
                    )

                    context.getString(R.string.checkup_package) -> ServiceTypeContent(
                        modifier = Modifier.padding(start = 24.dp, top = 10.dp, end = 24.dp),
                        serviceTypes = serviceTypes
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_load_more),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stringResource(R.string.show_more),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.proximanova_bold)),
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                )
            }
        }
    }
}

@Composable
private fun ProductsContent(products: List<HealthyProductItem>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(products, key = { it.id }) { product ->
            Card(
                modifier = Modifier.shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp),
                    spotColor = MaterialTheme.colorScheme.outline,
                    ambientColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.24f)
                ),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                ConstraintLayout(modifier = Modifier.padding(10.dp)) {
                    val (ratingIcon, totalRating, productImage, productName, productPrice, productStock) = createRefs()

                    Icon(
                        modifier = Modifier.constrainAs(ratingIcon) {
                            end.linkTo(totalRating.start, margin = 2.dp)
                            top.linkTo(parent.top)
                        },
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = YellowSun
                    )

                    Text(
                        modifier = Modifier.constrainAs(totalRating) {
                            top.linkTo(ratingIcon.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(ratingIcon.bottom)
                        },
                        text = product.rating.toString(),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.proximanova_bold)),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                    )

                    product.image?.toBitmap()?.asImageBitmap()?.let {
                        Image(
                            modifier = Modifier.constrainAs(productImage) {
                                start.linkTo(parent.start)
                                top.linkTo(ratingIcon.bottom)
                                end.linkTo(parent.end)
                            },
                            bitmap = it,
                            contentDescription = null
                        )
                    }

                    Text(
                        modifier = Modifier.constrainAs(productName) {
                            start.linkTo(parent.start)
                            top.linkTo(productImage.bottom, margin = 12.dp)
                            end.linkTo(parent.end)
                            width = Dimension.preferredWrapContent
                            horizontalBias = 0F
                        },
                        text = product.name,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.proximanova_bold)),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    )

                    createHorizontalChain(productPrice, productStock, chainStyle = ChainStyle.Packed)

                    Text(
                        modifier = Modifier.constrainAs(productPrice) {
                            start.linkTo(parent.start)
                            top.linkTo(productName.bottom, margin = 8.dp)
                            end.linkTo(productStock.start)
                            width = Dimension.preferredWrapContent
                            horizontalBias = 0F
                        },
                        text = product.price,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.proximanova_bold)),
                            fontSize = 12.sp,
                            color = OrangeAccent,
                        )
                    )

                    Text(
                        modifier = Modifier
                            .constrainAs(productStock) {
                                start.linkTo(productPrice.end)
                                end.linkTo(parent.end)
                                bottom.linkTo(productPrice.bottom)
                                width = Dimension.preferredWrapContent
                                horizontalBias = 1F
                            }
                            .padding(start = 24.dp)
                            .background(
                                shape = RoundedCornerShape(4.dp),
                                color = GreenPastel
                            )
                            .padding(vertical = 2.dp, horizontal = 4.dp),
                        text = if (product.stock > 0) stringResource(R.string.ready_stock) else stringResource(R.string.empty_stock),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.proximanova_regular)),
                            fontSize = 10.sp,
                            color = GreenPrimary,
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ServiceTypeContent(serviceTypes: List<HealthyServiceType>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(vertical = 20.dp)
    ) {
        items(serviceTypes, key = { it.id }) { type ->
            Card(
                modifier = Modifier.shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp),
                    spotColor = MaterialTheme.colorScheme.outline,
                    ambientColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.24f)
                ),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                ConstraintLayout {
                    val (typeName, price, hospitalName, hospitalNameIcon, hospitalAddress, hospitalAddressIcon, hospitalImage) = createRefs()

                    Text(
                        modifier = Modifier
                            .constrainAs(typeName) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                end.linkTo(hospitalImage.start, margin = 8.dp)
                                width = Dimension.preferredWrapContent
                                horizontalBias = 0F
                            }
                            .padding(start = 20.dp, top = 10.dp),
                        text = type.name,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.proximanova_bold)),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )

                    Text(
                        modifier = Modifier
                            .constrainAs(price) {
                                start.linkTo(parent.start)
                                top.linkTo(typeName.bottom, margin = 12.dp)
                                end.linkTo(hospitalImage.start, margin = 8.dp)
                                width = Dimension.preferredWrapContent
                                horizontalBias = 0F
                            }
                            .padding(start = 20.dp),
                        text = type.price,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.proximanova_bold)),
                            fontSize = 14.sp,
                            color = OrangeAccent
                        )
                    )

                    createHorizontalChain(hospitalNameIcon, hospitalName, chainStyle = ChainStyle.Packed)

                    Icon(
                        modifier = Modifier
                            .constrainAs(hospitalNameIcon) {
                                start.linkTo(parent.start)
                                top.linkTo(hospitalName.top)
                                end.linkTo(hospitalName.start)
                                bottom.linkTo(hospitalName.bottom)
                                horizontalBias = 0F
                            }
                            .padding(start = 20.dp),
                        painter = painterResource(R.drawable.ic_hospital),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .constrainAs(hospitalName) {
                                start.linkTo(hospitalNameIcon.end)
                                top.linkTo(price.bottom, margin = 20.dp)
                                end.linkTo(hospitalImage.start, margin = 8.dp)
                                width = Dimension.preferredWrapContent
                                horizontalBias = 0F
                            }
                            .padding(start = 8.dp),
                        text = type.hospitalName,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.proximanova_bold)),
                            fontSize = 14.sp,
                            color = GrayMedium
                        )
                    )

                    createHorizontalChain(hospitalAddressIcon, hospitalAddress, chainStyle = ChainStyle.Packed)

                    Icon(
                        modifier = Modifier
                            .constrainAs(hospitalAddressIcon) {
                                start.linkTo(parent.start)
                                top.linkTo(hospitalAddress.top)
                                end.linkTo(hospitalAddress.start)
                                bottom.linkTo(hospitalAddress.bottom)
                                horizontalBias = 0F
                            }
                            .padding(start = 20.dp),
                        painter = painterResource(R.drawable.ic_mark_location),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .constrainAs(hospitalAddress) {
                                start.linkTo(hospitalAddressIcon.end)
                                top.linkTo(hospitalName.bottom, margin = 8.dp)
                                end.linkTo(hospitalImage.start, margin = 8.dp)
                                width = Dimension.preferredWrapContent
                                horizontalBias = 0F
                            }
                            .padding(start = 8.dp),
                        text = type.hospitalAddress,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.proximanova_regular)),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                    )

                    type.image?.toBitmap()?.asImageBitmap()?.let {
                        Image(
                            modifier = Modifier.constrainAs(hospitalImage) {
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            },
                            bitmap = it,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DashboardContentPreview() {
    MedifyTheme {
        val context = LocalContext.current
        DashboardContent(
            uiState = DashboardUiState(),
            categories = listOf("All Products, Health Care, Pharmacy"),
            products = listOf(
                HealthyProductItem(
                    id = 1,
                    rating = 5,
                    image = AppCompatResources.getDrawable(context, R.drawable.img_sterile_injection),
                    name = "Suntik Steril",
                    price = "Rp. 10.000",
                    stock = 5
                ),
                HealthyProductItem(
                    id = 2,
                    rating = 5,
                    image = AppCompatResources.getDrawable(context, R.drawable.img_sterile_injection),
                    name = "Suntik Steril",
                    price = "Rp. 10.000",
                    stock = 5
                ),
                HealthyProductItem(
                    id = 3,
                    rating = 5,
                    image = AppCompatResources.getDrawable(context, R.drawable.img_sterile_injection),
                    name = "Suntik Steril",
                    price = "Rp. 10.000",
                    stock = 5
                )
            ),
            serviceTypeCategories = listOf(
                "Satuan",
                "Paket Pemeriksaan"
            ),
            serviceTypes = listOf(
                HealthyServiceType(
                    id = 1,
                    name = "PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja",
                    price = "Rp. 1.400.000",
                    hospitalName = "Lenmarc Surabaya",
                    hospitalAddress = "Dukuh Pakis, Surabaya",
                    image = AppCompatResources.getDrawable(context, R.drawable.img_dummy_first),
                ),
                HealthyServiceType(
                    id = 2,
                    name = "PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja",
                    price = "Rp. 1.400.000",
                    hospitalName = "Lenmarc Surabaya",
                    hospitalAddress = "Dukuh Pakis, Surabaya",
                    image = AppCompatResources.getDrawable(context, R.drawable.img_dummy_second),
                ),
                HealthyServiceType(
                    id = 3,
                    name = "PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja",
                    price = "Rp. 1.400.000",
                    hospitalName = "Lenmarc Surabaya",
                    hospitalAddress = "Dukuh Pakis, Surabaya",
                    image = AppCompatResources.getDrawable(context, R.drawable.img_dummy_second),
                )
            ),
            onSearchValueChange = {},
            onFeatureNotAvailable = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AllProductsContentPreview() {
    MedifyTheme {
        val context = LocalContext.current
        ProductsContent(
            products = listOf(
                HealthyProductItem(
                    id = 1,
                    rating = 5,
                    image = AppCompatResources.getDrawable(context, R.drawable.img_sterile_injection),
                    name = "Suntik Steril",
                    price = "Rp. 10.000",
                    stock = 5
                ),
                HealthyProductItem(
                    id = 1,
                    rating = 5,
                    image = AppCompatResources.getDrawable(context, R.drawable.img_sterile_injection),
                    name = "Suntik Steril",
                    price = "Rp. 10.000",
                    stock = 5
                ),
                HealthyProductItem(
                    id = 1,
                    rating = 5,
                    image = AppCompatResources.getDrawable(context, R.drawable.img_sterile_injection),
                    name = "Suntik Steril",
                    price = "Rp. 10.000",
                    stock = 5
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ServiceTypeContentPreview() {
    MedifyTheme {
        val context = LocalContext.current
        ServiceTypeContent(
            serviceTypes = listOf(
                HealthyServiceType(
                    id = 1,
                    name = "PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja",
                    price = "Rp. 1.400.000",
                    hospitalName = "Lenmarc Surabaya",
                    hospitalAddress = "Dukuh Pakis, Surabaya",
                    image = AppCompatResources.getDrawable(context, R.drawable.img_dummy_first),
                ),
                HealthyServiceType(
                    id = 2,
                    name = "PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja",
                    price = "Rp. 1.400.000",
                    hospitalName = "Lenmarc Surabaya",
                    hospitalAddress = "Dukuh Pakis, Surabaya",
                    image = AppCompatResources.getDrawable(context, R.drawable.img_dummy_second),
                ),
                HealthyServiceType(
                    id = 3,
                    name = "PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja",
                    price = "Rp. 1.400.000",
                    hospitalName = "Lenmarc Surabaya",
                    hospitalAddress = "Dukuh Pakis, Surabaya",
                    image = AppCompatResources.getDrawable(context, R.drawable.img_dummy_second),
                )
            )
        )
    }
}