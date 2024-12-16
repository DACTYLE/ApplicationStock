package com.dactyle.applicationstock

import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.test.espresso.IdlingResource
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import androidx.test.espresso.Root
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback

import com.google.android.material.snackbar.Snackbar

class ToastMatcher : TypeSafeMatcher<Root>() {

    override fun describeTo(description: Description?) {
        description?.appendText("Email non valide")
    }

    override fun matchesSafely(root: Root): Boolean {
        val type = root.windowLayoutParams.get().type
        Log.d("ToastMatcher", "Root type: $type") // Diagnostic pour le type de fenêtre

        // Le type de fenêtre pour un toast est souvent TYPE_TOAST ou un décor spécifique
        // Il peut aussi être de type TYPE_APPLICATION ou TYPE_APPLICATION_OVERLAY
        if (type == WindowManager.LayoutParams.TYPE_TOAST ||
            type == WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY ||
            type == WindowManager.LayoutParams.TYPE_APPLICATION) {

            val windowToken = root.decorView.windowToken
            val appToken = root.decorView.applicationWindowToken
            val matches = windowToken === appToken
            Log.d("ToastMatcher", "Matches: $matches")
            return matches
        }

        return false
    }
}

class ToastIdlingResource : IdlingResource {
    private var countingIdlingResource = CountingIdlingResource("Toast Idling Resource")
    private var callback: ResourceCallback? = null

    override fun getName(): String {
        return countingIdlingResource.name
    }

    override fun isIdleNow(): Boolean {
        return countingIdlingResource.isIdleNow()
    }

    override fun registerIdleTransitionCallback(callback: ResourceCallback) {
        this.callback = callback
        countingIdlingResource.registerIdleTransitionCallback(callback)
    }

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        countingIdlingResource.decrement()
    }
}