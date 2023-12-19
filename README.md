# beerpedia
This sample project shows beers loaded from [PUNK API](https://punkapi.com/documentation/v2) and allows users to see details of selected beers.

The UI is created with [Compose](https://developer.android.com/develop/ui) and it consists of two main screens with four states (empty, loading, loaded, error) that are provided by a [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel).

After initial loading from network (using [Retrofit](https://square.github.io/retrofit/)), the beers are stored with [Proto DataStore](https://developer.android.com/topic/libraries/architecture/datastore#proto-datastore).

[Hilt](https://developer.android.com/training/dependency-injection/hilt-android) is the choice for dependency injection.

### Testing
Instrumented tests for the main composables are present.

Unit tests for the ViewModel and the repository are present.

