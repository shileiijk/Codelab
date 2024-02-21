# Debug Log

```shell
// 启动日志
ActivityManager: START u0|ActivityManager:|libprocessgroup:|system_server: Background concurren`t

MediaFocusControl: |



// Favorite 为空场景，CA片单无法播放
tag~:FavoriteEmptyFragment|PlayerActivity|AbstractPlayerFragment|PlayerManager|PLAYERIMG|LiveTVPlayerFragment
```

## HDC
```java
// 安装、更新，多HAP可以指定多个文件路径
hdc install C:\entry.hap C:\feature.hap
// 执行结果
install bundle successfully.
// 卸载
hdc uninstall com.example.myapplication
// 执行结果
uninstall bundle successfully.


// 先执行hdc shell才能使用bm工具
hdc shell
// 安装、更新，多HAP可以指定多个文件路径
bm install -p /data/app/entry.hap /data/app/feature.hap
// 执行结果
install bundle successfully.
// 卸载
bm uninstall -n com.example.myapplication
// 执行结果
uninstall bundle successfully.

$ hdc shell am force-stop com.shilei.ijk.ohsshell
$ hdc shell bm uninstall com.shilei.ijk.ohsshell
$ hdc file send D:\shilei\code\huawei\ohsshell\entry\build\default\outputs\default\entry-default-unsigned.hap /sdcard/entry-default-unsigned.hap
$ hdc shell bm install -p /sdcard/04daa65d4e3347fca74b32c930f1b373/
$ hdc shell rm -rf /sdcard/04daa65d4e3347fca74b32c930f1b373
$ hdc shell aa start -a EntryAbility -b com.shilei.ijk.ohsshell
```