---
title: "Swarm preview"
group: "Adding & creating"
order: 6
beta: false
summary: "A Swarm line in the Add dialog says Healthy, Thin, Incomplete or Dead before you add, with the counts, and follows the files you tick."
---

Whether a torrent will finish is worth knowing before it sits in the list for a week. The Add dialog looks at the swarm while you choose.

- **A verdict under the free space**, with the seeds and peers it counted:
  - **Healthy**: three seeds and up.
  - **Thin**: a seed or two, or no seed but every piece among the peers.
  - **Incomplete**: no seed, and pieces of the chosen files that no peer has.
  - **Dead**: no one at all.
- **It follows your choice.** Untick the file nobody has, and an Incomplete swarm can become Thin.
- **At once for a `.torrent`**, and for a magnet once its file list is in.
- **Nothing downloaded.** It reads the trackers' counts and the peers it meets, and gives no time estimate: there is no rate to measure one from, and a guess shown as a number reads as a measurement.
- **Private torrents are left alone**, since a private tracker counts every announce, unless **Settings → Adding torrents → Look at a private torrent's swarm** is on.

The preview needs libtorrent; with a remote Transmission daemon as the engine the line is not shown. See [Adding torrents](/docs/adding-torrents#the-swarm-line).
