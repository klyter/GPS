package com.lyterk

import android.app.Activity
import android.os.Bundle
import android.widget.{LinearLayout, Button, TextView, ToggleButton}
import macroid._
import macroid.FullDsl._
import macroid.Ui._


class MainActivity extends Activity with Contexts[Activity] {

  var greeting = slot[TextView]

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)    

    setContentView {

      getUi {
        var greeting = slot[TextView]

        l[LinearLayout] (     
          w[Button] <~
            text("Click me") <~
            On.click {
              greeting <~ show
            },
          w[TextView] <~
            wire(greeting) <~
            MyTweaks.greeting("Button successfully clicked.")
        ) <~ vertical

      }
    }
  }
}
