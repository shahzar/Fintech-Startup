package com.shzlabs.mamopay.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shzlabs.mamopay.MainViewModel
import com.shzlabs.mamopay.SplashViewModel
import com.shzlabs.mamopay.ui.home.OnBoardingViewModel
import com.shzlabs.mamopay.ui.main.dashboard.DashboardViewModel
import com.shzlabs.mamopay.ui.onboarding.details.OnBoardingDetailsViewModel
import com.shzlabs.mamopay.ui.signin.SignInViewModel
import com.shzlabs.mamopay.ui.signin.setup.SignInSetupViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ViewModelFactory @Inject constructor (val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creators[modelClass]?.get() as T
    }

}

@Module
internal abstract class ViewModelBuilder {
    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory


    // Add all ViewModels here
    @Binds
    @IntoMap
    @ViewModelKey(OnBoardingViewModel::class)
    internal abstract fun onBoardingViewModel(onBoardingViewModel: OnBoardingViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(onBoardingViewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun splashViewModel(splashViewModel: SplashViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OnBoardingDetailsViewModel::class)
    internal abstract fun onBoardingDetailsViewModel(onBoardingDetailsViewModel: OnBoardingDetailsViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInSetupViewModel::class)
    internal abstract fun viewModel(viewModel: SignInSetupViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    internal abstract fun signInViewModel(viewModel: SignInViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    internal abstract fun dashboardViewModel(viewModel: DashboardViewModel) : ViewModel


}

@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)