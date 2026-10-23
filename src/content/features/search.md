---
title: "Search"
group: "Finding torrents"
order: 1
beta: false
summary: "Ask your Prowlarr or Jackett, and sites described by a small definition file, all at once. Results land as each answers, the same torrent shown once, best seeded first."
---

The Search tab (`Ctrl+Shift+F`, or `Ctrl+2`) takes the place of the transfer list until `Esc` brings it back.

- **Torznab providers**: add your Prowlarr or Jackett by URL and API key under Settings → Search, and press Test.
- **Definition files** for sites without an API: one JSON file per site in the config folder says where to search and how to read the answer, by JSON pointer or CSS-like selector. One generic provider runs them, so a site's change is an edit, not a release. Three examples ship: Internet Archive, LinuxTracker and Academic Torrents.
- **All at once.** Every enabled provider is asked in parallel; each answer lands as it arrives, with a line per provider saying how many it found or why it could not answer.
- **One row per torrent.** The same torrent from two providers is shown once, the better-seeded copy kept; results sort by seeds unless you click a column.
- **Polite.** One request per two seconds per provider, and an answer reused for five minutes.

`Enter` on a result opens the Add dialog for it, fetching a `.torrent` link first; `Ctrl+C` copies its link.
