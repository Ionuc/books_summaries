

DNS
===

    - when searching for an address, DNS is looking:
        - local machine hosts file
        - local DNS Cache
        - DNS server

Root Hints
----------
    - used for one DNS server to redirect to another DNS server closer to the DNS server containing the domain name

Authoritative vs Non-Autoritative
---------------------------------
    - Authoritative:
        - the ip of the domain exists in your DNS server
        - The reponse have "Authority RRs" with value 1
    - Non-Autoritative:
        - ip of the domain exists in another DNS server and are used root hints to find the corresponding DNS server
        - The reponse have "Authority RRs" with value 1

DNS Hierarchy
-------------
    - Root : "."
        -> ".com" (Top Level Domain)
        -> ".edu" (TLD)
            -> Berkeley (Second Level)
            -> MIT (Second Level)
        -> ".net" (TLD)
        -> ".mil" (TLD)
            -> Army (Second Level)
            -> Navy (Second Level)
        -> ".org" (TLD)

DNS Zones
---------
    - Farward Lookup Zones
        - host name to ip address resolution
        - contains authoritative data
    - Reverse Lookup Zones
        - IP address to host name resolution

Resource Records
----------------
    - A or AAAAA
        - A is an IPV4 record
        - AAAA - is an IPV6 record
    - PTR : Pointer Record:
        - records used in the Reverse Lookup Zone
    - CName
    - SRV
    - MX
    - NAPTR
    