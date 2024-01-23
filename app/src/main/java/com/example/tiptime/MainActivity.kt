package com.example.tiptime

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appUpdateManager: AppUpdateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        checkUpdates()




    }


    override fun onResume() {
        super.onResume()

        appUpdateManager
            .appUpdateInfo
            .addOnSuccessListener { appUpdateInfo ->

                if (appUpdateInfo.updateAvailability()
                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
                ) {
                    // If an in-app update is already running, resume the update.
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        IMMEDIATE,
                        this,
                        REQUEST_CODE_APP_UPDATE
                    );
                }
            }
    }
    private fun checkUpdates(){
        // Inside your activity or fragment
        appUpdateManager = AppUpdateManagerFactory.create(this)

        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                when (appUpdateInfo.updateAvailability()) {
                    UpdateAvailability.UPDATE_AVAILABLE -> {
                        if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                            // An immediate update is available
                            // Call the method to start the immediate update flow
                            startAppUpdateFlow(appUpdateInfo)
                        } else if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                            // A flexible update is available
                            // Call the method to start the flexible update flow
                            startAppUpdateFlow(appUpdateInfo)
                        }
                    }
                    // ... (other cases)
                }
            }
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_APP_UPDATE) {
            if (resultCode != RESULT_OK) {
                // If the update is not completed, you can retry the update or handle accordingly
            } else {
                // The update was successful, complete the update
                appUpdateManager.completeUpdate()
            }
        }
    }



    private fun startAppUpdateFlow(appUpdateInfo: AppUpdateInfo) {

         appUpdateManager.startUpdateFlowForResult(
            appUpdateInfo,
             if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                 AppUpdateType.IMMEDIATE
             } else if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                 AppUpdateType.FLEXIBLE
             } else {
                    return
             },
             this,
             REQUEST_CODE_APP_UPDATE

        )
        appUpdateManager.registerListener(installStateUpdatedListener)

        // Add listener for download success
      /*  appUpdateManager.registerListener { state ->
            if (state.installStatus() == InstallStatus.DOWNLOADED) {
                // The update has been downloaded
                showSnackbarUpdateDownloaded()
            }
        }*/
    }

    private val installStateUpdatedListener = InstallStateUpdatedListener { state ->
        if (state.installStatus() == InstallStatus.DOWNLOADED) {
            // The update has been downloaded
            showSnackbarUpdateDownloaded()
        }
    }


    private fun showSnackbarUpdateDownloaded() {
        Snackbar.make(binding.root, "Update has been downloaded", Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Unregister the listener to avoid memory leaks
        appUpdateManager.unregisterListener(installStateUpdatedListener)
    }


    // Constants
    companion object {
        private const val REQUEST_CODE_APP_UPDATE = 123
    }



}