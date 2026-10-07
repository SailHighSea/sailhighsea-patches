package app.castbox.patches.ads

import app.castbox.patches.shared.Constants.COMPATIBILITY_CASTBOX
import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch

@Suppress("unused")
val removeAdsPatch = bytecodePatch(
    name = "Remove ads",
    description = "Removes the banner and interstitial ads by turning on the app's own built-in ad block switch. " +
        "Premium and purchases are not touched.",
    default = true
) {
    compatibleWith(COMPATIBILITY_CASTBOX)

    execute {
        // The app asks this method "should ads be blocked?" before it starts the ad SDK,
        // loads interstitials and shows banners. Always answering yes switches all of them off.
        ShouldBlockAdsFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x1
                return v0
            """
        )
    }
}
