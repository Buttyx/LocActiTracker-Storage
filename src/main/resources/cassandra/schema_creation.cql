CREATE KEYSPACE IF NOT EXISTS locactitracker WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};

USE locactitracker;

CREATE TABLE IF NOT EXISTS Recognition (
  timestamp bigint,
  user text,
  dominantHand boolean,
  x double,
  y double,
  z double,
  longitude double,
  latitude double,
  PRIMARY KEY (user, timestamp)
) WITH CLUSTERING ORDER BY (timestamp ASC);

CREATE TABLE IF NOT EXISTS Training (
  timestamp bigint,
  activity text,
  user text,
  dominantHand boolean,
  x double,
  y double,
  z double,
  longitude double,
  latitude double,
  PRIMARY KEY (user, timestamp)
) WITH CLUSTERING ORDER BY (timestamp ASC);

CREATE TABLE IF NOT EXISTS Result (
  timestamp bigint,
  activity text,
  user text,
  model text,
  dominantHand boolean,
  locationAware boolean,
  PRIMARY KEY (user, timestamp, model)
) WITH CLUSTERING ORDER BY (timestamp ASC);