package com.lyterk

import android.view.View
import android.app.Activity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.GoogleApiClient.{ConnectionCallbacks, OnConnectionFailedListener}
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.location.LocationServices

import com.lyterk.layouts.MainView

import macroid._
import macroid.FullDsl._
import macroid.contrib._

import scala.concurrent.ExecutionContext.Implicits.global

class MainActivity extends Activity
    with Contexts[Activity]
    with GoogleApiClient.ConnectionCallbacks
    with GoogleApiClient.OnConnectionFailedListener {

  var mGoogleApiClient: GoogleApiClient = _
  // var latitude: String
  // var longitude: String
  val TAG: String = "com.lyterk.logging"

  protected def buildGoogleApiClient() {
    this.synchronized {
      mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .addApi(LocationServices.API)
        .build();
      // Log.v(TAG, "buildGoogleApiClient successful")
    }
  }
  
  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)
    setContentView(getUi(MainView.layout))

    buildGoogleApiClient()

    if (mGoogleApiClient != null) {
      Log.v(TAG, "mGoogleApiClient is not null")
      mGoogleApiClient.connect()
    } else {      
    }
  }

  override def onConnected(connectionHint: Bundle) = {
    Log.v(TAG, "onConnected")
    var mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
    if (mLastLocation != null) {
      val latitude = String.valueOf(mLastLocation.getLatitude())
      val longitude  = String.valueOf(mLastLocation.getLongitude())
      Log.d(TAG, latitude)
      Log.d(TAG, longitude)
    } else {      
    }
  }

override def onConnectionFailed(conRes: ConnectionResult) = {
    // super.onConnectionFailed(conRes)
    Log.v(TAG, "onConnectionFailed")
  }

  override def onConnectionSuspended(x: Int): Unit = {
    // super.onConnectionSuspended(x)
    Log.v(TAG, "onConnectionSuspended")
  }
}
