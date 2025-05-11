const express = require('express');
const { db } = require('../server');
const router = express.Router();

// Helper function to simulate AI odds update
function generateRandomOdds() {
  const base = Math.random() * (3 - 1.2) + 1.2; // odds between 1.2 and 3
  return Math.round(base * 100) / 100;
}

// Get all upcoming matches with current odds
router.get('/', (req, res) => {
  db.all('SELECT * FROM matches WHERE match_date >= datetime("now")', [], (err, rows) => {
    if (err) {
      return res.status(500).json({ message: 'Database error' });
    }
    res.json(rows);
  });
});

// Admin: Add a new match
router.post('/add', (req, res) => {
  const { sport, team1, team2, match_date } = req.body;
  if (!sport || !team1 || !team2 || !match_date) {
    return res.status(400).json({ message: 'Missing required fields' });
  }
  const odds_team1 = generateRandomOdds();
  const odds_team2 = generateRandomOdds();
  const odds_draw = sport === 'football' ? generateRandomOdds() : null;

  const stmt = db.prepare('INSERT INTO matches (sport, team1, team2, match_date, odds_team1, odds_team2, odds_draw) VALUES (?, ?, ?, ?, ?, ?, ?)');
  stmt.run(sport, team1, team2, match_date, odds_team1, odds_team2, odds_draw, function(err) {
    if (err) {
      return res.status(500).json({ message: 'Database error' });
    }
    res.json({ id: this.lastID });
  });
  stmt.finalize();
});

// Admin: Update match result
router.post('/result', (req, res) => {
  const { match_id, result } = req.body;
  if (!match_id || !result) {
    return res.status(400).json({ message: 'Missing match_id or result' });
  }
  const stmt = db.prepare('UPDATE matches SET result = ? WHERE id = ?');
  stmt.run(result, match_id, function(err) {
    if (err) {
      return res.status(500).json({ message: 'Database error' });
    }
    res.json({ updated: this.changes });
  });
  stmt.finalize();
});

module.exports = router;
