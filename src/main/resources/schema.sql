DROP TABLE IF EXISTS User;

DROP TABLE IF EXISTS Portfolio;

DROP TABLE IF EXISTS StockPosition;


CREATE TABLE IF NOT EXISTS User (
  id INTEGER PRIMARY KEY,
  username TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL,
  active BOOLEAN NOT NULL,
  roles TEXT NOT NULL,
  email TEXT NOT NULL UNIQUE,
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  register_date TEXT NOT NULL
);


CREATE TABLE IF NOT EXISTS Portfolio (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  description TEXT,
  cost FLOAT NOT NULL,
  equity_value FLOAT NOT NULL,
  cash_position FLOAT NOT NULL,
  num_of_positions INTEGER NOT NULL,
  created_date TEXT NOT NULL,
  user_id INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES User (id)
);


CREATE TABLE IF NOT EXISTS StockPosition (
  id INTEGER PRIMARY KEY,
  ticker TEXT NOT NULL,
  cost FLOAT NOT NULL,
  avg_cost FLOAT NOT NULL,
  equity_value FLOAT NOT NULL,
  quantity FLOAT NOT NULL,
  initiated_date TEXT NOT NULL,
  last_date TEXT NOT NULL,
  port_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES User (id),
  FOREIGN KEY (port_id) REFERENCES Portfolio (id)
);