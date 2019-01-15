#!/bin/bash
set -euo pipefail
cd /usr/share/mastodon-exporter
screen -S mastodon-exporter -rX stuff $'\003'
exit 0