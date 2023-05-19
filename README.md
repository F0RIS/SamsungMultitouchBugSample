# SamsungMultitouchBugSample
Sample project to demonstrade bug with multitouch coords when Game Booster Plus uses "Graphics quality" feature for the game. Video with the issue https://youtu.be/LxipWNW-C2E

### Intro
Samsung devices has a feature called "Graphics quality" that tricks apps to render in lower resolution for the sake of performance.
It can be controlled with Game Booster Plus app, rendering resolution can be changed in range between 30% and 100%. But the feature itself may be active without Game Booster Plus being installed.
Device may silently enable this feature for any game without user acknowledge to reduce rendering resolution and improve performance.

### When this bug appears?
1. Your app considered as a game[1]
2. "Graphics quality" is enabled for your app
3. You have native views in your layout (usually games render all UI with game engine in something similar to GLSurfaceView)
4. You multitouching between different views

[1] Just having isGame flag in manifest doesn't do the trick. Samsung has some additional logic of determining this. 
That's why I use here package name of real game project that Samsung already know

### Core of the bug
When you meet all conditions from the list above you are getting wrong touch coordinates in onTouch method when you multitouch between different views.
Wrong touch coords are dispatched to all views that are involved in multitouch.
This also leads to buttons sometimes not triggering their onClick listener because wrong touch coords can be outside of button area for ACTION_UP/ACTION_POINTER_UP

### What's the fix?
1. Avoid necessity of multitouching between native views in your game
2. Calculating coefficient of rendering downscale and apply it in case of multitouching between different views. This approach can be seeng in branch [Fix1](https://github.com/F0RIS/SamsungMultitouchBugSample/tree/Fix1)

#### You can read discussion of this bug here https://github.com/Swordfish90/Lemuroid/issues/178
