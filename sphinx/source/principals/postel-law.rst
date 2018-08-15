.. _postel-law-label:

Postel's Law
============

Overview
--------
    - or Robustness principle
    - is a very old principles
    - originally it was stated in designing network protocols
    - it says: "You should be very conservative in what you send, but you should be very liberal in what you accept"
    - networks are inherently faulty, so packets may be dropped or may arrive out of order
    - is important that the recipient of those packages is as liberal as possible in accepting the packages
    - on the other hand, it is very important when you send something, when you have output, to be as conservative as possible
    - conservative here means is that you as stirnctly as possible adhere to whatever contract that you have constituted with your client
    
:ref:`Go Back <principals-label>`.