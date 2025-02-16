#!/bin/sh

# get real path of softlink
get_real_path() {
    local f="$1"
    while [ -h "$f" ]; do
        ls=`ls -ld "$f"`
        link=`expr "$ls" : '.*-> \(.*\)$'`
            if expr "$link" : '/.*' > /dev/null; then
            f="$link"
        else
            f=`dirname "$f"`/"$link"
        fi
    done
    eval "$2"="'$f'"
}

get_real_path "$0" prg_path
echo "Script path [$prg_path]"
PROJECT_HOME=$(cd $(dirname $prg_path)/.. && pwd)
echo "Project home [$PROJECT_HOME]"

# 添加调试信息
echo "Checking if hooks directory exists:"
ls -la $PROJECT_HOME/.git/hooks
echo "Source directory contents:"
ls -la $PROJECT_HOME/.git-hooks/

# 使用绝对路径进行复制
cp -v $PROJECT_HOME/.git-hooks/* $PROJECT_HOME/.git/hooks/

