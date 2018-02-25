# DaggerViewModelHelper

This library enables you to easily provide ViewModels with their dependencies and inject a view model factory into the Fragment or Activity to provide the ViewModel with its dependencies

An android library to help with, and reduce the boilerplate of, implmenting dagger with android architecture ViewModel component inspired by the [google github sample](https://github.com/googlesamples/android-architecture-components/tree/e33782ba54ebe87f7e21e03542230695bc893818/GithubBrowserSample)

Problem 

The default ViewModel Provider doesn't allow for constructor inection
```ViewModelProviders.of(this).get(MyViewModel.class);```
So we have to create a ```ViewModelProvider.Factory``` that can provide the dependencies. However this is non-trivial and requires some boilerplate to do cleanly with dagger.

Solution 

Using daggers multibing we can create a map with the ViewModels and a generic ```ViewModelProvider.Factory``` that can be injected into the Activity or Fragment which can be used the to get the desired ```ViewModel```

## Installation

Ensure you have the jcentre() dependencies in your root build.gradle (most android projects should have this by default)
```
allprojects {
  repositories {
    ...
    jcenter()
  }
}
```
Add the dependency in the build.gradle of any app or module.
```
dependencies {
  implementation 'com.github.arranlomas.daggerviewmodelhelper:daggerviewmodelhelper:0.1.0'
}
```

## Setup
(See the [sample app](https://github.com/arranlomas/DaggerViewModelHelper/tree/master/app) for full details)

Main Application
```
class Application : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        AppInjector.init(this)
        val appComponent = DaggerAppComponent.create()
        appComponent.inject(this)
        return appComponent
    }
}
```

ViewModelModule
```
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    //IMPORTANT - this is the generic ViewModelFactory supplied with this library that created the ViewModel with its dependencies
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
```

ViewModel
IMPORATANT - note the @Inject constructor with the dependencies
```
class MainViewModel @Inject constructor(private val someDependency: ISomeDependency) : ViewModel() {
    fun getSomeStuff(): LiveData<List<Int>> {
        return someDependency.getSomeStuff()
    }
}
```

Activity
IMPORTANT - note how the view model factory is injeccted and how it implements Injectable (this is so we don't have to call AndroidInjection.inject(this) just like with DaggerActivity but DaggerActivity cannot be used with ViewModelProvider
```
class MainActivity : AppCompatActivity(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getSomeStuff().observe(this, Observer<List<Int>> { somewhereToShowStuff.text = it?.toString() ?: "error" })
    }
}
```
