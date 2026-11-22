package app.clearscanner.patches.backup

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import app.morphe.patcher.string

// Clear Scanner is obfuscated. The names below are pinned per version: the plain objects are 10.2.56,
// the "Legacy" ones are 10.2.18. The patch picks the set by checking which backup-write method exists.

/** 10.2.56 only: MyBackupActivity.T(OutputStream), used to tell the two versions apart. */
object BackupWriteFingerprint : Fingerprint(
    definingClass = "Lcom/indymobile/app/activity/MyBackupActivity;",
    name = "T",
    returnType = "V",
    parameters = listOf("Ljava/io/OutputStream;")
)

/** 10.2.18: MainActivity.T(): opens the cloud sync screen. */
object OpenSyncScreenLegacyFingerprint : Fingerprint(
    definingClass = "Lcom/indymobile/app/activity/MainActivity;",
    name = "T",
    returnType = "V",
    parameters = listOf()
)

/** 10.2.18: MainActivity.U(boolean): "Sync now". */
object SyncNowLegacyFingerprint : Fingerprint(
    definingClass = "Lcom/indymobile/app/activity/MainActivity;",
    name = "U",
    returnType = "V",
    parameters = listOf("Z")
)

/** 10.2.18: MyBackupActivity.onActivityResult(); the backup is written by O(OutputStream). */
object BackupFileChosenLegacyFingerprint : Fingerprint(
    definingClass = "Lcom/indymobile/app/activity/MyBackupActivity;",
    name = "onActivityResult",
    returnType = "V",
    parameters = listOf("I", "I", "Landroid/content/Intent;"),
    filters = listOf(
        methodCall(definingClass = "this", name = "O")
    )
)

/** 10.2.56: MainActivity.W(): opens the cloud sync screen. */
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
    name = "onClick",
    returnType = "V",
    parameters = listOf("Landroid/view/View;"),
    filters = listOf(
        string("android.intent.action.CREATE_DOCUMENT"),
        methodCall(definingClass = "Landroid/content/Intent;", name = "<init>")
    )
)
