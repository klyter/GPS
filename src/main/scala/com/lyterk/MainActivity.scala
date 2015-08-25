package com.lyterk

import android.view.View
import android.app.Activity
import android.util.Log

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.GoogleApiClient.{ConnectionCallbacks, OnConnectionFailedListener}

import com.lyterk.layouts.MainView

import macroid._
import macroid.FullDsl._
import macroid.contrib._

import scala.concurrent.ExecutionContext.Implicits.global

class MainActivity extends Activity
    with Contexts[Activity]
    with ConnectionCallbacks
    with OnConnectionFailedListener {

  var mGoogleApiClient: GoogleApiClient
  
  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)
    buildGoogleApiClient()
    setContentView(getUi(MainView.layout))
  }  

  protected override def onStart() = {
    super.onStart();
    // Log.v("com.lyterk.logging : Connected?  " + String.valueOf(mGoogleApiClient.isConnected()))
    mGoogleApiClient.connect()
  }

  override def onConnected(connectionHint: Bundle) = {
    mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
    if (mLastLocation != null) {
      latitude = valueOf(mLastLocation.getLatitude()).toString
      longitude  = valueOf(mLastLocation.getLongitude()).toString

      val TAG: String = "com.lyterk.logging"
      Log.d(TAG, latitude)
      Log.d(TAG, longitude)
    }
  }

  protected def buildGoogleApiClient() {    
    this.synchronized {
      mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .addApi(LocationServices.API)
        .build();
    }
  }
