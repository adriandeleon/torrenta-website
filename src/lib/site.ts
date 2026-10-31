// Where the site is served: the apex domain torrenta-project.dev (www. redirects to it), at the
// root, so `base` is "/". The host is also in public/CNAME, public/robots.txt and the social
// cards (scripts/gen-images.mjs), and set as the custom domain under the repository's
// Settings → Pages. Keep href() and `base`: moving again changes this file, not the templates.
export const site = "https://torrenta-project.dev";
export const base = "/";

/**
 * A root-relative path ("/docs/search", "/#features", "og/docs.png") as it is served under
 * `base`. Every internal link and asset in a template goes through this; links in Markdown are
 * rewritten the same way at build time (astro.config.mjs). External URLs pass through unchanged.
 */
export function href(path: string): string {
  if (/^[a-z]+:/i.test(path) || path.startsWith("//")) return path;
  const joined = `${base}/${path.replace(/^\/+/, "")}`;
  return joined.replace(/\/{2,}/g, "/");
}
