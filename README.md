# mastodon-prometheus-exporter

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

## Usage

1. Download/Compile jar
2. Create config file
3. Run jar
4. Add target in prometheus