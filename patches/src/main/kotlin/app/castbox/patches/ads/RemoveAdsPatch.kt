package app.castbox.patches.ads

import app.castbox.patches.shared.Constants.COMPATIBILITY_CASTBOX
import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch

@Suppress("unused")
val removeAdsPatch = bytecodePatch(
    name = "Remove ads",
    description = "Removes the banner and interstitial ads. Premium and purchases are not touched.",
    default = true
) {
    compatibleWith(COMPATIBILITY_CASTBOX)

    execute {
        // 11.26.1: the app asks "should ads be blocked?" before it starts the ad SDK,
        // loads interstitials and shows banners. Always answering yes switches all of them off.
        val blockSwitch = try {
            ShouldBlockAdsFingerprint.method
        } catch (e: Exception) {
            null
        }

        if (blockSwitch != null) {
            blockSwitch.addInstructions(
                0,
                """
                    const/4 v0, 0x1
                    return v0
                """
            )
            return@execute
        }

        // 11.24.0: no block switch, so turn off the ad managers directly.
        InterstitialShowFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return v0
            """
        )
        InterstitialLoadFingerprint.method.addInstructions(0, "return-void")
        InterstitialPreloadFingerprint.method.addInstructions(0, "return-void")
        BannerAllowedFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return v0
            """
        )
    }
}
