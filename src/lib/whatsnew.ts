// Hand-written highlights of the current release for the home page's "What's new" block. Update
// it with each release, from that release's news post.

export type NewsItem = { title: string; detail: string };

export const whatsNew: NewsItem[] = [
  { title: "Torrenta 0.3.0", detail: "Out for Linux (x64), macOS (Apple Silicon) and Windows (x64), each installer with its own Java runtime." },
  { title: "Sonarr, Radarr and a phone", detail: "Remote access speaks qBittorrent's web API, signed in always, and serves a web UI such as VueTorrent; <code>torrenta-nox</code> runs it all without a window." },
  { title: "Bring your torrents over", detail: "File → Import from another client… takes qBittorrent's, Transmission's and Deluge's torrents at their progress, without a check." },
  { title: "Look before you add", detail: "The Add dialog flags a <code>Film.mkv.exe</code>, says whether the swarm is healthy, and links files you already have instead of downloading them." },
  { title: "Keys for many at once", detail: "Saved views, <code>&gt;set-category Films</code> over every match, Ctrl+Z for the last ten actions, and shortcuts you can change." },
  { title: "Protocol extras", detail: "Play a file while it downloads, follow or publish updatable torrents (BEP 46), slow down on battery, and a leak check for a VPN." },
];
