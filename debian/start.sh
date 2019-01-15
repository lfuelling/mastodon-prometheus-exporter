#!/bin/bash
cd /usr/share/mastodon-exporter
echo "cleaning leftover screens..."
screen -wipe
echo "starting mastodon-exporter screen..."
screen -dmS mastodon-exporter bash
screen -S mastodon-exporter -X stuff 'java -jar lib/mastodon-exporter.jar'$'\n'
echo "fetching pid..."
screen -ls | grep .mastodon-exporter | grep -oP "([0-9]*(?=.mastodon-exporter))" > current.pid
echo Current PID: $(cat current.pid)