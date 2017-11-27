##!/bin/bash
#
#set -e
#
## only do deployment, when travis detects a new tag
#if [ ! -z "$TRAVIS_TAG" ]
#then
#    echo "on a tag -> set pom.xml <version> to $TRAVIS_TAG"
#    mvn --settings .travis/settings.xml org.codehaus.mojo:versions-maven-plugin:2.3:set -DnewVersion=$TRAVIS_TAG -Prelease
#
#    if [ ! -z "$TRAVIS" -a -f "$HOME/.gnupg" ]; then
#        shred -v ~/.gnupg/*
#        rm -rf ~/.gnupg
#    fi
#
#    source .travis/gpg.sh
#
#    mvn clean deploy --settings .travis/settings.xml -DskipTests=true --batch-mode --update-snapshots -Prelease
#
#
#    if [ ! -z "$TRAVIS" ]; then
#        shred -v ~/.gnupg/*
#        rm -rf ~/.gnupg
#    fi
#else
#    echo "not on a tag -> keep snapshot version in pom.xml"
#    mvn clean deploy --settings .travis/settings.xml -DskipTests=true -B -U
#fi


cd jtelegrambotapi-core/target/apidocs
git init
git remote add javadoc git@github.com:jTelegram/jTelegramBotAPI.git

git config user.name "Travis CI"
git config user.email "travis@travis-ci.org"

git add --all
git commit -m ""
git merge --no-edit -s ours remotes/javadoc/ghpages
git push javadoc master:gh-pages
rm -r -f .git
