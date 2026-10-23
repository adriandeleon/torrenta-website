---
title: "Statistics"
group: "Watching & managing"
order: 5
beta: false
summary: "This session's and all-time totals, ratio, overhead, wasted bytes, peers, DHT nodes and disk activity, over a speed graph of the last minute, ten minutes, hour or day."
---

The Statistics window (`Ctrl+Shift+T`) refreshes every second while it shows:

- **downloaded, uploaded, share ratio and active time**, for this session and for all time;
- bytes with **protocol overhead**, and **wasted** bytes;
- **peers** connected and connecting, **DHT nodes**;
- **disk** reads, writes, buffers and queued jobs;
- the torrents **by state**.

Every number comes from libtorrent's own session counters, read without a round trip to the engine. Under them, the session's **speed graph**: download and upload over the last minute, ten minutes, hour or day, with the rate now and the peak in the legend. Each torrent has the same graph on its Speed tab, and the status bar a minute's sparkline beside each rate.
