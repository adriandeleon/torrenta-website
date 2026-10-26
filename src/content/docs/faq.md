---
title: FAQ
description: Licensing, data collection, platforms, signing, how it compares, remote use, where data lives, paused versus queued, the port state, v2 torrents and bug reports.
category: Help
order: 15
---

## Is Torrenta free and open source?

Yes. It is free and released under the MIT License. The source is on
[GitHub](https://github.com/adriandeleon/torrenta).

## Does it collect data or phone home?

No accounts, no telemetry, no analytics. The one request Torrenta makes on its own is the update
check: after startup, at most once a day, it asks GitHub whether a newer release exists, and
sends nothing about you or the machine beyond the request itself. Untick **Settings → General →
Updates → Check for updates**, or set `updates.check=false` in the settings file, to turn it off.

Everything else that reaches the network is something you set up: the torrents themselves, your
search providers, your RSS feeds, a blocklist URL, a Transmission daemon,
[settings sync](/docs/settings#settings-sync) to a Git repository you own, and the
[leak check's](/docs/connection#the-leak-check) address-echo server, which is off until you turn
it on.

## Why is there no Intel Mac or ARM Linux build?

The engine is libtorrent through libtorrent4j, whose native library exists for macOS on Apple
Silicon, Linux x64 and Windows x64 only. Those are the three builds. On another platform the
engine fails to start with a message that says so, and the window stays up.

The installers bundle their own Java runtime, so you do not need Java installed.

## The installer is unsigned. How do I open it?

The builds are not signed yet. On macOS, the first launch is refused; allow it once under
**System Settings → Privacy & Security → Open Anyway**. On Windows, SmartScreen may warn on the
first run; let it continue. Signed installers are one of the criteria for 1.0. Each release lists
its files' checksums in `checksums_sha256.txt`.

## Can it replace qBittorrent or Transmission?

That is the goal for 1.0. The [roadmap](https://github.com/adriandeleon/torrenta/blob/master/docs/ROADMAP.md#parity-matrix)
has a parity matrix comparing Transmission 4.1, qBittorrent 5 and Torrenta feature by feature.
Torrenta uses the same engine as qBittorrent, so most of the work is exposing what libtorrent
already does. It also speaks qBittorrent's web API, so the tools built around qBittorrent work
with it, and [imports](/docs/importing) qBittorrent's, Transmission's and Deluge's torrents
without a check. A few things are deliberately left for later, such as a port test and email
notifications.

## Does it have a web UI?

Not one of its own, but it can serve one. [Remote access](/docs/remote-access) speaks
qBittorrent's WebUI API, so a web interface written for qBittorrent, such as VueTorrent, runs
against Torrenta from a phone's browser, and Sonarr, Radarr and the phone apps made for
qBittorrent connect to it. It is off until you switch it on, and always asks for a password.

## Can it run on a server without a window?

Yes: `torrenta --headless`, or the `torrenta-nox` launcher packaged beside the app, runs
everything but the window. See [Headless](/docs/headless), with a systemd unit.

## Can it control a seedbox?

Yes, if the seedbox runs `transmission-daemon` 4.0 or later. Add a profile in **Settings →
Engine** with the daemon's URL, user and password, and switch to it. What the daemon cannot do
is hidden rather than faked. See [Remote: a Transmission daemon](/docs/remote).

## Where are my settings and torrents stored?

In `~/.torrenta` on every platform. `settings.properties` holds the settings and can be edited
by hand; `torrents/` holds one resume file per torrent with the torrent's info inside, so a
torrent comes back after a restart, seeding without a recheck if it was complete. Downloads
themselves go to `~/Downloads` unless you change the folder. The full list of files is in
[Settings, themes and sync](/docs/settings#the-files-torrenta-keeps).

## How do I move the config folder?

Start Torrenta with `--config-dir DIR`, or set the `TORRENTA_CONFIG_DIR` environment variable.
Each config folder is its own Torrenta: one instance runs per folder, with its own torrents and
settings.

## What is the difference between Paused and Queued?

"Paused" means paused by a person: you pressed `Space` or Pause, and the row says "Paused by you".
The queue never resumes a torrent you paused. "Queued" means the queue limits on **Settings →
Queue and share limits** are holding it back; the row says its place and that it starts when a
slot frees. **Force Resume** (`Ctrl+Shift+U`) starts a queued torrent now.

## Why does the port say "checking"?

Torrenta calls the port **open** only once a peer from outside your network has connected to it,
and that needs a running torrent. Until then it reads "checking reachability". After five minutes
of running torrents with no such peer it reads **blocked**, which usually means a firewall or the
router. Downloads still work either way, with fewer peers. See
[Connection](/docs/connection#what-the-port-state-means).

## Does it support v2 torrents?

Yes. It adds v1, v2 and hybrid torrents (the Add dialog's header says which), and the
[New Torrent dialog](/docs/creating-torrents) makes all three, hybrid by default, showing both
info-hashes.

## How do I report a bug?

Open an issue at [github.com/adriandeleon/torrenta/issues](https://github.com/adriandeleon/torrenta/issues).
Include what you did and what happened, the details from **Help → About** (**Copy details**
gives the build, engine, Java, JavaFX, OS and config folder), and the log: **View → Debug Log**
(also **Settings → General → Diagnostics**) shows this session's warnings, errors and uncaught
exceptions with Copy. The same lines are in `torrenta-session.log` in the config folder, which a
crash leaves behind.

## Is it legal to use?

BitTorrent is a way of moving files, and Torrenta is a tool for it, like any other client. What
matters is what you download and share: share what you have the right to share. The search
examples Torrenta ships point at public, non-infringing indexes such as the Internet Archive.

Didn't find your answer?
[Open an issue](https://github.com/adriandeleon/torrenta/issues).
