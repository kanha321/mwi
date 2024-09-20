const os = require('os');

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

module.exports = {
  getLocalIPAddress,
};
