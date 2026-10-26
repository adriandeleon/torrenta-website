---
title: "Updatable torrents"
group: "Adding & creating"
order: 9
beta: false
summary: "Follow a BEP 46 link and get every new version as it is published, unchanged files linked rather than downloaded; or publish one of your own."
---

A torrent is fixed once it is made. An updatable torrent (BEP 46) is a name in the DHT, signed by its publisher, that points at the current version, so a dataset, a distribution or a collection can grow without a new link each time.

- **Follow one.** A `magnet:?xs=urn:btpk:…` link, typed, opened, dropped into a watch folder's `.magnet` file or found in an RSS feed, is looked up in the DHT and its current version added.
- **New versions arrive by themselves.** Torrenta looks again every six hours (**Settings → Adding torrents → Look for newer versions every**) and adds a newer version into the same folder, filed the same way, with the files that did not change [linked rather than downloaded again](/features/dedup).
- **The old version lets go.** Once the new one is complete the old one is no longer followed, and leaves the list, its files kept, if you choose.
- **Automation knows.** Settings → Automation can run a program or notify on a new version.
- **Publish one.** The Create Torrent dialog's **Publish as updatable** makes a key under a name you choose, seeds the torrent and puts it in the DHT; the updatable link it shows is what others follow. **Torrent → New Version…** creates the next version under the same key, and **Copy Updatable Link** gives the link again.

The key is `keys/<name>.key` in Torrenta's folder, on this computer only and never synced: anyone with it can publish versions, and without it there will be no more, so keep a copy. While Torrenta runs it puts the link back in the DHT every hour when it is no longer found there. See [Updatable torrents](/docs/updatable-torrents).
