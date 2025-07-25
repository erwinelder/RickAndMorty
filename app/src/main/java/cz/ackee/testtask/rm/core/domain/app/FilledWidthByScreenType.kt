package cz.ackee.testtask.rm.core.domain.app

data class FilledWidthByScreenType(
    val compact: Float = .88f,
    val medium: Float = .67f,
    val expanded: Float = .44f
) {

    fun get(windowType: WindowType): Float {
        return when (windowType) {
            WindowType.Compact -> compact
            WindowType.Medium -> medium
            WindowType.Expanded -> expanded
        }
    }

}
