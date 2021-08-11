# StackView

```StackFragment``` is a Framework supporting expand and collapse states.

In collapse state It accepts another fragment as it's child fragment (make sure you add child fragment using ```childFragmentManager```).

Features:
1. When Collapsed View is clicked can call expandAndDetachChildren() function to expand the current view and remove its children.
2. Child view can be any framgent or even StackFragment itself to create a complex view type structure.
    *(make sure the view is visible to the user when all StackFragments stack up)*.
3. onBackPress is also handled within the StackFragment to remove its top most child first.
4. Customise option is present, can do what ever you like to.

Concept of using fragments is state on orientation change, animations, and back press can be handled on its own and handy individually.
