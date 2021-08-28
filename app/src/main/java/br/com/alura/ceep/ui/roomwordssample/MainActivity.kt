package br.com.alura.ceep.ui.roomwordssample

import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.ceep.ui.roomwordssample.WordViewModel.WordViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: WordViewModel by viewModels {
        WordViewModelFactory(
            (application as WordsApplication).userRepository
        )
    }
    private lateinit var putPassword: TextInputEditText
    private lateinit var putEmail: TextInputEditText
    private lateinit var putConfirmPassword: TextInputEditText
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
        observers()
        buttons()
    }

    private fun onListItemClick(user: User, position: Int) {
        Toast.makeText(this, user.password + " " + position, Toast.LENGTH_SHORT).show()
    }

    private fun onListRemoveButtonClick(user: User) {
        viewModel.deleteUser(user)
    }

    private fun onListChangeStatusClick(user: User) {
        if (!user.status) {
            viewModel.activeUser(user)
        } else {
            viewModel.inactiveUser(user)
        }
    }

    fun setup() {
        putPassword = findViewById(R.id.password_input)
        putEmail = findViewById(R.id.email_input)
        putConfirmPassword = findViewById(R.id.confirm_password_input)
        recyclerView = findViewById(R.id.recyclerview)
    }

    private fun observers() {
        val adapter = EmailListAdapter(
            onRemoveButtonClick = { user ->
                onListRemoveButtonClick(user)
            },
            onItemClick = { user, position ->
                onListItemClick(user, position)
            },
            onStatusButtonClick = { user ->
                onListChangeStatusClick(user)
            }
        )
        viewModel.statusChanged.observe(this) { ok ->
            viewModel.getAllUsers()
        }
        viewModel.list.observe(this) { users ->
            adapter.list.addAll(users)
            adapter.notifyDataSetChanged()
        }
        viewModel.deleted.observe(this) { user ->
            Toast.makeText(this, "User excluded", Toast.LENGTH_SHORT).show()
            adapter.list.remove(user)
            adapter.notifyDataSetChanged()
        }
        viewModel.added.observe(this) { saved ->
            if (saved) {
                Toast.makeText(this, "User saved", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.filteredByUser.observe(this) { users ->
            if (users.isNotEmpty()) {
                Toast.makeText(this, "User already exist", Toast.LENGTH_SHORT).show()
            } else {
                val email = putEmail.text.toString()
                val password = putPassword.text.toString()
                val user = User(email, password)
                viewModel.addUser(user)
            }
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.getAllUsers()
        }
    }

    private fun buttons() {
        val saveBtn = findViewById<Button>(R.id.saveBtn)
        saveBtn.setOnClickListener {
            val email = putEmail.text.toString()
            val password = putPassword.text.toString()
            val confirmPassword = putConfirmPassword.text.toString()
            if (password != confirmPassword) {
                Toast.makeText(
                    this, "Different passwords. " +
                            "Please, to save the user," +
                            " confirm the password correctly",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.getByUser(email, password)
                } else {
                    Toast.makeText(this, "Invalid login", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun dataTransferFromAdapter() {

    }

}

