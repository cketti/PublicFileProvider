package de.cketti.fileprovider.sample


import android.content.Context
import okio.Okio
import java.io.File


class SoundFileProvider(private val context: Context) {
    companion object {
        private val SOUND_FILE_NAME = "dun_dun_dunnnnn.ogg"
    }

    val soundFile: File
        get() {
            val soundDirectory = soundDirectory
            val soundFile = File(soundDirectory, SOUND_FILE_NAME)

            if (!soundFile.exists()) {
                val resources = context.resources
                Okio.source(resources.openRawResource(R.raw.dun_dun_dunnn)).use { source ->
                    Okio.buffer(Okio.sink(soundFile)).use { sink ->
                        sink.writeAll(source)
                    }
                }
            }

            return soundFile
        }

    private val soundDirectory: File
        get() {
            val cacheDir = context.cacheDir
            val soundDirectory = File(cacheDir, "sounds")
            if (!soundDirectory.exists()) {
                if (!soundDirectory.mkdirs()) {
                    throw RuntimeException("Couldn't create directory: " + soundDirectory)
                }
            }

            return soundDirectory
        }
}
