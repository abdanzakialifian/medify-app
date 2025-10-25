package com.medify.app.presentation.dashboard

import androidx.lifecycle.ViewModel
import com.medify.app.R
import com.medify.app.domain.model.HealthyProductItem
import com.medify.app.domain.model.HealthyServiceType
import com.medify.app.resource.ResourceProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DashboardViewModel(private val resourceProvider: ResourceProvider) : ViewModel() {
    private val _uiState: MutableStateFlow<DashboardUiState> = MutableStateFlow(DashboardUiState())
    val uiState get() = _uiState.asStateFlow()

    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.OnSearchValueChange -> _uiState.update { it.copy(search = event.search) }
        }
    }

    fun getCategories(): List<String> = listOf(
        resourceProvider.getString(R.string.all_product),
        resourceProvider.getString(R.string.health_services),
        resourceProvider.getString(R.string.medical_equipment),
        resourceProvider.getString(R.string.medicine_and_vitamins),
        resourceProvider.getString(R.string.mother_and_baby),
        resourceProvider.getString(R.string.beauty),
    )

    fun getProducts(): List<HealthyProductItem> = listOf(
        HealthyProductItem(
            id = 1,
            rating = 5,
            image = resourceProvider.getDrawable(R.drawable.img_sterile_injection),
            name = "Suntik Steril",
            price = "Rp. 10.000",
            stock = 5
        ),
        HealthyProductItem(
            id = 2,
            rating = 5,
            image = resourceProvider.getDrawable(R.drawable.img_sterile_injection),
            name = "Suntik Steril",
            price = "Rp. 10.000",
            stock = 5
        ),
        HealthyProductItem(
            id = 3,
            rating = 5,
            image = resourceProvider.getDrawable(R.drawable.img_sterile_injection),
            name = "Suntik Steril",
            price = "Rp. 10.000",
            stock = 5
        ),
        HealthyProductItem(
            id = 4,
            rating = 5,
            image = resourceProvider.getDrawable(R.drawable.img_sterile_injection),
            name = "Suntik Steril",
            price = "Rp. 10.000",
            stock = 5
        ),
        HealthyProductItem(
            id = 5,
            rating = 5,
            image = resourceProvider.getDrawable(R.drawable.img_sterile_injection),
            name = "Suntik Steril",
            price = "Rp. 10.000",
            stock = 5
        ),
        HealthyProductItem(
            id = 6,
            rating = 5,
            image = resourceProvider.getDrawable(R.drawable.img_sterile_injection),
            name = "Suntik Steril",
            price = "Rp. 10.000",
            stock = 5
        )
    )

    fun getServiceTypeCategories(): List<String> = listOf(
        resourceProvider.getString(R.string.unit),
        resourceProvider.getString(R.string.checkup_package),
    )

    fun getServiceTypes(): List<HealthyServiceType> = listOf(
        HealthyServiceType(
            id = 1,
            name = "PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja",
            price = "Rp. 1.400.000",
            hospitalName = "Lenmarc Surabaya",
            hospitalAddress = "Dukuh Pakis, Surabaya",
            image = resourceProvider.getDrawable(R.drawable.img_dummy_first),
        ),
        HealthyServiceType(
            id = 2,
            name = "PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja",
            price = "Rp. 1.400.000",
            hospitalName = "Lenmarc Surabaya",
            hospitalAddress = "Dukuh Pakis, Surabaya",
            image = resourceProvider.getDrawable(R.drawable.img_dummy_second),
        ),
        HealthyServiceType(
            id = 3,
            name = "PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja",
            price = "Rp. 1.400.000",
            hospitalName = "Lenmarc Surabaya",
            hospitalAddress = "Dukuh Pakis, Surabaya",
            image = resourceProvider.getDrawable(R.drawable.img_dummy_first),
        ),
        HealthyServiceType(
            id = 4,
            name = "PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja",
            price = "Rp. 1.400.000",
            hospitalName = "Lenmarc Surabaya",
            hospitalAddress = "Dukuh Pakis, Surabaya",
            image = resourceProvider.getDrawable(R.drawable.img_dummy_second),
        ),
        HealthyServiceType(
            id = 5,
            name = "PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja",
            price = "Rp. 1.400.000",
            hospitalName = "Lenmarc Surabaya",
            hospitalAddress = "Dukuh Pakis, Surabaya",
            image = resourceProvider.getDrawable(R.drawable.img_dummy_first),
        ),
        HealthyServiceType(
            id = 6,
            name = "PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja",
            price = "Rp. 1.400.000",
            hospitalName = "Lenmarc Surabaya",
            hospitalAddress = "Dukuh Pakis, Surabaya",
            image = resourceProvider.getDrawable(R.drawable.img_dummy_second),
        )
    )
}