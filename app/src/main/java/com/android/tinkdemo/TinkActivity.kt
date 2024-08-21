package com.android.tinkdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fintecsystems.xs2awizard.components.XS2AWizardCallbackListener
import com.fintecsystems.xs2awizard.components.XS2AWizardError
import com.fintecsystems.xs2awizard.components.XS2AWizardStep
import com.fintecsystems.xs2awizard.components.theme.XS2ATheme
import com.fintecsystems.xs2awizard.wrappers.XS2AWizardFragment
import com.fintecsystems.xs2awizard.wrappers.setXs2aCallbacks

class TinkActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_tink)
    startFtsSdk()
    setXS2ACallbacks()
  }

  private fun setXS2ACallbacks() {
    supportFragmentManager.setXs2aCallbacks(this, object : XS2AWizardCallbackListener {
      // finish calls to make implementation simpler - added results in real production app
      override fun onAbort() { finish() }
      override fun onBack() { /* NO-OP */ }
      override fun onError(xs2aWizardError: XS2AWizardError) { finish() }
      override fun onFinish(credentials: String?) { finish() }
      override fun onNetworkError() { /* NO-OP */ }
      override fun onStep(newStep: XS2AWizardStep) { /* NO-OP */ }
    })
  }

  private fun startFtsSdk() {
    val xs2aWizard = XS2AWizardFragment(
      sessionKey = intent.getStringExtra(WIZARD_SESSION_KEY_KEY)!!,
      theme = XS2ATheme(), //we set the custom theme, but the content shouldn't matter in this case
      // we set font id as well, but it doesn't affect the flow
    )
    replaceFragment(xs2aWizard)
  }

  private fun replaceFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
      .replace(R.id.views_container, fragment)
      .commit()
  }

  override fun onBackPressed() {
    finish()
  }

  override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
  }

  companion object {
    fun intent(context: Context, wizardSessionKey: String) =
      Intent(context, TinkActivity::class.java)
        .putExtra(WIZARD_SESSION_KEY_KEY, wizardSessionKey)
  }
}

private const val WIZARD_SESSION_KEY_KEY = "WIZARD_SESSION_KEY"