# Trek
How the Postgres db was initialized:
```
CREATE TABLE Places (
    geohash VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE Posts (
    id SERIAL PRIMARY KEY,
    place_geohash VARCHAR(255),
    post_id VARCHAR(255),
    "user" VARCHAR(255),
    timestamp BIGINT,
    post_date DATE,
    type VARCHAR(255),
    title VARCHAR(255),
    content TEXT,
    FOREIGN KEY (place_geohash) REFERENCES Places(geohash)
);

-- Index for efficient querying by place_geohash
CREATE INDEX idx_posts_place_geohash ON Posts(place_geohash);

-- Index for querying by post_date
CREATE INDEX idx_posts_post_date ON Posts(post_date);
```
Remember to put the correct URL n shit for the db into `application.properties`.

### ngeohash
To decode the geohashes into lat. and long. values, I'm using a library called `ngeohash`.

This shit was a pain to setup though. It's designed to be added as a npm/node module, not for being included with a cdn link.
Thus, I had to download the minified cdn link js ([found here](https://cdn.jsdelivr.net/npm/ngeohash@0.6.3/main.min.js)) into the `static` folder, and then change the very last line from `module.exports=geohash;` to `window.Geohash = geohash;` to get it working.
