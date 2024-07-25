package tpc.com.uy.t2parking.util.models

data class CustomDialogData(
    val message: String? = null,
    val firstOnClick: (() -> Unit)? = null
)
