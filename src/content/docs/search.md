---
title: Search
description: Search through your Prowlarr or Jackett server and through definition files, and add a result in one key.
category: Finding torrents
order: 8
---

Torrenta's search asks providers you set up; it ships no site-specific code. A provider is
either a **Torznab server** (Prowlarr or Jackett, which put hundreds of indexers behind one URL)
or a **definition file** for a site without an API.

## Adding a Torznab provider

Open **Settings → Search** and press **Add…** under **Search providers**. The dialog takes:

- **Name**: leave it blank and Test fills it in with the server's own title.
- **URL**:
  - Prowlarr: `http://host:9696/1/api`. The API key is under Prowlarr's Settings → General.
  - Jackett: `http://host:9117/api/v2.0/indexers/all/results/torznab/api` asks every indexer at
    once; put one indexer's id in place of `all` to ask only that one.
- **API key**: blank when the server asks for none.
- **Enabled**: whether it is asked when you search.

**Test** asks the server who it is and how many categories it knows, both in the dialog and on
the page. The page lists each provider with its URL and the category count the last Test found.

The provider list, API keys included, is `search-providers.json` in the config folder. It stays
on this machine and never syncs, because the key is a secret.

## The Search tab

`Ctrl+Shift+F` or `Ctrl+2` (also **View → Search…**) shows the Search tab in place of the
transfers, with the query focused. `Esc` returns to the transfers as they were.

- **The query.** Type and press `Enter`.
- **Category chips**: All (the default), Movies, TV, Music, Games, Software, Books, Anime,
  Other.
- **Provider chips**: every enabled provider, on unless you switch it off. While there is none,
  the tab links to Settings → Search.
- **The status line**: one clause per provider, such as "Prowlarr: 42 results · Jackett: could
  not connect".
- **The results**: Name, Size, Seeds, Leechers, Date and Provider.

`Enter` asks every chosen provider at once. Each answer lands in the table as it arrives, and
the rows are updated in place so the selection survives. A new search drops the one still
running.

The same torrent from two providers is shown once (matched by info-hash, else by link), with the
better-seeded copy kept. Results are ranked by seeds, then by how many of the query's words the
name contains, then by date, until you click a column to sort by it. The table's columns are
remembered.

### Adding a result

`Enter` or a double-click on a result opens the [Add dialog](/docs/adding-torrents) for it,
marked "FROM SEARCH". A magnet goes in as it is; a `.torrent` link is fetched first, and the
status bar says so. `Ctrl+C` copies the result's link. The right-click menu has **Add…**, **Copy
Link**, **Copy Magnet Link** and **Open Detail Page**.

### Limits and caching

Each provider gets 20 seconds to answer and is asked at most once every two seconds. An answer
is reused for five minutes; after that the same search asks again.

## Definition files

A site with a JSON API or a stable HTML listing can be a **definition file**:
`search-providers/<id>.json` in the config folder. The file says where to search (a URL with
`{query}` and `{category}`, optional paging and headers) and how to read the answer (JSON paths
or CSS-like selectors, one per field). One generic provider interprets every file, so when a
site changes, the fix is an edit to the file, not a new release.

Three examples ship and are copied into the folder on the first run:

- **Internet Archive**: its advanced search, read as JSON.
- **LinuxTracker**: its HTML listing.
- **Academic Torrents**: its feed, filtered locally.

An example you have edited is never written over; the shipped version goes beside it as
`<id>.json.example`. Delete an example and it comes back at the next start, so switch it off
instead to be rid of it.

**Settings → Search → Definition files** lists the files with their type and status (loaded, or
why not), with **Enable / Disable**, **Reload definitions** and **Open folder**. The folder is
also re-read when the Search tab opens, so a file you drop in is a provider chip at once. Which
files are switched off is a setting that syncs. A file that does not load is reported once in
the Event Log, with the key at fault; unknown keys are errors, so a typo cannot pass silently.

The format, field by field, is in
[docs/search-providers.md](https://github.com/adriandeleon/torrenta/blob/master/docs/search-providers.md).

## Keys in the tab

| Key | Where | Does |
| --- | --- | --- |
| `Enter` | Query | Search |
| `Tab` | Anywhere | Move between the query, the chip rows and the table |
| `Space` | A chip | Toggle it |
| `Down` | Query | Into the results |
| `Enter` | Results | Open the Add dialog for the result |
| `Ctrl+C` | Results | Copy its link |
| `Esc` | Results | Back to the query |
| `Esc` | Query | Back to the transfers |
