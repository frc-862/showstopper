// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RobotMap.VisionConstants;

public class PhotonVision extends SubsystemBase {

    private PhotonCamera camera;

    private PhotonPipelineResult result;

    public PhotonVision() {
        camera = new PhotonCamera(VisionConstants.camera1Name);
    }

    public void initLogging() {

    }

    public boolean hasTarget() {
        return result.hasTargets();
    }

    public double getXBestTarget() {
        return result.getBestTarget().getYaw();
    }

    public double getYBestTarget() {
        return result.getBestTarget().getPitch();
    }

    public double getSkewBestTarget() {
        return result.getBestTarget().getSkew();
    }

    public Transform3d getTransformBestTarget() {
        return result.getBestTarget().getBestCameraToTarget();
    }
    
    

    @Override
    public void periodic() {
        result = camera.getLatestResult();
    }
}
