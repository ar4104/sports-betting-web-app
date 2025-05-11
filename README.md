# Sports Betting Web Application

## Overview
This is a simple sports betting web application built with Node.js (Express) backend and React frontend. It allows users to register, view upcoming matches, place bets using virtual currency "BetCoins", and recharge their balance via Payeer payment system. The app includes an AI-powered odds generator and an admin panel for managing matches and results. SQLite is used as the database.

## Features
- User registration and login
- View upcoming football, basketball, and tennis matches
- Place bets with BetCoins
- Recharge BetCoins via Payeer payment integration
- AI-powered dynamic odds generator
- User dashboard with bet history and balance
- Admin panel to add matches, set results, and view bets
- Mobile-friendly and clean UI

## Setup Instructions

### Prerequisites
- Node.js (v16+ recommended)
- npm
- SQLite3

### Backend Setup
1. Navigate to the backend directory:
   ```
   cd backend
   ```
2. Install dependencies:
   ```
   npm install express sqlite3 body-parser cors
   ```
3. Initialize the database:
   ```
   node db-init.js
   ```
4. Start the backend server:
   ```
   node server.js
   ```
   The server will run on port 5000 by default.

### Frontend Setup
(To be added after frontend implementation)

## Deployment on VPS
1. Install Node.js and npm on your VPS.
2. Clone the repository.
3. Follow the backend and frontend setup steps.
4. Use a process manager like PM2 to run the backend server:
   ```
   pm2 start backend/server.js --name sports-betting-backend
   ```
5. Configure a reverse proxy (e.g., Nginx) to forward requests to the backend server.
6. Set up SSL certificates for secure HTTPS access.

## Notes
- Payeer integration requires configuration of shop_id and secret key.
- AI odds generator simulates odds dynamically based on random data.
- All code is well-commented for ease of understanding and modification.
