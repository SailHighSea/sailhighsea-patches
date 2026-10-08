package app.hdqwalls.patches.ads

import app.hdqwalls.patches.shared.Constants.COMPATIBILITY_HDQWALLS
import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch

@Suppress("unused")
val removeAdsPatch = bytecodePatch(
    name = "Remove ads",
    description = "Removes the full-screen interstitial ads. Subscriptions and purchases are not touched.",
    default = true
) {
    compatibleWith(COMPATIBILITY_HDQWALLS)

    execute {
        // Never load an interstitial ad.
        LoadInterstitialFingerprint.method.addInstructions(0, "return-void")

        // If something still asks to show one, skip it and go straight to the next step
        // (the callback), so downloads and navigation keep working.
        ShowInterstitialFingerprint.method.addInstructions(
            0,
            """
                invoke-interface { p2 }, Ldg/a;->a()Ljava/lang/Object;
                return-void
            """
        )
    }
}
