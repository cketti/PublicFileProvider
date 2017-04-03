package de.cketti.fileprovider.sample


import android.net.Uri


class NotificationData(val id: Int, val buildSoundUri: () -> Uri, val title: String, val contentText: String)
