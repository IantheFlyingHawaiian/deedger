package TweenAccessors;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * ValueAccessor
 * The ValueAccessor will be used whenever we need to
 * tween a float. For example if we want to flash a screen
 * using a white rectangle with changing opacity,
 * we will create a new Value object and tween it using our
 * ValueAccessor. In fact, we are going to do this to smoothly
 * transition into our GameScreen from the SplashScreen.
 *
 * Created by Ian on 3/16/2016.
 *
 */
public class ValueAccessor implements TweenAccessor<Value> {

    @Override
    public int getValues(Value target, int tweenType, float[] returnValues) {
        returnValues[0] = target.getValue();
        return 1;
    }

    @Override
    public void setValues(Value target, int tweenType, float[] newValues) {
        target.setValue(newValues[0]);
    }
}
