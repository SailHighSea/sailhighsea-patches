package app.clearscanner.patches.popups

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags

/**
 * The "rate this app" dialog logic (obfuscated `qi.c(Context)`). Called from `MainActivity.onStart`,
 * counts launches and shows the dialog once enough have passed.
 */
object AppRaterFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.STATIC),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
    filters = listOf(
        string("apprater"),
        string("dontshowagain"),
        string("launch_count"),
    )
)

/**
 * Decides whether the full screen "upgrade to Pro" paywall is shown on start (obfuscated `uz5.e(...)`).
 * Returns true when it should be shown.
 */
object PaywallGateFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.STATIC),
    returnType = "Z",
    parameters = listOf("Landroid/content/Context;", "Z", "Z", "J", "J", "J", "J", "J"),
    filters = listOf(
        string("paywall_frequency"),
        string("last_paywall_shown_time"),
    )
)
