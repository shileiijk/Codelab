# Repo 

## 简介

Repo 是一个用于管理 Android 源代码的命令行工具。
Repo 工具用于在本地一个单独的工作空间workspace创建和管理多个 Git 仓库，并使用 Git 命令来操作这些仓库。


## 安装

## 使用

## 命令
```shell
repo start branch_name // 根据当前分支创建新的分支, 并切换到新分支

repo upload // 将当前分支的改动提交到远程仓库

repo abandon branch_name . // 放弃当前分支的改动

repo forall -c 'git status' // 显示当前分支的改动
```
