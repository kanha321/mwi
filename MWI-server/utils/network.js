const os = require('os');

function getLocalIPAddress() {
  const interfaces = os.networkInterfaces();
  let localIP = 'localhost';

  // Check for Wi-Fi interface first
  for (const interfaceName in interfaces) {
    const interfaceInfo = interfaces[interfaceName];
    if (interfaceName.toLowerCase().includes('wi-fi')) {
      for (let i = 0; i < interfaceInfo.length; i++) {
        const alias = interfaceInfo[i];
        if (alias.family === 'IPv4' && !alias.internal) {
          return alias.address;
        }
      }
    }
  }

  // Check for Ethernet interface next
  for (const interfaceName in interfaces) {
    const interfaceInfo = interfaces[interfaceName];
    if (
      interfaceName.toLowerCase().includes('ethernet') &&
      !interfaceName.toLowerCase().includes('virtual') &&
      !interfaceName.toLowerCase().includes('wsl')
    ) {
      for (let i = 0; i < interfaceInfo.length; i++) {
        const alias = interfaceInfo[i];
        if (alias.family === 'IPv4' && !alias.internal) {
          return alias.address;
        }
      }
    }
  }

  // Check for other interfaces
  for (const interfaceName in interfaces) {
    const interfaceInfo = interfaces[interfaceName];
    for (let i = 0; i < interfaceInfo.length; i++) {
      const alias = interfaceInfo[i];
      if (alias.family === 'IPv4' && !alias.internal) {
        return alias.address;
      }
    }
  }

  // If no other interface is found, return localhost
  return localIP;
}

module.exports = {
  getLocalIPAddress,
};