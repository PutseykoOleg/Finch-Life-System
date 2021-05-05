# Finch-Life-System
Simulator of the life of a Finch 1.0 robot with the ability to control it

## Initial setup

### Where can I get robot?

This project uses the Finch robot [version 1.0](https://store.birdbraintechnologies.com/collections/featured-items/products/finch-robot).
If you are sure that this system is compatible with robots of another generation, you can purchase another version of the robot [here](https://store.birdbraintechnologies.com/).

____
### API

You don't need to download API for this project, because it has already been downloaded and located in the `/libs/` and `/src/com/birdbraintechnologies/` folders. 

But if you would like to get a Finch robot API, you can download it [here](https://www.birdbraintechnologies.com/portal/finch1/program/).

____
### User development

**You have the ability to create new version of this system.**

#### - Signals

The system has an abstract class `Signal`, object of which you cannot instantiate. Instead, you can create a new signal type by inheriting class `Signal`. 
Each new signal type has to have a Singleton pattern structure. Therefore, you must make the constructor non-public and you will need to write a `getInstance` method to get an instance of the class:

```Java
public static [new_class_name] getInstance(){
  return ([new_class_name]) Signal.getRootInstance(([new_class_name])_instance, new [new_class_name]()); 
}
```

In order to describe the behavior of the robot (new signal), you need to override `action` method, in the body of which you should write all the logic of the behavior. It's important to remember that this method is already being called on a new thread!

```Java
protected synchronized void action(Finch targetFinch) {
  /*behavior of the robot*/
}
```

To use a new signal in your code, you need to assign an instance of the `Signal` class to the value returned by the `getInstance` method of the new signal class:

```Java
Signal signal = [new_class_name].getInstance();
```

To play a new signal , call the `play` method of the `signal` instance:

```Java
signal.play();
```
