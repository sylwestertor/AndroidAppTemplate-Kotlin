package pl.alab.online.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.widget.EditText
import android.widget.TextView

import pl.alab.online.R

class ExampleDialog(context: Context, listener: (string: String) -> Unit) : Dialog(context) {
    internal var etExample: EditText
    internal var btnSend: TextView

    init {
        setContentView(R.layout.dialog_example)
        etExample = findViewById(R.id.etExample)
        btnSend = findViewById(R.id.tvSendButton)

        btnSend.setOnClickListener { v ->
            listener.invoke(etExample.text.toString())
            dismiss()
        }
    }

    override fun show() {
        super.show()
        etExample.text = null
    }
}
