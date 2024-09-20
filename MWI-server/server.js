const express = require('express');
const path = require('path');
const network = require('./utils/network'); // Import network utils
const videosRouter = require('./routes/videos'); // Import videos route
const streamingRouter = require('./routes/streaming'); // Import streaming route

const app = express();
const PORT = 3000;

// Serve static files from the "public" directory
app.use(express.static(path.join(__dirname, 'public')));

// Use the videos router for handling video listing
app.use('/videos', videosRouter);

// Use the streaming router for handling video streaming
app.use('/video', streamingRouter);

// Start the server
const localIP = network.getLocalIPAddress();
app.listen(PORT, '0.0.0.0', () => {
  console.log(`Server is running on http://${localIP}:${PORT}`);
});
