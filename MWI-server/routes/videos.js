const express = require('express');
const fs = require('fs');
const path = require('path');

const router = express.Router();

router.get('/', (req, res) => {
  const videosDir = path.join(__dirname, '../public/videos');
  
  fs.readdir(videosDir, (err, folders) => {
    if (err) {
      console.error('Directory read error:', err);
      return res.status(500).send('Internal server error');
    }

    const videoFolders = folders.filter(folder => {
      return fs.statSync(path.join(videosDir, folder)).isDirectory();
    });

    res.json(videoFolders);
  });
});

module.exports = router;