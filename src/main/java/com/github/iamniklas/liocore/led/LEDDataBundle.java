package com.github.iamniklas.liocore.led;

import com.github.iamniklas.liocore.interpolation.InterpolationType;
import com.github.iamniklas.liocore.led.colorspace.ColorRGB;
import com.github.iamniklas.liocore.procedures.ProcedureCalls;
import com.github.iamniklas.liocore.procedures.models.Direction;

public class LEDDataBundle {
    public ColorRGB colorPrimary;
    public ColorRGB colorSecondary;
    public ColorRGB colorTertiary;
    public String data;
    public Float value1;
    public Float value2;
    public Float value3;
    public Integer modulo;
    public InterpolationType interpolation;
    public Direction direction;
    public Integer bpm;
    public Float repetitions;
    public Float speed;
    public Integer duration;
    public Boolean pulsating;
    public String path;

    //subBundle
    public Boolean isSubProcedure;
    public Boolean indeterminate;
    public Integer puModulo;
    public Boolean puModuloInvert;
    public ILedStripManager ledStrip;
    public ProcedureCalls procedureCalls;
}