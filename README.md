# StackView

```StackFragment``` is a UI Component supporting expand and collapse states with a child fragment in it.


Introduction
------------
In collapse state It accepts another fragment as it's child fragment (make sure you add child fragment using ```childFragmentManager```).

### Features
1. When Collapsed View is clicked can call expandAndDetachChildren() function to expand the current view and remove its children.
2. Child view can be any fragment or even StackFragment itself to create a complex view type structure.
    *(make sure the view is visible to the user when all StackFragments stack up)*.
3. onBackPress is also handled within the StackFragment to remove its top most child first.
4. Customise option is present, can do what ever you like to.

![DEMO](https://user-images.githubusercontent.com/25269643/129148506-f9cf58f7-9d11-496e-a337-c0ded952e31e.gif)

------------
Concept of using fragments is state on orientation change, animations, and back press can be handled on its own and handy individually.
