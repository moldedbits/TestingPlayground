emulator -avd lollipop -no-skin -no-audio -no-window &
adb wait-for-device
adb shell input keyevent 82 &
./gradlew connectedAndroidTest
