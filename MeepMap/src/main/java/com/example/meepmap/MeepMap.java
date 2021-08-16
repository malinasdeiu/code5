package com.example.meepmap;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;


public class MeepMap {
    public static void main(String[] args) {
        // TODO: If you experience poor performance, enable this flag
        // System.setProperty("sun.java2d.opengl", "true");

        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep mm = new MeepMeep(500,120)
                // Set field image
                .setBackground(MeepMeep.Background.FIELD_ULTIMATE_GOAL_DARK)
                // Set theme
                .setTheme(new ColorSchemeRedDark())
                // Background opacity from 0-1
                .setBackgroundAlpha(1f)
                // Set constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(100, 100, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-61.5, -47.5, 0))
                                .lineTo(new Vector2d(53,-56))
                                .waitSeconds(1.5)
                                .turn(Math.toRadians(-45))
                                .lineTo(new Vector2d(-7,-14))
                                .turn(Math.toRadians(-135))
                                .waitSeconds(4)
                                .lineTo(new Vector2d(-37,-24))
                                .waitSeconds(1.5)
                                .lineTo(new Vector2d(-38,-35.7))
                                .lineTo(new Vector2d(-4,-35.7))
                                .waitSeconds(4)
                                .lineToLinearHeading(new Pose2d(59,-51, Math.toRadians(-90)))
                                .waitSeconds(2)
                                .lineTo(new Vector2d(11,-15))
                                .build()
                )
                .start();
    }
}