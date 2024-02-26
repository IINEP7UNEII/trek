# Trek
A location-based social network that helps you find people irl that you click with.

By CMPT 276 Group 12: Alex Chen, Daniel Tolsky, Huang Pan, Hamza Qayyum, Saarth Patel.

## Abstract
Trek is an innovative web application that transcends traditional location-based media sharing by allowing users to discover and share content tied to a location without it necessarily being about that location.

Differentiating itself from platforms like Google Maps, Snap Maps and AllTrails, Trek offers a unique feature where content shared is reflective of the interests of people at a specific location rather than the location itself. This gives users a window into the collective interests of a place, be it a university, a bustling city square, or a serene hiking trail. With Trek, we aim to create opportunities for users to connect with others in their vicinity, not by chance, but through shared passions and interests.

## Introduction
While existing platforms provide glimpses into the activities or sights of a location, Trek's mission is to connect people through the content they cherish. This could range from a thought-provoking article to a song that resonates with the spirit of a place. By mandating that users be physically present to post content, Trek ensures authenticity and a genuine reflection of the location's current vibe.

## Problem Statement
In a world where physical paths cross countless times a day, there is a missed opportunity for individuals to connect with those around them who share common interests.

Traditional social platforms have not capitalized on this aspect of connection; they often either operate on a global scale or focus too narrowly on specific location details without fostering personal interactions.

Trek seeks to fill this void by enabling individuals to discover and connect with like-minded people in their immediate surroundings through shared content, transforming fleeting proximity into lasting connections.

## Current Solutions
Platforms like Google Maps and Snap Maps provide location-based content sharing, but the content is often limited to being about the place itself. Trek innovates by curating a feed of diverse media shared by individuals in specific locations, reflecting a broader spectrum of interests that may or may not be related to the place they are pinned to, but are certainly reflective of the _people_ that inhabit that space.

## Proposed Solution

Trek is an innovative platform that integrates a content feed with geographic locations, redefining our interaction with our surroundings and each other. It allows users to contribute various types of content, including links, music, images, and videos, to specific map 'pins' but only when they are physically at the location. This ensures the authenticity of the content, reflecting the true interests of the local community. The platform's map showcases pins in a range of environments, from bustling urban areas to secluded hiking trails. While anyone can browse content from anywhere in the world, contributing is restricted to those physically present at a pin's location, maintaining the integrity and local relevance of the feeds. Moreover, Trek fosters discovery and connection among users with similar interests, facilitating serendipitous and meaningful relationships. It's a tool for finding "your people," connecting individuals who share similar tastes in media and passions.

## Target Audience
Trek is designed for anyone seeking to discover and connect with like-minded individuals in their immediate environment, from students and travelers to local residents and outdoor enthusiasts.

However, we do have a specific client as well: one of our group members' cousin was discussing how he's having difficulty making like-minded friends in university. This discussion with him was the inspiration for this project, and he is, thus, our specific client.

## Project Scope
The scope of Trek extends to a fully interactive web application featuring user authentication, location verification, real-time content sharing, and robust user profiles that foster connections based on shared content preferences.

We believe the scope is certainly large enough for the work to be split nicely amongst five group members.

## Features and Epics

Trek's platform is distinguished by its unique features and epics, which enhance the way we share and connect. It includes content-feed 'pins' positioned across various locations, ranging from the academic ambiance of universities to the serene landscapes of hiking trails. A crucial aspect of Trek is its location verification process, which guarantees that content is shared by users who are actually present at the specific location, ensuring the authenticity of the information. The feeds are diverse, encompassing a wide array of content such as books, songs, videos, articles, personal reflections, and links, essentially allowing for the sharing of any kind of content. Additionally, Trek emphasizes social connection through user profiles that facilitate connections based on shared interests in content, promoting a community of users who are engaged and connected by common tastes and preferences.

## User Stories
Some example use cases/user stories include a university student who may use Trek to share their favorite podcast episode on their campus pin, aiming to connect with fellow students who share similar interests.  Similarly, an avid hiker might post the music they listen to while exploring trails, sharing the soundtrack of their adventures with others who trek the same paths. This would create a unique, shared experience among hikers, connecting them through the ambiance of their journey. Additionally, individuals new to a city can leverage Trek to delve into the local content feed, discovering others with similar tastes.

## Technology Overview
Trek will be a desktop web app built with a React front-end for a smooth and
responsive user experience. The back-end will use Springboot with a PostgreSQL
database.

Data storage for things like photos and videos will be handled using Cloudflare Stream
and Cloudflare Images.

For the map, we will be using the Google Maps API.

## GitHub Repository Link
[https://github.com/IINEP7UNEII/trek](https://github.com/IINEP7UNEII/trek)

## Conclusion
Trek reimagines social connectivity by transforming shared digital content into pathways for real-world connections. It's more than an app—it's a social compass guiding users to discover kindred spirits in the tapestry of everyday locales, by intertwining physical presence with shared passions.

With Trek, every place tells a story, vibe, and pulse of the people that inhabit it, and, as a participant, enables you to find "your people" at each place.
