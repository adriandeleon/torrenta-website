---
title: RSS and auto-download rules
description: Read torrent feeds in the RSS tab, and let rules add new episodes and releases by themselves.
category: Finding torrents
order: 9
---

## The RSS tab

`Ctrl+R` or `Ctrl+3` (also **View → RSS Feeds…**) shows the RSS tab in place of the transfers.
`Esc` returns.

- **Feeds** on the left: **All feeds** first, then each feed with its unread count, and a glyph
  while it refreshes or after it failed.
- **Articles** of the chosen feed on the right: title (bold while unread), date and feed, with a
  filter box and an **Unread only** toggle above them. An article whose torrent a rule or
  Download already added has a glyph before its title.
- **The article's text**, plain, with its link, below.

The article columns are remembered.

## Feeds

Add a feed with `N` in the tab, the **Add feed…** button, or **Settings → RSS → Feeds**. Give its
URL; a blank name takes the feed's own title. The feed is fetched once to check it. RSS 2.0 and
Atom are read.

An article's torrent is its enclosure, a magnet in its link, guid or text, a
`<torrent:magnetURI>`, or an info-hash element.

Feeds are refreshed every 30 minutes by default (**Settings → RSS → Refresh every (minutes)**,
5 to 1440), and a feed can have **its own interval**. `F5` refreshes the chosen feed and
`Shift+F5` every feed. An unchanged feed costs one request: the last answer's `ETag` and
`Last-Modified` go back, and the server answers "not modified". A failure becomes the feed's
status ("last error: the server answered 404").

New articles are merged by guid with their read state kept, up to 500 per feed; the oldest are
dropped.

## Reading and downloading

Choosing an article marks it read. `M` toggles read on the article, `Shift+M` marks the whole
feed read.

`Enter` or a double-click on an article with a torrent opens the
[Add dialog](/docs/adding-torrents) for it, marked "FROM RSS"; a `.torrent` link is fetched first.
The right-click menu's **Download** adds it at once with the defaults, no dialog. `Ctrl+C` copies
the torrent link or the page link. `Delete` in the feed list removes a feed after asking (the
torrents already added stay).

## Rules

A rule adds new articles' torrents by itself. Rules live under **Settings → RSS → Rules**, with
**Add…**, **Edit…**, **Remove** and **Duplicate**.

### What a rule wants

- **Must contain**: words that must all appear in the title, case-insensitive. `|` separates
  alternatives. Blank takes every title.
- **Must not contain**: words that rule a title out.
- **Regular expressions**: read both fields as regular expressions instead.
- **Episode filter**, in qBittorrent's syntax, read off `S01E02`, `1x02` or `Season 1 Episode 2`
  in the title. Blank takes every episode.
- **Smart episode filter**: an episode taken once is not taken again. The rule remembers what it took (the last thousand).
- **Ignore older than** a number of days; 0 for no limit.
- **Feeds**: which feeds it reads. None ticked means every feed, including those added later.

| Episode filter | Takes |
| --- | --- |
| `1x2` | Season 1, episode 2 |
| `1x2-5` | Season 1, episodes 2 to 5 |
| `1x2-` | Season 1, from episode 2 on |
| `1x1-4;2x1` | Season 1 episodes 1 to 4, and season 2 episode 1 |

A filter or a regular expression that does not read is said under the form, and **Save** is held
back until it does.

### What it does with a match

- **Start**: paused, started, or as the Add dialog's default says.
- **Category**: the category's folder is then the save path.
- **Save to**: a folder of its own. Blank means the category's folder, else the download folder.

### Matches as you type

The rule dialog lists every article the feeds hold that the rule would take, re-read as you
type. Articles already downloaded are listed too; the rule skips them.

### Running the rules

Turn on **Download new articles' torrents automatically** (above the rules). Then every refresh
runs the rules over the feed's new articles: the first enabled rule that wants an article takes
it, its torrent is added (a magnet as it is, a `.torrent` link fetched first), and the article is
marked downloaded and read. An article is never added twice. The Event Log says "Rule 'Linux
ISOs' added … from feed …", or why it could not.

**Run RSS Rules Now** (the File menu, the palette, and **Run rules now** on the page) goes over
every unread article not yet downloaded, whether the switch is on or not.

**Create Rule from Title…** in an article's right-click menu opens the rule dialog with the
title's words before its episode number already filled in.

## What is kept where

Feeds, articles and rules stay on this machine and never sync: `rss/feeds.json`,
`rss/articles/<id>.json` and `rss/rules.json` in the config folder. The refresh interval and the
auto-download switch are settings, and they sync.
