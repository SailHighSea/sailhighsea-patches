package app.hdqwalls.patches.ads

import app.morphe.patcher.Fingerprint

// Pinned to HDQ Walls 2.5.7.0 (obfuscated class names).

// Loads the interstitial ad.
object LoadInterstitialFingerprint : Fingerprint(
    definingClass = "Lwe/z0;",
    name = "X",
    returnType = "V",
    parameters = listOf("Landroid/content/Context;")
)

// Shows the interstitial ad, then runs the callback that continues what the user was doing.
object ShowInterstitialFingerprint : Fingerprint(
    definingClass = "Lwe/z0;",
    name = "t0",
    returnType = "V",
    parameters = listOf("Landroid/content/Context;", "Ldg/a;")
)
