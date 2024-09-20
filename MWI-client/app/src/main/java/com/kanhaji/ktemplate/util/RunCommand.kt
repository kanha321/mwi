package com.kanhaji.ktemplate.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader

var ROOT_MODE = false

object RunCommand {
    suspend fun shell(
        command: String,
        asRoot: Boolean = ROOT_MODE,  // Add asRoot flag
        onLineReceived: suspend (String) -> Unit
    ): Process? {
        return try {
            var newCommand = command
            var newAsRoot = asRoot

            // Modify ADB and Fastboot commands
            if (command.startsWith("adb")) {
                newCommand = command.replace("adb", "/data/adb/bin/adb")
                newAsRoot = true
            } else if (command.startsWith("fastboot")) {
                newCommand = command.replace("fastboot", "/data/adb/bin/fastboot")
                newAsRoot = true
            }

            // Execute the command with or without root
            val process: Process = if (newAsRoot) {
                Runtime.getRuntime().exec("su").apply {
                    outputStream.write(newCommand.toByteArray())
                    outputStream.flush()
                    outputStream.close()
                }
            } else {
                Runtime.getRuntime().exec(newCommand)
            }

            // Coroutine scope for running background tasks
            val scope = CoroutineScope(Dispatchers.IO)

            // Handle output stream in a coroutine
            val outputJob = scope.launch {
                val outputReader = BufferedReader(InputStreamReader(process.inputStream))
                var line: String?
                while (outputReader.readLine().also { line = it } != null) {
                    onLineReceived(line ?: "")
                }
                outputReader.close()
            }

            // Handle error stream in a coroutine
            val errorJob = scope.launch {
                val errorReader = BufferedReader(InputStreamReader(process.errorStream))
                var line: String?
                while (errorReader.readLine().also { line = it } != null) {
                    onLineReceived(line ?: "")
                }
                errorReader.close()
            }

            // Wait for both coroutines to complete
            outputJob.join()
            errorJob.join()

            process.waitFor()
            process
        } catch (e: Exception) {
            onLineReceived("Error: ${e.message}")
            null
        }
    }
}
