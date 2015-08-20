package com.lyterk

import macroid.AppContext
import macroid.FullDsl._
import macroid._
import macroid.contrib.TextTweaks

object MyTweaks {

  def greeting(greeting: String)(implicit appCtx: AppContext) =
    TextTweaks.large +
    text(greeting) +
    hide
}
