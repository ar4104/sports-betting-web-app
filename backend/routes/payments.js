const express = require('express');
const crypto = require('crypto');
const { db } = require('../server');
const router = express.Router();

const PAYEER_SHOP_ID = 'your_shop_id';
const PAYEER_SECRET_KEY = 'your_secret_key';
const PAYEER_URL = 'https://payeer.com/merchant/';

// Generate Payeer payment URL
router.post('/generate', (req, res) => {
  const { user_id, amount } = req.body;
  if (!user_id || !amount || amount <= 0) {
    return res.status(400).json({ message: 'Invalid user_id or amount' });
  }

  const order_id = Date.now().toString();
  const currency = 'USD';

  // Signature: shop_id:order_id:amount:currency:secret_key
  const signString = `${PAYEER_SHOP_ID}:${order_id}:${amount}:${currency}:${PAYEER_SECRET_KEY}`;
  const m_sign = crypto.createHash('sha256').update(signString).digest('hex').toUpperCase();

  const paymentUrl = `${PAYEER_URL}?m_shop=${PAYEER_SHOP_ID}&m_orderid=${order_id}&m_amount=${amount}&m_curr=${currency}&m_sign=${m_sign}`;

  // Save transaction with pending status
  const stmt = db.prepare('INSERT INTO transactions (user_id, amount, type) VALUES (?, ?, ?)');
  stmt.run(user_id, amount, 'pending', function(err) {
    if (err) {
      return res.status(500).json({ message: 'Database error' });
    }
    res.json({ paymentUrl, order_id });
  });
  stmt.finalize();
});

// Payment success callback (simulate)
router.post('/success', (req, res) => {
  const { user_id, order_id, amount } = req.body;
  if (!user_id || !order_id || !amount) {
    return res.status(400).json({ message: 'Missing parameters' });
  }

  // Update user balance and transaction status
  db.serialize(() => {
    db.run('BEGIN TRANSACTION');
    db.run('UPDATE users SET betcoins = betcoins + ? WHERE id = ?', [amount, user_id]);
    db.run('UPDATE transactions SET type = ? WHERE id = (SELECT id FROM transactions WHERE user_id = ? AND amount = ? AND type = ? ORDER BY id DESC LIMIT 1)', ['completed', user_id, amount, 'pending']);
    db.run('COMMIT');
  });

  res.json({ message: 'Payment processed, balance updated' });
});

module.exports = router;
