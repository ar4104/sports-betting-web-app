const { db } = require('./server');

// Create tables for users, matches, bets, transactions
db.serialize(() => {
  db.run(`
    CREATE TABLE IF NOT EXISTS users (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      username TEXT UNIQUE,
      password TEXT,
      betcoins INTEGER DEFAULT 0,
      payeer_wallet TEXT
    )
  `);

  db.run(`
    CREATE TABLE IF NOT EXISTS matches (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      sport TEXT,
      team1 TEXT,
      team2 TEXT,
      match_date TEXT,
      odds_team1 REAL,
      odds_team2 REAL,
      odds_draw REAL,
      result TEXT
    )
  `);

  db.run(`
    CREATE TABLE IF NOT EXISTS bets (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      user_id INTEGER,
      match_id INTEGER,
      bet_amount INTEGER,
      bet_on TEXT,
      status TEXT,
      FOREIGN KEY(user_id) REFERENCES users(id),
      FOREIGN KEY(match_id) REFERENCES matches(id)
    )
  `);

  db.run(`
    CREATE TABLE IF NOT EXISTS transactions (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      user_id INTEGER,
      amount INTEGER,
      type TEXT,
      timestamp TEXT DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY(user_id) REFERENCES users(id)
    )
  `);

  console.log('Database tables created or verified.');
  db.close();
});
