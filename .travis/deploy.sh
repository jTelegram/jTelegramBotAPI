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

if [ ! -z "$SSH_KEY" ]
then
    # Setup deployment key
    echo $SSH_KEY | base64 --decode > $HOME/.ssh/id_rsa
    chmod 600 $HOME/.ssh/id_rsa

    cd jtelegrambotapi-core/target/apidocs
    git init
    git config user.name "Travis CI"
    git config user.email "travis@travis-ci.org"

    git remote add javadoc git@github.com:jTelegram/jTelegramBotAPI.git
    git add --all
    git commit -m "github pages update"

    #git merge --no-edit -s ours remotes/javadoc/ghpages

    git push --force --quiet javadoc master:gh-pages

    rm -rf .git
fi
