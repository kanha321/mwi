const express = require('express');
const fs = require('fs');
const path = require('path');

const router = express.Router();

// Function to generate password based on the current date
function getCurrentDateFormatted() {
  const now = new Date();
  const day = String(now.getDate()).padStart(2, '0');
  const month = String(now.getMonth() + 1).padStart(2, '0'); // Months are zero-based
  const year = String(now.getFullYear()).slice(-2); // Get last two digits of the year
  return `${day}${month}${year}`;
}

router.get('/', (req, res) => {
  const videosDir = path.join(__dirname, '../public/videos');
  
  fs.readdir(videosDir, (err, folders) => {
    if (err) {
      console.error('Directory read error:', err);
      return res.status(500).send('Internal server error');
    }

    // Filter out only directories and get folder creation dates
    const videoFolders = folders
      .filter(folder => fs.statSync(path.join(videosDir, folder)).isDirectory())
      .map(folder => ({
        name: folder,
        birthtime: fs.statSync(path.join(videosDir, folder)).birthtime
      }));

    // Sort the folders by creation date (birthtime)
    videoFolders.sort((a, b) => a.birthtime - b.birthtime);

    // Create a response with sorted folder names
    const response = {
      videoFolders: videoFolders.map(f => f.name), // Just the folder names
      password: Number(getCurrentDateFormatted()) * 69 % 1000000 // Password based on the current date
    };

    res.json(response);
  });
});

module.exports = router;
