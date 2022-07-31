package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.procedures.Procedure;
import com.github.iamniklas.liocore.procedures.models.Direction;

public class RaveProcedure extends Procedure {
    /*
    Effects
    0 Very Fast Lightning ✓
    1 Rainbow Mono ✓
    2 Rainbow ✓
    3 Random Color Blocks ✓
    4 Rainbow Mono ✓
    5 Rainbow ✓
    6 Random Color Blocks ✓
    
    Starting slowly, getting faster after some iterations until max speed is reached
     */
    
    private float startSpeed = 125; //ms
    private float maxSpeed = 50; //ms
    private float currentSpeed;
    
    private Procedure[] procedures;
    private Procedure activeProcedure;
    private int idx = 0;

    private long time = 0;

    public RaveProcedure(LEDDataBundle ledDataBundle) {
        super(ledDataBundle);
        
        procedures = new Procedure[7];

        LEDDataBundle proc0Bundle = new LEDDataBundle();
        proc0Bundle.ledStrip = ledDataBundle.ledStrip;
        proc0Bundle.procedureCalls = ledDataBundle.procedureCalls;
        proc0Bundle.duration = 1;
        proc0Bundle.speed = 1.48f;
        proc0Bundle.value1 = 0.05f;
        proc0Bundle.puModulo = 1;
        proc0Bundle.puModuloInvert = true;
        procedures[0] = new LightningProcedure(proc0Bundle);

        LEDDataBundle proc1Bundle = new LEDDataBundle();
        proc1Bundle.ledStrip = ledDataBundle.ledStrip;
        proc1Bundle.procedureCalls = ledDataBundle.procedureCalls;
        proc1Bundle.speed = 4f;
        proc1Bundle.puModulo = 0;
        proc1Bundle.puModuloInvert = true;
        procedures[1] = new RainbowMonoProcedure(proc1Bundle);
        procedures[4] = procedures[1];

        LEDDataBundle proc2Bundle = new LEDDataBundle();
        proc2Bundle.ledStrip = ledDataBundle.ledStrip;
        proc2Bundle.procedureCalls = ledDataBundle.procedureCalls;
        proc2Bundle.speed = 4f;
        proc2Bundle.direction = Direction.Right;
        proc2Bundle.repetitions = 1f;
        proc2Bundle.puModulo = 10;
        proc2Bundle.puModuloInvert = true;
        procedures[2] = new RainbowProcedure(proc2Bundle);
        procedures[5] = procedures[2];

        LEDDataBundle proc3Bundle = new LEDDataBundle();
        proc3Bundle.ledStrip = ledDataBundle.ledStrip;
        proc3Bundle.procedureCalls = ledDataBundle.procedureCalls;
        proc3Bundle.duration = 3;
        proc3Bundle.bpm = 128;
        proc3Bundle.modulo = 2;
        procedures[3] = new RandomColorBlocksProcedure(proc3Bundle);
        procedures[6] = procedures[3];
    }
    
    @Override
    public void update() {
        if(time == 0) {
            time = System.currentTimeMillis();
            activeProcedure = procedures[idx];
        }

        activeProcedure.update();

        if(System.currentTimeMillis() - time > startSpeed) {
            time = 0;
            idx = idx == 6 ? 0 : idx + 1;
        }
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle bundle) {

    }
}
