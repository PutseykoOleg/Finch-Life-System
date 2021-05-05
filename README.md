# Finch-Life-System
Simulator of the life of a Finch 1.0 robot with the ability to control it

## Initial setup

### Where can I get robot?

This project uses the Finch robot [version 1.0](https://store.birdbraintechnologies.com/collections/featured-items/products/finch-robot).
If you are sure that this system is compatible with robots of another generation, you can purchase another version of the robot [here](https://store.birdbraintechnologies.com/).


### API

You don't need to download API for this project, because it has already been downloaded and located in the `/libs/` and `/src/com/birdbraintechnologies/` folders. 

But if you would like to get a Finch robot API, you can download it [here](https://www.birdbraintechnologies.com/portal/finch1/program/).


### User development

**You have the ability to create new version of this system.**

#### Signals

The system has an abstract class `Signal`, object of which you cannot instantiate. Instead, you can create a new signal type by inheriting class `Signal`. 
Each new signal type will have a Singleton pattern structure. Therefore, you will need to write a `getInstance` method to get an instance of the class:

```Java
public static [new_class_name] getInstance(){
  return ([new_class_name]) Signal.getRootInstance(([new_class_name])_instance, new [new_class_name]()); 
}
 ```
