## MoEngage Flutter Sample App

Sample App to demonstrate FCM token and payload handling done by App.

1. App registers the `FirebaseMessagingService` and handles the token and payload passing to MoEngage SDK in `CustomFirebaseReceiver`
2. `MainActivity` is linked to the deeplink url `deeplink://moengage/circle`, adding this to the `DeepLink` navigation action will launch the Sample App

## Sample App Usage

1. Add your APP-ID in the `SampleApplication` class
2. Add your `google-services.json` file to `app/` and replace the `applicationId` in `build.gradle` with your application id.

    ```
   defaultConfig {
   // TODO: Specify your own unique Application ID (https://developer.android.com/studio/build/application-id.html).
   applicationId "YOUR_APP_ID"
   ...
   }
   ```
