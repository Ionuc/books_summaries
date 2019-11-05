.. _technical-optimization.-label:


Technical Optimization
======================
    - is improving the website as a hole
    - it looks for how the site is seen and understood by search engines
    - is important that the search engine to find improvements done for on-page optimization

Sitemap
-------
    - points search engines to pages on your site
    - there are 2 types:
        - HTML sitemap:
            - are easier to read & understand
            - are simple page containing links to pages inside the website
        - XML sitemap:
            - are created for search engines
            - can contain additional information like: last update, how ofthen are changes, how important is this page

Robots
------
    - is a protocol which specifies which pages should or should not be indexed
    - should be places inside the root of server
    - robots can choose to ignore this information
    - search engines tend to respect this file

Error codes
-----------
    - each error code has a status code kept in the http response
    - having pages which are 404 pages, but with status 200, will make search engine to index them, and too see them as duplicated pages

Redirect
--------
    - is a type of status code
    - is used to transfer users from old pages to new & updated pages
    - there are many types of redirect:
        - 301 => permanent redirect:
            - is recommended 301 to be used
            - pass wuthority from page to page
        - 302 => temporary 
            - passes little to no authority
        - 307 => the new 302
        - meta refresh:
            - does not present http status code
            - redirect is executed at page level instead of server itself
            - generally not recommended
    - is not recommended to chain redirecting because it will be lost the autorithy

404 pages bases practices
--------------------------
    - you should provide some navigation information like:
        - look & feel should be matched the rest of site
        - let user know they landed on correct side
        - display error message to limit user confusion
        - you can add some call to action, home page link, or a search link to the content.

:ref:`Go Back <seo-label>`.