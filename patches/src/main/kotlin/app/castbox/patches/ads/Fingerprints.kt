package app.castbox.patches.ads

import app.morphe.patcher.Fingerprint

// ---- Castbox 11.26.1 (obfuscated names) ----

// Castbox's own "block ads" switch (it is used for regions where ads are blocked).
object ShouldBlockAdsFingerprint : Fingerprint(
    definingClass = "Lo4/g;",
    name = "d",
    returnType = "Z",
    parameters = listOf()
)

// ---- Castbox 11.24.0 (this version has no block switch, so the ad managers are patched) ----

// Interstitial manager: show.
object InterstitialShowFingerprint : Fingerprint(
    definingClass = "Lfm/castbox/ad/max/b;",
    name = "d",
    returnType = "Z",
    parameters = listOf("Lh7/a;")
)

// Interstitial manager: the two load methods.
object InterstitialLoadFingerprint : Fingerprint(
    definingClass = "Lfm/castbox/ad/max/b;",
    name = "b",
    returnType = "V",
    parameters = listOf("J")
)

object InterstitialPreloadFingerprint : Fingerprint(
    definingClass = "Lfm/castbox/ad/max/b;",
    name = "c",
    returnType = "V",
    parameters = listOf("J")
)

// Player banner manager: "may a banner be shown/loaded".
object BannerAllowedFingerprint : Fingerprint(
    definingClass = "Lfm/castbox/ad/max/d;",
    name = "d",
    returnType = "Z",
    parameters = listOf()
)
