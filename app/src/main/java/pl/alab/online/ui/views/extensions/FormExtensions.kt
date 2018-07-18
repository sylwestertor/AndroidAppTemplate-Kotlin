package pl.alab.online.ui.views.extensions

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onTextChanged(function: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(text: Editable?) {
            function(text.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}

fun TextInputLayout.setErrorLabel(errorMessage: String?) {
    this.error = errorMessage
    this.isErrorEnabled = error != null
}