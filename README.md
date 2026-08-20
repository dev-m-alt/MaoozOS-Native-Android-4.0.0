# MaoozOS Native Android 4.0.0

This is the native Android foundation for MaoozOS v4. It intentionally does **not** wrap the web app in a WebView. The UI is native Android Views for smoother scrolling, correct text measurement, better touch behavior, and lower rendering cost.

## Compatibility
- minSdk 23 (Android 6.0)
- target/compile SDK 36
- Java 17

## Core features in this foundation
- Native dashboard with classes/deadlines priority
- Native navigation
- Native search across saved records
- Academic profile storage
- Courses + credit hours
- Timetable/classes
- Tasks
- Notes
- URL resources with offline recognition
- Native notification channel + test notification
- Notification settings shortcut
- Exact-alarm settings shortcut on Android 12+
- Full JSON backup/export and validated import/restore
- First-run tutorial and replay from Settings
- Persistent local data using SharedPreferences JSON store

## Build without Android Studio
The included GitHub Actions workflow builds the APK on GitHub-hosted runners.

## Important
This is the first **native v4 foundation**. It deliberately replaces the laggy WebView UI architecture. More of the previously planned modules can be migrated into native screens on top of the same data foundation without bringing WebView back.
