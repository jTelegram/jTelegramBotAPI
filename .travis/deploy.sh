#!/bin/bash

set -e

# only do deployment, when travis detects a new tag
if [ ! -z "$TRAVIS_TAG" ]
then
    echo "on a tag -> set pom.xml <version> to $TRAVIS_TAG"
    mvn --settings .travis/settings.xml org.codehaus.mojo:versions-maven-plugin:2.3:set -DnewVersion=$TRAVIS_TAG -Prelease

    if [ ! -z "$TRAVIS" -a -f "$HOME/.gnupg" ]; then
        shred -v ~/.gnupg/*
        rm -rf ~/.gnupg
    fi

    source .travis/gpg.sh

    mvn clean deploy --settings .travis/settings.xml -DskipTests=true --batch-mode --update-snapshots -Prelease


    if [ ! -z "$TRAVIS" ]; then
        shred -v ~/.gnupg/*
        rm -rf ~/.gnupg
    fi
else
    echo "not on a tag -> keep snapshot version in pom.xml"
    mvn clean deploy --settings .travis/settings.xml -DskipTests=true -B -U
fi

if [ ! -z "$SSH_KEY" ]
then
    #mvn clean package
    # Setup deployment key
    echo $SSH_KEY | base64 --decode > $HOME/.ssh/id_rsa
    chmod 600 $HOME/.ssh/id_rsa

    cd jtelegrambotapi-core/target

    git clone -b gh-pages git@github.com:jTelegram/jTelegramBotAPI.git gh-pages
    cd gh-pages
    rm -rf *

    git config --global push.default simple
    git config user.name "Travis CI"
    git config user.email "travis@travis-ci.org"

    cp -rf ../apidocs/* .

    git remote add javadoc
    git add --all
    git commit -m "Deploy javadocs to GitHub Pages Travis build: ${TRAVIS_BUILD_NUMBER}" -m "Commit: ${TRAVIS_COMMIT}"

    #git merge --no-edit -s ours remotes/javadoc/ghpages

    git push --force --quiet

    rm -rf .git
fi
