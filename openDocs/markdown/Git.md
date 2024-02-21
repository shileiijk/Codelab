## Git 报错： Failed to connect to github.com port 443

```shell
// 1. 全局设置
// 配置 http 代理
git config --global --replace-all http.proxy http://127.0.0.1:7890
git config --global --replace-all https.proxy https://127.0.0.1:7890

// 配置 socks5 代理
git config --global --replace-all http.proxy socks5://127.0.0.1:7890
git config --global --replace-all https.proxy socks5://127.0.0.1:7890

// 2 只对Github代理（推荐）
// 使用socks5代理（推荐）
git config --global http.https://github.com.proxy socks5://127.0.0.1:7890
// 使用http代理（不推荐）
git config --global http.https://github.com.proxy http://127.0.0.1:7890

// 3 取消代理
git config --global --unset http.proxy 
git config --global --unset https.proxy
```

## Gradle 查看依赖树

```shell
./gradlew :app:dependencies
```

## Add exist repo to gitlab

```shell
git remote add origin_gitlab https://gitlab.com/*******.git
git branch -M main
git push -uf origin_gitlab main
```

## Gitlab pre-receive hook declined

```shell
git pull origin_gitlab main --allow-unrelated-histories
git merge origin_gitlab main
git push -uf origin_gitlab main
```

## Git 出现 Unlink of file 'app/version.property' failed. Should I try again? 报错
+ 解决方案
    + 关闭AndroidStudio 或者其他Git管理用软件，使用命令行操作