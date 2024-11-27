package com.dactyle.applicationstock.ui

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.dactyle.applicationstock.R
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val emailEditText: EditText = view.findViewById(R.id.emailEditText)
        val passwordEditText: EditText = view.findViewById(R.id.passwordEditText)
        val loginButton: Button = view.findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if(!::auth.isInitialized) auth = FirebaseAuth.getInstance()

            if (!isValidEmail(email)) {
                Toast.makeText(requireContext(), "Email non valide", Toast.LENGTH_SHORT).show()
            }
            else if (password.isEmpty()) {
                passwordEditText.error = "Mot de passe requis"
            } else {
                loginUser(email, password)
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        // Connecter l'utilisateur ou lancer une autre action
        // FirebaseAuth par exemple
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //Toast.makeText(requireContext(), "Connexion réussie", Toast.LENGTH_SHORT).show()
                    val navController = findNavController()
                    navController.navigate(R.id.action_loginFragment_to_articleFragment)
                } else {
                    // Erreur de connexion

                    Log.d("DebugMyApp", task.exception?.message ?: "erreur inconnue");

                    Toast.makeText(requireContext(), "Échec de la connexion : ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}