package decepticons;

import java.awt.Color;
import robocode.*;
import robocode.util.Utils;

public class SniperTron extends TeamRobot {

    public void run() {

        // Configura a arma e o radar para girarem independentemente
        setAdjustGunForRobotTurn(true);
        
        setBodyColor(Color.gray);
        setRadarColor(Color.yellow);
        setGunColor(Color.green);

        while (true) {
            // Procura inimigos com o radar
            turnGunLeft(360);

        }
    }

    /* Executado quando um inimigo é detectado */

    public void onScannedRobot(ScannedRobotEvent e) {
		if (isTeammate(e.getName())) {
			return;
		}

        // DEMONSTRAR DETECÇÃO

        setRadarColor(Color.red);
        
        fire(3);
        turnGunRight(180);
        setRadarColor(Color.yellow);
    }

    /* Executado quando o robô é atingido por um tiro */

    public void onBulletHit(BulletHitEvent e){
        setGunColor(Color.red);
        turnRight(360);
        setGunColor(Color.green);
    }

    public void onHitByBullet(HitByBulletEvent e) {
        setBodyColor(Color.red);
        back(20);
        turnRight(45);
        ahead(180);
        setBodyColor(Color.gray);
    }

    /* Executado quando o robô bate em uma parede */
	
    public void onHitWall(HitWallEvent e) {
        turnRight(180);
        back(50);
    }
}
