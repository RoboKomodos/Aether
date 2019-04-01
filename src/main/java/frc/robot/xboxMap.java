/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public enum xboxMap {
    lb(5),rb(6),x(3),y(4),a(1),b(2),rightJoystick(10),leftJoystick(9),start(8),select(6);
    public int value;
    private xboxMap(int v)
    {
        value = v;
    }
}
