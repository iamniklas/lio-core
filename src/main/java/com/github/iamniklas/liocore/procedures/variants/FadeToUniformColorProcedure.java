package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.colorspaces.ColorRGBA;
import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

public class FadeToUniformColorProcedure extends Procedure {

    private LEDDataBundle bundle;

    private ColorRGB baseColor;
    private ColorRGB targetColor;
    private float duration = 0.0f;
    private int counter = 0;
    private float alphaStep = 0.0f;
    private float alphaAddValue = 0.0f;

    public FadeToUniformColorProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
        bundle = _ledUpdateModel.bundle;

        targetColor = bundle.colorPrimary;
        baseColor = bundle.colorSecondary;
        duration = bundle.duration;
        steps = (int) Math.ceil((duration / (ProgramConfiguration.configuration.frametime / 1000.0f)));
        alphaAddValue = 1 / (float)steps;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        counter++;

        alphaStep += alphaAddValue;
        ColorRGBA outputColor = new ColorRGBA(baseColor.r, baseColor.g, baseColor.b, (int)(alphaStep * 255));
        strip.setAllPixels(LIOColor.fromRGBA(outputColor));
        if(counter > steps) {
            strip.setAllPixels(LIOColor.fromRGB(baseColor));
            finishProcedure();
        }
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {

    }
}
