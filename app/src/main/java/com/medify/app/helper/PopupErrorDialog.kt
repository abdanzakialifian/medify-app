package com.medify.app.helper

import androidx.compose.runtime.Composable
import com.medify.app.designsystem.component.PopupDialog

@Composable
fun PopupErrorDialog(throwable: Throwable, onButtonClick: () -> Unit, onDismissRequest: () -> Unit) {
    return when (throwable.errorType()) {
        ApiErrorType.Network -> PopupDialog(
            title = "Koneksi Terputus",
            subtitle = "Periksa jaringan internet Anda dan coba lagi.",
            buttonTitle = "OK",
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )

        ApiErrorType.Timeout -> PopupDialog(
            title = "Waktu Habis",
            subtitle = "Server membutuhkan waktu terlalu lama untuk merespons.",
            buttonTitle = "OK",
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )

        ApiErrorType.Unauthorized -> PopupDialog(
            title = "Sesi Berakhir",
            subtitle = "Silakan login kembali untuk melanjutkan.",
            buttonTitle = "OK",
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )

        ApiErrorType.Forbidden -> PopupDialog(
            title = "Akses Ditolak",
            subtitle = "Anda tidak memiliki izin untuk melakukan tindakan ini.",
            buttonTitle = "OK",
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )

        ApiErrorType.NotFound -> PopupDialog(
            title = "Data Tidak Ditemukan",
            subtitle = "Data yang Anda cari tidak tersedia atau sudah dihapus.",
            buttonTitle = "OK",
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )

        ApiErrorType.BadRequest -> PopupDialog(
            title = "Permintaan Tidak Valid",
            subtitle = "Pastikan data yang Anda masukkan sudah benar.",
            buttonTitle = "OK",
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )

        ApiErrorType.Server -> PopupDialog(
            title = "Terjadi Kesalahan Server",
            subtitle = "Mohon coba lagi beberapa saat lagi.",
            buttonTitle = "OK",
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )

        ApiErrorType.Unknown -> PopupDialog(
            title = "Terjadi Kesalahan",
            subtitle = "Ada sesuatu yang tidak beres. Silakan coba lagi.",
            buttonTitle = "OK",
            onButtonClick = onButtonClick,
            onDismissRequest = onDismissRequest,
        )
    }
}