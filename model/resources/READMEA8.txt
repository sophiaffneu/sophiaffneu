In this assignment we work on completing the controller with adding play back controls to the animation.

We chose to use JButtons, JLable and JSlider to complete the task. 

First of all,we created a new class NewView for the view with play back controls. Eight buttons and a slider with label was added into a newly created buttonPanel. Their functions are as followed;

START button: start the animation.
PAUSE button: pause the animation while it is playing.
RESUME button: resume playing from the last frame when paused.
RESTART button: start over the animation from beginning.
CYCLE button: start looping.
STOPCYCLE button: end looping.
SAVESVG button: save SVG file.
SAVETEXT button: save text file. 
Slider and label: control play back speed.

The controller implemented ActionListner and ChangeListner interfaces. It acts as action listner for all the buttons and change listner for the slider. In the controller, the individual view is called when requested. When the “play back” view is called, different controls will apply when the controller calls back to the call back functions corresponding to a certain button clicking.

The changes we’ve made to our previous assignment are as followed:

1)We still use one Iview interface for all the views. Methods specific to NewView (setters for buttons and slider) are added to Iview and implemented in all view classes.

2)In ViewFactory, added option of creating NewView instance.Changed text view and SVG view parameters from “ model.getShapeList(), model.getTransList()” to copies of both lists. This prevents any mutation to the Model.


 





