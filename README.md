# sunshine
Basic android app to get weather info

## Build instructions

Compile and generate Debug APK
```
./gradlew assembleDebug
```

Install APK in device
```
adb install -r app/build/outputs/apk/app-debug-unaligned.apk
```

Launch Sunshine from terminal
```
adb shell am start -n com.jmferrete.sunshine/com.jmferrete.sunshine.MainActivity
```
