package app.castbox.patches.ads

import app.morphe.patcher.Fingerprint

// Castbox's own "block ads" switch (it is used for regions where ads are blocked).
// Pinned to Castbox 11.26.1 (obfuscated class names).
object ShouldBlockAdsFingerprint : Fingerprint(
    definingClass = "Lo4/g;",
    name = "d",
    returnType = "Z",
    parameters = listOf()
)
