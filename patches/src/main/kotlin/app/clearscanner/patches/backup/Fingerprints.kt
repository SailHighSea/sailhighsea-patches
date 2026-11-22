package app.clearscanner.patches.backup

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import app.morphe.patcher.string

// Clear Scanner 10.2.56 is obfuscated. These names are pinned to that exact version.

/** MainActivity.W(): opens the cloud sync screen. */
object OpenSyncScreenFingerprint : Fingerprint(
    definingClass = "Lcom/indymobile/app/activity/MainActivity;",
    name = "W",
    returnType = "V",
    parameters = listOf()
)

/** MainActivity.X(boolean): "Sync now". Starts a sync, or opens the sync screen when not connected. */
object SyncNowFingerprint : Fingerprint(
    definingClass = "Lcom/indymobile/app/activity/MainActivity;",
    name = "X",
    returnType = "V",
    parameters = listOf("Z")
)

/** MyBackupActivity.onCreate(): the existing "Backup file / Restore" screen. */
object BackupScreenCreateFingerprint : Fingerprint(
    definingClass = "Lcom/indymobile/app/activity/MyBackupActivity;",
    name = "onCreate",
    returnType = "V",
    parameters = listOf("Landroid/os/Bundle;"),
    filters = listOf(
        // The two buttons: create backup, restore.
        methodCall(name = "setOnClickListener"),
        methodCall(name = "setOnClickListener")
    )
)

/** MyBackupActivity.onActivityResult(): the user picked a file; the backup is written by T(OutputStream). */
object BackupFileChosenFingerprint : Fingerprint(
    definingClass = "Lcom/indymobile/app/activity/MyBackupActivity;",
    name = "onActivityResult",
    returnType = "V",
    parameters = listOf("I", "I", "Landroid/content/Intent;"),
    filters = listOf(
        methodCall(definingClass = "this", name = "T")
    )
)

/** Click listener of the backup screen buttons. The backup case opens a CREATE_DOCUMENT file picker. */
object BackupButtonClickFingerprint : Fingerprint(
    definingClass = "Lht3;",
    name = "onClick",
    returnType = "V",
    parameters = listOf("Landroid/view/View;"),
    filters = listOf(
        string("android.intent.action.CREATE_DOCUMENT"),
        methodCall(definingClass = "Landroid/content/Intent;", name = "<init>")
    )
)
