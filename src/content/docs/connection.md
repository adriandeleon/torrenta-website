---
title: Connection, privacy and the IP filter
description: The Connection, Protocol and IP filter pages, and what the status bar's port state means.
category: Engine & network
order: 11
---

These pages are in the **Engine** group of Settings (`Ctrl+,`). Like every setting, they apply
the moment you change them, at start and live.

## Connection

**Listening**

- **Port**, or **Random port**.
- **Map the port with UPnP / NAT-PMP**: asks your router to forward the port.
- **Listen on IPv6**.
- **Bind to**: an address or a network interface; blank means all interfaces. This setting stays
  on this machine.

**Limits**

- **Connections**: in all.
- **Connections per torrent**.
- **Upload slots**.

**Proxy**

- **Type**: None, SOCKS5 or HTTP.
- **Host** and **Proxy port**.
- **Username** and **Password**, for a proxy that asks for a login.
- **Use the proxy for peers**: trackers always go through the proxy; this sends peer
  connections through it too.

With a proxy set, host names are looked up through the proxy as well. The proxy password is
never synced.

There is no port test: libtorrent has none, and it needs an outside service.

## Protocol

**Finding peers**

- **DHT**: the distributed hash table, how magnets and trackerless torrents find peers.
- **Peer exchange (PEX)**: peers tell each other about more peers.
- **Local peer discovery**: peers on your local network.

**Transport**

- **uTP**.
- **Encryption**: Require, Prefer or Off.
- **Anonymous mode**: does not identify the client.

**Trackers**

- **Default trackers**, added to every torrent, new and existing, on a tier of their own. They
  are also what the [New Torrent dialog](/docs/creating-torrents) starts with the first time.

## IP filter

The IP filter refuses connections from the addresses it lists.

**Blocklist**

- **Filter peers**: the switch.
- **Blocklist URL**: a PeerGuardian (`.p2p`) or eMule (`.dat`) list, CIDR ranges, or plain
  addresses, one per line, gzipped or not. It is downloaded on the schedule and kept beside the
  settings. Lists larger than 200 MiB are refused.
- **Refresh every (hours)**, and **Download now**.
- **Local list**: a list file on this machine, in the same formats.

**Manual bans**

- **Banned addresses**: addresses you add by hand. **Ban Peer** in the details pane's Peers tab
  adds one from a connected peer.

The **Status** row says how many ranges are loaded, when, how many lines were skipped, and how
many peers were refused this session.

The filter is rebuilt from the downloaded list, the local file and the bans every time one of
them changes. A filter set before the engine starts is applied at start, so no peer slips in
while the first download runs. Addresses are taken as written; a host name in a list is never
looked up.

With a [Transmission daemon](/docs/remote), the daemon fetches the blocklist URL itself; the
local list, the manual bans and Ban Peer are hidden.

## What the port state means

The status bar shows the listening port with a dot and one of four words:

| State | Means |
| --- | --- |
| **open** | A peer from outside this network has connected, so others can reach the port |
| **checking reachability** | No outside peer has connected yet. That needs a running torrent; with none, there is nothing to check |
| **blocked** | No outside peer has connected in five minutes of running torrents: a firewall or the router is probably blocking the port. Downloads still work, with fewer peers |
| **not listening** | No network interface could listen on the port; another program may hold it. Pick another on the Connection page |

"Open" means exactly that and nothing less: a connection from an address on your own network,
CGNAT included, proves nothing. Changing the port starts the check over. The tooltip also says
whether the router forwarded the port through UPnP or NAT-PMP.

Next to it, **DHT** shows the number of nodes in the routing table, or "DHT off".
