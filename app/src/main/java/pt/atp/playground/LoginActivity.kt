package pt.atp.playground

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setup()
    }

    private fun setup() {

        findViewById<EditText>(R.id.tet_password).setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                validateCredentialsAndRedirect()
            }
            true
        }

        findViewById<Button>(R.id.btn_authenticate).setOnClickListener {
            validateCredentialsAndRedirect()
        }
    }

    private fun validateCredentialsAndRedirect() {
        if (areCredentialsValid()) {
            val username = findViewById<EditText>(R.id.tet_username).text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EXTRA_USERNAME, username)
            startActivity(intent)
            finish()
        }
    }

    private fun areCredentialsValid(): Boolean {
        val username = findViewById<EditText>(R.id.tet_username).text.toString()
        if (username.isEmpty()) {
            findViewById<TextView>(R.id.tv_error).text = getString(R.string.error_credentials_empty_username)
            return false
        }

        val password = findViewById<EditText>(R.id.tet_password).text.toString()
        if (password.isEmpty()) {
            findViewById<TextView>(R.id.tv_error).text = getString(R.string.error_credentials_empty_password)
            return false
        }

        val valid = username == password
        if (!valid) {
            findViewById<TextView>(R.id.tv_error).text = getString(R.string.error_credentials_mismatch)
        }

        return valid
    }
}