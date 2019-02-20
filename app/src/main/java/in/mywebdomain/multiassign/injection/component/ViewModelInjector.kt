package `in`.mywebdomain.multiassign.injection.component

import `in`.mywebdomain.multiassign.injection.module.NetworkModule
import `in`.mywebdomain.multiassign.ui.category.CategoryListViewModel
import `in`.mywebdomain.multiassign.ui.jokes.JokesListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(jokesListViewModel: JokesListViewModel)
    fun inject(cateListViewModel: CategoryListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}