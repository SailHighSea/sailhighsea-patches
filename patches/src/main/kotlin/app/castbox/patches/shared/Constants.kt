package app.castbox.patches.shared

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

object Constants {
    val COMPATIBILITY_CASTBOX = Compatibility(
        name = "Castbox",
        packageName = "fm.castbox.audiobook.radio.podcast",
        apkFileType = ApkFileType.APK,
        appIconColor = 0xF43E37,
        targets = listOf(
            AppTarget(version = "11.26.1"),
            AppTarget(version = "11.24.0")
        )
    )
}
