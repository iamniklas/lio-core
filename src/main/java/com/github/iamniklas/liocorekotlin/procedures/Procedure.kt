package com.github.iamniklas.liocorekotlin.procedures

import com.github.iamniklas.liocorekotlin.led.ColorRGB
import com.github.iamniklas.liocorekotlin.led.LEDDataBundle
import com.github.iamniklas.liocorekotlin.led.LEDStripManager

abstract class Procedure(_bundle: com.github.iamniklas.liocorekotlin.led.LEDDataBundle) {
    var mStrip: com.github.iamniklas.liocorekotlin.led.LEDStripManager?
    var mCallbacks: com.github.iamniklas.liocorekotlin.procedures.ProcedureCalls?
    var mStep = 0
    open var mSteps = 0
    open var mModulo = 1
    var mModuloInvert = false
    protected var mIsSubProcedure = false
    abstract fun start()
    abstract fun update()
    fun postUpdate() {
        if (mModulo < 2) return
        for (i in 0 .. com.github.iamniklas.liocorekotlin.led.LEDStripManager.LED_COUNT) {
            if (mModuloInvert) {
                if (i % mModulo != 0) {
                    mStrip!!.setPixel(i, com.github.iamniklas.liocorekotlin.led.ColorRGB.black.toSystemColor())
                }
            } else {
                if (i % mModulo == 0) {
                    mStrip!!.setPixel(i, com.github.iamniklas.liocorekotlin.led.ColorRGB.black.toSystemColor())
                }
            }
        }
        if (mStep > mSteps) {
            finishProcedure()
        }
    }

    protected fun finishProcedure(_clearStrip: Boolean) {
        mStrip!!.mProcContainer.removeCurrentProcedure()
        if (_clearStrip) {
            mStrip!!.setAllPixels(com.github.iamniklas.liocorekotlin.led.ColorRGB.black.toSystemColor())
        }
        mCallbacks!!.onProcedureFinish()
    }

    protected fun finishProcedure() {
        mStrip!!.mProcContainer.removeCurrentProcedure()
        mCallbacks!!.onProcedureFinish()
    }

    init {
        mStrip = _bundle.ledStrip
        mCallbacks = _bundle.procedureCalls
        if (_bundle.puModulo != null) {
            mModulo = _bundle.puModulo!!
        }
        if (_bundle.puModuloInvert != null) {
            mModuloInvert = _bundle.puModuloInvert!!
        }
    }
}