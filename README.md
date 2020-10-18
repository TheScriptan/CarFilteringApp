# CarFilteringApp

Simple app that queries a list of cars, displays it in a RecyclerView and allows the user to sort it by three factors: Distance, Plate Number & Battery Remaining.

## Tasks
- [x] Display cars in a list.
- [x] Add a button to sort cars by distance from user
- Create filter for cars:
- [x] _*Dagger*_/Kodein
- [x] MVVM
- [ ] Unit/UI tests
- [x] _*Rx*_ or Coroutines

## Libraries Used
* Google Play Services Locations
* Material
* Navigation Architecture Component
* Coil
* RxPermission
* RxLocation
* Dagger 2
* Retrofit
* And others...

What could've been done better | How it could've been done better
------------ | -------------
Improve design and XML code | Add styles & selectors for ViewHolder states;
Implement Responsive Error handling for RecyclerView | Define Sealed classes for three states: (Failure, Loading, Success)
Create a map with Car markers on it| Create a new fragment with Google Map to which you could navigate by using BottomNavView
Receive more accurate distance between user and the car| By using Distance Matrix API it is possible to receive information about distance and journey time to specified location
Replace sorting of battery remaining to filtering | Add a slider that hides cars in relation to the slider threshold (could be located in BottomSheetDialogFragment)