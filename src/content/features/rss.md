---
title: "RSS and auto-download rules"
group: "Finding torrents"
order: 2
beta: false
summary: "A feed reader with unread counts, and rules in qBittorrent's syntax: must and must-not words, regex, <code>1x2-5</code> episode filters and a smart filter that never takes an episode twice."
---

The RSS tab (`Ctrl+R`, or `Ctrl+3`): feeds on the left with their unread counts, the chosen feed's articles on the right, the article's text below.

- **Feeds** are added by URL (`N`), named after themselves, refreshed every half hour by default (each may have its own interval), and only re-read when they changed: `ETag` and `Last-Modified` are sent back.
- **`Enter` on an article** with a torrent (an enclosure, a magnet, a `torrent:magnetURI`) opens the Add dialog; Download adds it at once with the defaults.

**Rules** add new articles' torrents by themselves once the switch is on:

- what the title must contain and must not contain: words, `|` between alternatives, or regular expressions;
- an **episode filter** in qBittorrent's syntax: `1x2` one episode, `1x2-5` a range, `1x2-` from episode 2 on, several joined by `;`, read off `S01E02`, `1x02` or "Season 1 Episode 2";
- a **smart episode filter** that keeps a rule from taking the same episode twice;
- which feeds, how old is too old, and what to do with a match: start paused, a category, a save path.

The rule dialog lists what the rule matches among the articles as you type. "Create rule from title…" starts one from an article.
