---
title: "Headless: Torrenta without a window"
description: Run Torrenta on a server or a NAS with torrenta-nox, set the remote-access password from the command line, and keep it running with a systemd user unit.
category: Engine & network
order: 12.7
---

`torrenta --headless` runs the same session the window does, with no window and without the
toolkit ever loading: on a server, a NAS, a machine reached over SSH. The packaged app has a
second launcher for it, **`torrenta-nox`** (`bin/torrenta-nox` beside `bin/Torrenta` on Linux; a
console program on Windows), which is `torrenta --headless` under another name.

```bash
torrenta-nox --config-dir /srv/torrenta 'magnet:?xt=urn:btih:…'
```

## What is different

- **Everything is added without asking.** There is no dialog to ask in: a `.torrent` path, a link
  or a magnet on the command line, one handed over by another Torrenta, a watch folder's, an RSS
  rule's, all go in with the settings' defaults. The
  [content check](/docs/adding-torrents#the-content-check) still applies, as **Flagged files when
  adding without a dialog** says (skip those files, by default). A link that is not a torrent goes
  to curl, as from the window.
- **The log is stdout.** One line per event, `HH:MM:SS LEVEL message`: the lines the window's
  Event Log shows, also written to `torrenta-session.log` in the config folder.
- **SIGTERM stops it cleanly**, and so does `Ctrl+C`: the engine stops as it does at Quit,
  waiting up to five seconds for the last resume data, so nothing re-checks on the next start. An
  engine that cannot start ends the process with status 1.

Everything else is the window's: the settings file, the watch folders, the RSS feeds and rules,
the IP filter, the speed schedule, share limits, run-on-event commands, and a remote engine
profile. Edit the settings with the window on the same config folder, or by hand while Torrenta
is stopped: `settings.properties` is a plain properties file, and hand edits are kept.

## One Torrenta per config folder

As with the window, the first Torrenta on a config folder holds it. A second one, headless or
not, hands its command line to the first and exits: run while the server is up,
`torrenta-nox --config-dir /srv/torrenta 'magnet:…'` adds the magnet to it and is gone. A
browser's magnet link reaches a headless Torrenta the same way.

## Remote access on a server

The *arr apps live on servers, and talk to Torrenta through [Remote access](/docs/remote-access).
With no window to type a password into, set it from the command line while Torrenta is stopped:
it is asked twice at a terminal, or read as one line from stdin. Only its salted hash is kept.

```bash
torrenta-nox --config-dir /srv/torrenta --set-remote-password
```

Then switch it on in `settings.properties` (every `webapi.*` key is this machine's and never
syncs):

```properties
webapi.enabled=true
# 127.0.0.1 until you choose: this machine alone. 0.0.0.0 is every network it is on.
webapi.address=127.0.0.1
webapi.port=8080
webapi.username=admin
# Names besides addresses and localhost that requests may name.
webapi.domains=nas.lan
# A web UI written for qBittorrent's API, served at /.
webapi.rootFolder=/srv/torrenta/vuetorrent
# Where the apps may put files besides the download and category folders, \n between them.
webapi.roots=/srv/media/tv\n/srv/media/films
# HTTPS from a PKCS#12 file; without one, keep to 127.0.0.1 or put a TLS proxy in front.
webapi.httpsFile=/srv/torrenta/cert.p12
webapi.httpsPassword=…
```

The start says `Remote access is on at http://127.0.0.1:8080/`, or why not (a port in use, no
password).

## A systemd user unit

`~/.config/systemd/user/torrenta.service`, for the packaged app in `/opt/torrenta`:

```ini
[Unit]
Description=Torrenta, headless
After=network-online.target
Wants=network-online.target

[Service]
ExecStart=/opt/torrenta/bin/torrenta-nox --config-dir %h/.torrenta
# SIGTERM is the clean stop; give the last resume data its time.
KillSignal=SIGTERM
TimeoutStopSec=20
Restart=on-failure

[Install]
WantedBy=default.target
```

```bash
systemctl --user daemon-reload
```

```bash
systemctl --user enable --now torrenta
```

```bash
journalctl --user -u torrenta -f
```

To keep it running while nobody is logged in, enable lingering for the user:

```bash
loginctl enable-linger "$USER"
```

A server behind a VPN can bind Torrenta to it too: `connection.interface` (Settings → Connection
→ Bind to, say `wg0`). The [leak check's](/docs/connection#the-leak-check) lines, a binding down
or missing and the address peers see, are in the log like any other.
