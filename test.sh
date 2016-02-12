emulator -avd Nexus_5X_API_23 &

echo "waiting for device"
adb wait-for-device

echo "wait over, now waiting for boot"
A=$(adb shell getprop sys.boot_completed | tr -d '\r')

while [ "$A" != "1" ]; do
        sleep 2
        A=$(adb shell getprop sys.boot_completed | tr -d '\r')
done

echo "device boot completed"
adb shell input keyevent 82 &

./gradlew connectedCheck
