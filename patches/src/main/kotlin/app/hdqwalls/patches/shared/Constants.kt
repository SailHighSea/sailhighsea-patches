package app.hdqwalls.patches.shared

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

object Constants {
    val COMPATIBILITY_HDQWALLS = Compatibility(
        name = "HDQ Walls",
        packageName = "com.hdqwalls.hdqwalls1",
        apkFileType = ApkFileType.APK,
        appIconColor = 0x1E88E5,
        targets = listOf(
            AppTarget(version = "2.5.7.0")
        )
    )
}
