const express = require('express');
const { db } = require('../server');
const router = express.Router();

// Place a bet
router.post('/place', (req, res) => {
  const { user_id, match_id, bet_amount, bet_on } = req.body;
  if (!user_id || !match_id || !bet_amount || !bet_on) {
    return res.status(400).json({ message: 'Missing required fields' });
  }

  // Check user balance
  db.get('SELECT betcoins FROM users WHERE id = ?', [user_id], (err, user) => {
    if (err) return res.status(500).json({ message: 'Database error' });
    if (!user) return res.status(404).json({ message: 'User not found' });
    if (user.betcoins < bet_amount) {
      return res.status(400).json({ message: 'Insufficient BetCoins balance' });
    }

    // Deduct bet amount and insert bet
    db.serialize(() => {
      db.run('BEGIN TRANSACTION');
      db.run('UPDATE users SET betcoins = betcoins - ? WHERE id = ?', [bet_amount, user_id]);
      const stmt = db.prepare('INSERT INTO bets (user_id, match_id, bet_amount, bet_on, status) VALUES (?, ?, ?, ?, ?)');
      stmt.run(user_id, match_id, bet_amount, bet_on, 'pending', function(err) {
        if (err) {
          db.run('ROLLBACK');
          return res.status(500).json({ message: 'Database error' });
        }
        db.run('COMMIT');
        res.json({ bet_id: this.lastID });
      });
      stmt.finalize();
    });
  });
});

// Get bet history for a user
router.get('/history/:user_id', (req, res) => {
  const user_id = req.params.user_id;
  db.all('SELECT b.*, m.sport, m.team1, m.team2, m.match_date FROM bets b JOIN matches m ON b.match_id = m.id WHERE b.user_id = ?', [user_id], (err, rows) => {
    if (err) return res.status(500).json({ message: 'Database error' });
    res.json(rows);
  });
});

module.exports = router;
