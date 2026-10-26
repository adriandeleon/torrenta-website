---
title: "Remote access: Sonarr, Radarr and web UIs"
description: Torrenta speaks qBittorrent's WebUI API, for the *arr apps, phone apps and web interfaces such as VueTorrent. How to switch it on safely and connect them.
category: Engine & network
order: 12.5
---

**Settings → Remote access** makes Torrenta answer qBittorrent's WebUI API at `/api/v2`, at the
level of qBittorrent 5.0. Anything written for qBittorrent connects to it as it is:

- **Sonarr, Radarr, Lidarr, Readarr and Prowlarr**, as a download client.
- **Remote apps** on a phone made for qBittorrent.
- **Web interfaces** written for qBittorrent's API, such as VueTorrent, served by Torrenta itself.

This is not the [remote engine](/docs/remote): there, Torrenta's window drives a Transmission
daemon elsewhere. Here, other programs drive Torrenta.

## Switching it on

On **Settings → Remote access**:

| Row | Does |
| --- | --- |
| **Remote access** | The switch. Off by default |
| **Address** | `127.0.0.1` (the default) is this computer alone. One of this machine's addresses opens it to that network; `0.0.0.0` to every network it is on |
| **Port** | `8080` by default, qBittorrent's, the one the apps try first. A port in use is said, with a free one offered |
| **Names** | The names the apps reach this machine by, besides its addresses and `localhost` |
| **Allowed folders** | Where the apps may put files, besides the download folder and the categories' folders |
| **Web interface folder** | A web UI to serve at the server's address |
| **User name**, **Password** | What the apps sign in with. With no password set, the server does not start |
| **Certificate**, **Certificate password** | A PKCS#12 file (`.p12`, `.pfx`) for HTTPS. Empty is plain HTTP |

The **Status** row says where it is listening, or why it is not. All of these are this machine's
and never sync.

## The doors

Remote access is the widest door Torrenta has, so it is shut by default and narrow when open:

- **Sign-in always**, from every address, this machine's included: there is no localhost bypass.
  The password is kept only as a salted PBKDF2 hash, on this machine. A new password signs
  everyone out. Five wrong tries in a row ban an address for an hour.
- **Only this server's names.** A request must name this server in its `Host` header (an address,
  `localhost`, or a name under **Names**), so a website cannot reach it through DNS rebinding; a
  browser's `Origin` must be this server, so a page elsewhere cannot use a signed-in browser's
  cookie. The cookie is `HttpOnly` and `SameSite=Strict`.
- **Files go only where they are allowed.** A save path, a new location or a category's folder
  outside the download folder, the categories' folders and the **Allowed folders** is refused;
  links out of them and `..` are followed and refused. Links added through the API are magnets
  and http(s) links only, never a path on this machine.
- **The door's own settings** (the password, address and port) are changed only on this page,
  never through the API.
- **Plain HTTP to other machines gets a warning**: the password and the session would cross the
  network readable. Choose a certificate, keep the address at `127.0.0.1`, or put a TLS proxy in
  front.

## Connecting Sonarr or Radarr

In the app: **Settings → Download Clients → + → qBittorrent**. Give it Torrenta's host and port,
the user name and password, and a category (`tv-sonarr`, `radarr`). Test, then save.

- The category is created in Torrenta on the first grab. Give it a folder in
  [Settings → Categories and tags](/docs/queue-and-limits#categories-and-tags) if its downloads
  should land somewhere other than the download folder.
- **Remove Completed** works as with qBittorrent: once a torrent has reached Torrenta's
  [share limit](/docs/queue-and-limits#share-limits) and stopped, the app removes it with its
  files, having imported them by then. Torrenta moves the files to the trash, as its own Remove
  with files does.
- When the app runs in a container, mount the download folder at the same path inside, or map
  it under the app's Remote Path Mappings: the path the app is told is Torrenta's.

This was tried with Sonarr 4.0 and Radarr 6.4 through a whole grab, import and removal after
seeding.

## A web UI for a phone

Unpack a web UI written for qBittorrent's API (VueTorrent's release, say) into a folder and
choose it as the **Web interface folder**. It is served at the server's address, the way
qBittorrent serves an alternative WebUI: its public half (the login page) to anyone, its private
half only after signing in; nothing outside the folder, hidden in it, or behind a link out of it.
A phone's browser on the same network then runs Torrenta, given an address it can reach (and,
over the network, a certificate).

## What is spoken

The list and its changes (`torrents/info`, `sync/maindata`), a torrent's properties, files,
trackers, web seeds, piece states and peers; adding (magnets and links in `urls`, uploaded
`.torrent` files, with category, tags, save path, layout, rename, limits and share limits) through
the same [content check](/docs/adding-torrents#the-content-check) as any add without a dialog;
delete, stop and start, recheck, location, names, file priorities, trackers, limits, sequential,
first and last pieces, super seeding, the queue, force start, share limits; categories and tags;
the session's speeds and limits, the alternative limits, the preferences Torrenta has, and the
event log.

Not spoken: qBittorrent's `search/*` and `rss/*`, and the few edits with no Torrenta counterpart.
Anything else under `/api/` answers 404, which clients read as "not supported". Downloads from
links are not torrents and are not listed. A torrent is listed by its v1 hash where it has one,
since that is what the apps compute from what they sent.

Every answer is held to a real qBittorrent 5.1.0's, field by field, by a test that runs with every
build. The endpoints are listed in the app's
[docs/webapi.md](https://github.com/adriandeleon/torrenta/blob/master/docs/webapi.md).

On a server with no window, see [Headless](/docs/headless) for the same settings in
`settings.properties` and setting the password from the command line.
