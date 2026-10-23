---
title: "libtorrent inside"
group: "Engine & network"
order: 1
beta: false
summary: "The engine behind qBittorrent and Deluge: v2 and hybrid torrents, uTP, DHT, PEX, local discovery, encryption, proxies, and resume data that survives a crash."
---

Torrenta runs [libtorrent](https://www.libtorrent.org) 2.x in its own process, through [libtorrent4j](https://github.com/aldenml/libtorrent4j). Parity with qBittorrent is a matter of exposing what the engine already does, not of writing protocol code.

- **v1, v2 and hybrid torrents**, with both info-hashes shown and copied.
- **uTP, DHT, PEX, local service discovery** and **protocol encryption**, each a switch under Settings → Protocol, plus anonymous mode and default trackers added to every torrent.
- **Connection**: the listening port, UPnP and NAT-PMP port mapping, IPv6, a network interface to bind to, connection limits, and SOCKS or HTTP proxies, with a password if needed and host names looked up through the proxy.
- **Resume data** written on add, on metadata, on finish, every two minutes while anything changed and at quit, so a crash loses next to nothing; a magnet survives one even before its metadata arrives.
- **Honest connectivity**: the status bar calls the port open only once a peer from outside has reached it.

The installers carry the engine's native library for Linux x64, macOS on Apple Silicon and Windows x64, the platforms libtorrent4j builds for.
