# PublicFileProvider

PublicFileProvider is a special subclass of [`ContentProvider`](https://developer.android.com/reference/android/content/ContentProvider.html) that facilitates exposing files associated with an app by creating a `content://` URI for a file instead of a `file:///` URI.

**WARNING:** Most of the time this is NOT what you want. Use [FileProvider](https://developer.android.com/reference/android/support/v4/content/FileProvider.html) to only grant temporary access to files, e.g. when [sharing](https://developer.android.com/training/secure-file-sharing/index.html) content to other apps.
 
PublicFileProvider is a modified version of FileProvider with the specific goal to expose files without using Android's URI permission mechanism. This can come in handy when you have to provide a `content://` URI but can't easily grant read access to whatever app ends up accessing the content.
One use case is a custom ringtone in a notification. Check out the blog post [Notifications, Sounds, Android 7.0, and Aggravation](https://commonsware.com/blog/2016/09/07/notifications-sounds-android-7p0-aggravation.html) for more details.

## Usage

It's configured just like [FileProvider](https://developer.android.com/reference/android/support/v4/content/FileProvider.html).


## License

    Copyright 2016 cketti

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
