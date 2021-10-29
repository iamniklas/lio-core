package com.github.iamniklas.liocore.procedures;

import com.github.iamniklas.liocore.led.LEDDataBundle;

public abstract class Procedure {
    //TODO
    //LEDStripManager strip;
    LEDDataBundle dataBundle;
    ProcedureCalls procCalls;
    int step = 0;
    int steps = 0;
    int modulo = 1;
    boolean moduloInvert = false;
    boolean isSubProcedure = false;

    public Procedure(LEDDataBundle _bundle) {
        dataBundle = _bundle;

        //TODO Init. Strip
        procCalls = _bundle.procedureCalls;
        if(_bundle.puModulo != null) {
            modulo = _bundle.puModulo;
        }
        if(_bundle.puModuloInvert != null) {
            moduloInvert = _bundle.puModuloInvert;
        }
    }

    public abstract void start();
    public abstract void update();

    public void postUpdate() {
        if (modulo < 2) return;
        //TODO Get LED Strip size from LEDManager
        for (int i = 0; i < 300; i++) {
            if (moduloInvert) {
                if (i % modulo != 0) {
                    //mStrip!!.setPixel(i, com.github.iamniklas.liocorekotlin.led.ColorRGB.black.toSystemColor())
                }
            } else {
                if (i % modulo == 0) {
                    //mStrip!!.setPixel(i, com.github.iamniklas.liocorekotlin.led.ColorRGB.black.toSystemColor())
                }
            }
        }
        if (step > steps) {
            finishProcedure();
        }
    }

    protected void finishProcedure(boolean _clearStrip) {
        //TODO Impl. Strip Interface
        //mStrip!!.mProcContainer.removeCurrentProcedure()
        if (_clearStrip) {
            //mStrip!!.setAllPixels(com.github.iamniklas.liocorekotlin.led.ColorRGB.black.toSystemColor())
        }
        procCalls.onProcedureFinish();
    }

    protected void finishProcedure() {
        //TODO Impl. Strip Interface
        //mStrip!!.mProcContainer.removeCurrentProcedure()
        procCalls.onProcedureFinish();
    }
}