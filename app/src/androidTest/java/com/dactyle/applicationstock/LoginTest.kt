package com.dactyle.applicationstock

import android.util.Log
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId


import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dactyle.applicationstock.ui.LoginFragment
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
public class LoginTest {
    @Test
    fun testInvalidEmail() {
        val idlingResource = ToastIdlingResource()

        val scenario = ActivityScenario.launch(MainActivity::class.java)

        /*
        //Si vous souhaitez tester uniquement le fragment
        //Activer les lignes ci-dessous et commenter la ligne au dessus
        */
        /*
        val scenario = FragmentScenario.launchInContainer(LoginFragment::class.java)
        scenario.onFragment { fragment ->
            println("Fragment lancé : $fragment")
        }*/

        // Entrer un email invalide
        onView(withId(R.id.emailEditText)).perform(typeText("invalid"))
        onView(withId(R.id.passwordEditText)).perform(typeText("password123"))
        closeSoftKeyboard();

        idlingResource.increment()

        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.emailEditText))
            .check(matches(hasErrorText("Email non valide")))

        idlingResource.decrement()
        IdlingRegistry.getInstance().unregister(idlingResource)
    }


}