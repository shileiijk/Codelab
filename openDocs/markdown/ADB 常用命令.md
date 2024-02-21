# ADB 常用命令

```java
adb shell am start -n "com.tcl.tv.plus/com.tcl.tv.tclchannel.ui.policy.PolicyActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER
```

## ADB 查看窗口信息
```shell
// 查看窗口信息
adb shell dumpsys window w

// 查看整个窗口容器树的描述
adb shell dumpsys activity containers
```

## ADB 设置模拟器时间
```shell
adb shell date // 显示模拟器当前时间
adb shell date "+%Y-%m-%d %H:%M:%S"  // 格式化显示当前时间
adb shell date -s "YYYY-MM-DD HH:MM:SS" // 设置模拟器时间 adb shell date -s "2024-04-03 12:00:00"
adb shell su 0 date -s "2024-04-03 10:01:00" // 以超级用户身份执行命令
```