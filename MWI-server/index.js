const express = require('express');
const fs = require('fs');
const path = require('path');
const os = require('os');
const mime = require('mime-types'); // For dynamic Content-Type
const app = express();
const PORT = 3000;

// Serve static files (e.g., .m3u8 and .ts files) from the 'videos' directory
app.use(express.static('videos'));

// List video folders route
app.get('/videos', (req, res) => {
  const videosDir = path.join(__dirname, 'videos');

  fs.readdir(videosDir, (err, folders) => {
    if (err) {
      console.error('Directory read error:', err);
      return res.status(500).send('Internal server error');
    }

    const videoFolders = folders.filter(folder => {
      return fs.statSync(path.join(videosDir, folder)).isDirectory();
    });

    res.json(videoFolders);  // Send list of video folder names
  });
});

// Serve the HLS playlist (.m3u8) file
app.get('/video', (req, res) => {
  const folderName = req.query.folder;  // Get the folder name from the query parameters
  const playlistPath = path.join(__dirname, 'videos', folderName, 'playlist.m3u8');  // Always use 'playlist.m3u8'

  // Check if the playlist file exists
  if (!fs.existsSync(playlistPath)) {
    return res.status(404).send('Playlist not found');
  }

  const mimeType = mime.lookup(playlistPath) || 'application/octet-stream';
  res.setHeader('Content-Type', mimeType);

  // Stream the .m3u8 file
  const fileStream = fs.createReadStream(playlistPath);
  fileStream.pipe(res);

  fileStream.on('error', (err) => {
    console.error('Stream error:', err);
    res.status(500).send('Internal server error');
  });
});

// Get the real local IP address
function getLocalIPAddress() {
  const interfaces = os.networkInterfaces();
  let localIP = 'localhost';

  for (const interfaceName in interfaces) {
    const interfaceInfo = interfaces[interfaceName];

    // Prioritize Wi-Fi interface
    if (interfaceName.toLowerCase().includes('wi-fi')) {
      for (let i = 0; i < interfaceInfo.length; i++) {
        const alias = interfaceInfo[i];
        if (alias.family === 'IPv4' && !alias.internal) {
          return alias.address;
        }
      }
    }
  }

  return localIP;
}

const localIP = getLocalIPAddress();

app.listen(PORT, '0.0.0.0', () => {
  console.log(`Server is running on http://${localIP}:${PORT}`);
});
