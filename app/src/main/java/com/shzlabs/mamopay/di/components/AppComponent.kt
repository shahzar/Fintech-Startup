package com.shzlabs.mamopay.di.components

import android.app.Application
import com.shzlabs.mamopay.MainActivity
import com.shzlabs.mamopay.di.ViewModelBuilder
import com.shzlabs.mamopay.di.modules.AppModule
import com.shzlabs.mamopay.di.modules.NetworkModule
import com.shzlabs.mamopay.ui.home.HomeFragment
import com.shzlabs.mamopay.ui.onboarding.details.OnBoardingDetailsFragment
import com.shzlabs.mamopay.ui.signin.SignInFragment
import com.shzlabs.mamopay.ui.onboarding.OnBoardingFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelBuilder::class])
interface AppComponent {

    fun application(application: Application)
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(onBoardingFragment: OnBoardingFragment)
    fun inject(signInFragment: SignInFragment)
    fun inject(onBoardingDetailsFragment: OnBoardingDetailsFragment)

}