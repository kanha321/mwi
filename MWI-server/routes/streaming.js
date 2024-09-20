const express = require('express');
const fs = require('fs');
const path = require('path');
const mime = require('mime-types');

const router = express.Router();

router.get('/', (req, res) => {
  const folderName = req.query.folder;
  const filename = 'playlist.m3u8';
  const videoPath = path.join(__dirname, '../public/videos', folderName, filename);

  if (!fs.existsSync(videoPath)) {
    return res.status(404).send("Playlist not found");
  }

  const stat = fs.statSync(videoPath);
  const fileSize = stat.size;

  const head = {
    'Content-Length': fileSize,
    'Content-Type': mime.lookup(videoPath) || 'application/octet-stream',
  };

  res.writeHead(200, head);

  const fileStream = fs.createReadStream(videoPath);
  fileStream.pipe(res);

  fileStream.on('error', (err) => {
    console.error('Stream error:', err);
    res.status(500).send('Internal server error');
  });
});

module.exports = router;
