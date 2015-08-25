package com.lyterk.layouts

import android.widget.{Button, LinearLayout, TextView}
import android.util.Log
import android.view.View
import android.view.ViewGroup.LayoutParams._

import macroid._
import macroid.FullDsl._
import macroid.contrib._

import scala.concurrent.ExecutionContext

object MainView {    

  val TAG = "com.lyterk.logging"
  var TV = slot[TextView]

  def layout(implicit ctx: ActivityContext) = {
    l[LinearLayout](      
      w[TextView] <~ wire(TV) <~ text("Hello there")
    )
  }
}

