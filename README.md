# Overview #

The goal of this repository is to demonstrate use cases for design patterns and help solidify your understanding with simple code examples. 

If you are a web or mobile developer you may not be aware that you use some of these patterns every day. This repository will demonstrate how to apply design patterns to the iOS, Android, and Rails frameworks and give you a deeper understanding of when and how to use these patterns. 

#### Resources ####
While this repository is still in progress here are links to the planning doc and the slides for the presention.

- [Google Doc](https://docs.google.com/document/d/189NgasryPDxMfjgtemgyYicn0yXzqSqCw3IcZGuEy-g/edit)
- [Google Slides](https://docs.google.com/presentation/d/123Td1gM7_Vcjh_iPAtU6W75_c3dRzvzoYmqzDlgkepY/edit?userstoinvite=mahyar.varasteh@metova.com&ts=5605654a&actionButton=1#slide=id.p)

# Design Patterns #

The following list is a compilation of design patterns that we have explanations and code examples for.


## Singleton ##
Reference: [Singleton Pattern](https://sourcemaking.com/design_patterns/singleton)

#### Intent ####
>- Ensure a class has only one instance, and provide a global point of access to it.
- Encapsulated "just-in-time initialization" or "initialization on first use".

#### Problem ####
>Application needs one, and only one, instance of an object. Additionally, lazy initialization and global access are necessary.

#### Examples ####

- [DataManager - Android](https://github.com/metova/design-patterns-sample/blob/master/Android/Sample-Android/designpatterns/src/main/java/com/metova/designpatterns/data/DataManager.java)
- [WebService - iOS](https://github.com/metova/design-patterns-sample/blob/master/iOS/DesignPatternsSample/DesignPatternsSample/WebService.swift)

## Factory ##

#### Intent ####
>- Define an interface for creating an object, but let subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses.
- Defining a "virtual" constructor.
- The new operator considered harmful.

#### Problem ####
>A framework needs to standardize the architectural model for a range of applications, but allow for individual applications to define their own domain objects and provide for their instantiation.

#### Examples ####
[RestaurantFactory](https://github.com/metova/design-patterns-sample/blob/feature/android/Android/Sample-Android/designpatterns/src/main/java/com/metova/designpatterns/data/RestaurantFactory.java)

## Adapter ##
Reference: [Adapter Pattern](https://sourcemaking.com/design_patterns/adapter)

#### Intent ####
>- Convert the interface of a class into another interface clients expect. 
- Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.
- Wrap an existing class with a new interface.
Impedance match an old component to a new system

#### Problem ####
>An "off the shelf" component offers compelling functionality that you would like to reuse, but its "view of the world" is not compatible with the philosophy and architecture of the system currently being developed.

#### Examples ####
- [ListAdapter - Android](https://github.com/metova/design-patterns-sample/blob/master/Android/Sample-Android/designpatterns/src/main/java/com/metova/designpatterns/ui/ListAdapter.java)

## Flyweight ##
Reference: [Flyweight Pattern](https://sourcemaking.com/design_patterns/flyweight)

#### Intent ####
>- Use sharing to support large numbers of fine-grained objects efficiently.
- The Motif GUI strategy of replacing heavy-weight widgets with light-weight gadgets.

#### Problem ####
>Designing objects down to the lowest levels of system "granularity" provides optimal flexibility, but can be unacceptably expensive in terms of performance and memory usage.

#### Examples ####
[ViewHolder - Android](https://github.com/metova/design-patterns-sample/blob/master/Android/Sample-Android/designpatterns/src/main/java/com/metova/designpatterns/ui/ViewHolder.java#L10)

## Observer ##

#### Intent ####
>- Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
- Encapsulate the core (or common or engine) components in a Subject abstraction, and the variable (or optional or user interface) components in an Observer hierarchy.
- The "View" part of Model-View-Controller.

#### Problem ####
>A large monolithic design does not scale well as new graphing or monitoring requirements are levied.

#### Examples ####
[NSNotificationCenter - iOS]()

## Delegation ##
Reference: [Delegation Pattern](https://developer.apple.com/library/ios/documentation/General/Conceptual/DevPedia-CocoaCore/Delegation.html)

#### Intent ####
>- Allow an object to act on behalf of, or in coordination with, another object.
- Clients can customize behavior of another object while remaining fairly decoupled.

#### Problem ####
> You want to customize the behavior of an object when inheritance is either not feasible, or inheritance doesn't allow an object to maintain a separation of concerns.

#### Examples ####
[UITableView - iOS](https://github.com/metova/design-patterns-sample/blob/master/iOS/DesignPatternsSample/DesignPatternsSample/RestaurantListViewController.swift#L71)
