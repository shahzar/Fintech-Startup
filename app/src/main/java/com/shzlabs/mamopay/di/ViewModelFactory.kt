package com.shzlabs.mamopay.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shzlabs.mamopay.ui.home.HomeViewModel
import com.shzlabs.mamopay.ui.onboarding.details.OnBoardingDetailsViewModel
import com.shzlabs.mamopay.ui.home.OnBoardingViewModel
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
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(homeViewModel: HomeViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OnBoardingViewModel::class)
    internal abstract fun onBoardingViewModel(onBoardingViewModel: OnBoardingViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OnBoardingDetailsViewModel::class)
    internal abstract fun onBoardingDetailsViewModel(onBoardingDetailsViewModel: OnBoardingDetailsViewModel) : ViewModel


}

@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)