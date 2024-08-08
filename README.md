# Setup Guide
This guide will help you set up your development environment to work with Android project. Below are the steps you need to follow:

If you just want to try out the app, you can go to the **Releases page** and download the debug APK. However, if you want to run the project locally, please follow the guide below:




1. [Set up Android Studio](setup-IDE.md)
2. [(Optional) Set up an Emulator](setup-emulator.md)
3. [Open the Project and Run the App](#open-the-project-and-run-the-app)

## Open the Project and Run the App

Once you have set up Android Studio and the Emulator, follow these steps:

1. **Open the Native Android Project**
   - Launch Android Studio.
   - On the welcome screen, click on **"Open"**.
   - Navigate to the directory where your Android project is located and select the project folder.
   - Click **"OK"** to open the project.

2. **Sync Dependencies**
   - Once the project is opened, Android Studio will prompt you to sync the project with Gradle files. Click **"Sync Now"** in the notification bar.
   - Alternatively, you can sync manually by going to **File** > **Sync Project with Gradle Files**.
   - Wait for the sync process to complete. This may take a few minutes depending on the number of dependencies and your internet connection.


3. **Run the App Module**
   - Ensure that you have an emulator set up or a physical device connected.
   - Select the app module in the configuration dropdown menu.
   - Click the **Run** button (green play icon) or go to **Run** > **Run 'app'**.
   - Choose the target device (emulator or connected device) and click **OK**.
   - Wait for the app to compile and install on the selected device.

## Troubleshooting

- **Gradle Sync Issues**: If you encounter issues during Gradle sync, check the `build.gradle` files for any missing or incorrect dependencies.
- **Emulator Performance**: If the emulator is slow, try allocating more RAM or using a physical device for testing.
- **Build Errors**: Review the error messages in the **Build** window to identify and fix issues.

## Additional Resources

- [Android Developer Documentation](https://developer.android.com/docs)
- [Android Studio User Guide](https://developer.android.com/studio/intro)

