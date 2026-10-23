// @ts-check
import { defineConfig } from "astro/config";
import sitemap from "@astrojs/sitemap";
import pagefind from "astro-pagefind";
import { site } from "./src/lib/site.ts";

// Served at the custom apex domain in src/lib/site.ts (public/CNAME says the same to GitHub
// Pages). No `base`: the site lives at the domain root, so asset and link paths are "/".
//
// Unlike Editora's site the docs are not versioned yet: no Torrenta release deep-links into them.
// When one does, port Editora's /docs/v-<version>/ routes and archive instead of inventing a scheme.
export default defineConfig({
  site,
  // pagefind() runs the Pagefind indexer inside `astro build` (so it works however CI invokes the
  // build) and serves the index in `astro dev`.
  integrations: [sitemap(), pagefind()],
});
