package app.clearscanner.patches.popups

import app.clearscanner.patches.shared.Constants.COMPATIBILITY_CLEAR_SCANNER
import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch

@Suppress("unused")
val disablePopupsPatch = bytecodePatch(
    name = "Disable popups",
    description = "Removes the \"rate this app\" popup and the \"upgrade to Pro\" screen shown on start.",
    default = true
) {
    compatibleWith(COMPATIBILITY_CLEAR_SCANNER)

    execute {
        AppRaterFingerprint.method.addInstructions(0, "return-void")

        PaywallGateFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return v0
            """
        )
    }
}
