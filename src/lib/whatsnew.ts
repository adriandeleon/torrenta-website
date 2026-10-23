// Hand-written highlights of the current release for the home page's "What's new" block. Update
// it with each release, from that release's news post.

export type NewsItem = { title: string; detail: string };

export const whatsNew: NewsItem[] = [
  { title: "The first release", detail: "Torrenta 0.1.0 is out for Linux (x64), macOS (Apple Silicon) and Windows (x64): installers with their own Java runtime, nothing else to install." },
  { title: "Search built in", detail: "Prowlarr or Jackett over Torznab, or a definition file per site; every provider asked at once, results as they arrive, the same torrent shown once." },
  { title: "RSS with auto-download rules", detail: "Feeds refreshed on a timer, qBittorrent-style rules with episode filters, and a live list of what a rule matches as you type it." },
  { title: "A Transmission daemon as the engine", detail: "Drive a seedbox or a NAS from the same window; what the daemon cannot do is hidden rather than greyed out." },
  { title: "Create torrents", detail: "Hybrid, v1 or v2, piece size picked for you, trackers by tier, web seeds; seeds the moment it is made." },
  { title: "Six languages", detail: "English, Deutsch, Español, Français, Italiano and Português, every string included." },
];
