---
title: "Create torrents"
group: "Adding & creating"
order: 3
beta: false
summary: "Hybrid, v1 or v2, an automatic piece size, private, trackers by tier, web seeds and a source tag. Hashed on a worker with a Cancel, and seeded the moment it is made."
---

`Ctrl+N` opens the Create Torrent dialog. Drop a file or folder on it, or browse.

- **Format**: hybrid (the default, readable by every client), v1 only or v2 only.
- **Piece size**: Automatic aims at about 1,500 pieces, qBittorrent's rule, or choose one from 16 KiB to 32 MiB; the header shows the piece count for the size chosen, live.
- **Private**, a **comment**, a private tracker's **source** tag.
- **Trackers** one per line, with a blank line between tiers, pre-filled from your last creation or the default trackers.
- **Web seeds**, one URL per line.

Create… asks where to save the `.torrent`, then hashes on a worker with a progress bar and a Cancel and writes the file whole. The result shows the v1 and v2 info-hashes and the magnet link, each with a Copy button. With "Start seeding after creation" the torrent is in the list at once, trusted as complete and seeded from where the files are.

**Publish as updatable** makes the torrent an [updatable one](/features/updatable-torrents) (BEP 46): a key under a name you choose, the torrent seeded and put in the DHT, and a link others follow. **Torrent → New Version…** on it creates the next version under the same key.

The pieces are hashed in Java, bit for bit what libtorrent would produce, and tested against libtorrent's own hasher.
