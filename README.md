# mastodon-prometheus-exporter [![Build Status](https://travis-ci.org/lfuelling/mastodon-prometheus-exporter.svg?branch=master)](https://travis-ci.org/lfuelling/mastodon-prometheus-exporter)

Application that scrapes Mastodon instances when scraped by Prometheus.

## Configuration

Create a file called `exporter.properties` in your workdir (which is ideally where the jar is).

### Full config example (with defaults)

```
port=9534
host=localhost
target=https://localhost:3000
interval=1
```

- **port**: The port the exporter runs on
- **host**: The host/ip the exporter runs on
- **target**: The target mastodon instance
- **interval**: The polling interval in seconds 

## Usage

1. Download/Compile jar
2. Create config file
3. Run jar
4. Add target in prometheus