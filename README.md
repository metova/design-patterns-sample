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

## Factory ##

## Adapter ##
- [ListAdapter - Android](https://github.com/metova/design-patterns-sample/blob/master/Android/Sample-Android/designpatterns/src/main/java/com/metova/designpatterns/ui/HotChickenListAdapter.java)

## Flyweight ##
