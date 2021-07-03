# SpaceXRocketLaunch
Application Architecture 
Clean Architecture and MVVM 

Language Used 
Kotlin

Libraries Used
RxJava & RxAndroid : Provides observables and reactive streams that enables us to leverage on reactive and declarative style programming

OkHttp : An extensible HTTP client for Java

Retrofit : A type-safe HTTP client on top of OkHttp that allows us to define our API endpoints as interfaces

Hilt : A static, compile-time dependency injection library for Android

Room : The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

Mockito : Mocking framework for unit test cases

Glide : An image loader library

App Overview:
1. Displaying welcome dialog for first time user
2. Display list of active rockets after first launch
3. Each rocket has 
		Name 
		Country
		Engine Count
		Flicker Image

4. Has pull to refresh to refresh data
5. All information has been cached using room for future use 
