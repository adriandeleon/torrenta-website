---
title: "IP filter"
group: "Engine & network"
order: 3
beta: false
summary: "A blocklist URL refreshed on a schedule (PeerGuardian <code>.p2p</code>, eMule <code>.dat</code>, CIDR), a local list, and peers banned from the Peers tab."
---

Settings → IP filter builds one filter from three sources:

- **a blocklist URL**, fetched on a schedule and cached, in PeerGuardian `.p2p`, eMule `.dat`, CIDR or plain-range format, gzipped or not;
- **a local file** in the same formats;
- **manual bans**, added on the page or with Ban Peer from a torrent's Peers tab.

The filter is rebuilt whole each time and set before the session starts, so no peer slips in while the first download runs. Blocked connections are counted. Addresses are read as literals and never looked up.
