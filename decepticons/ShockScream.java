package decepticons;

import robocode.*;
import robocode.util.Utils;
import java.awt.Color;

public class ShockScream extends TeamRobot {

    public void run() {

        setBodyColor(Color.WHITE);
        setGunColor(Color.GRAY);
        setRadarColor(Color.WHITE);
        setBulletColor(Color.RED);
        setScanColor(Color.RED);

        setAdjustGunForRobotTurn(true);
        setAdjustRadarForRobotTurn(true);
        setAdjustRadarForGunTurn(true);

        while (true) {

            int i = (int) (Math.random() * 400);
            int j = (int) (Math.random() * 180);
            int k = (int) (Math.random() * 180);

            ahead(i); // se mexerá aleatoriamente p/ frente
            turnRadarRight(360); // gira o radar em 360 graus
            turnRight(j); // se mexerá aleatoriamente p/ direita
            turnRadarRight(360); // gira o radar em 360 graus
            back(k); // se mexerá aleatoriamente p/ trás

            turnRadarRight(360); // gira o radar em 360 graus
        }
    }

    // Detecta adversários
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {

        if (isTeammate(e.getName())) {
            return; // não faz nada se o alvo for um aliado
        }

        double direcao = getHeading() + e.getBearing(); // calcula a direção exata p/ o tiro

        atacar(direcao, e.getDistance()); // método criado exclusivamente p/ esse robô
    }

    // Método responsável por mirar e disparar
    public void atacar(double direcao, double distancia) {

        double giro = Utils.normalRelativeAngleDegrees(direcao - getGunHeading()); // calcula a rotação do canhão
        turnGunRight(giro); // executa a rotação

        if (getEnergy() > 3) { // dispara potência total se tiver energia p/ isso
            fire(3);
        } else if (getEnergy() > 0.5) { // senão, dispara com a potência menor
            fire(1);
        }
    }

    // Reage quando recebe um tiro
    @Override // reescrevendo uma classe pai
    public void onHitByBullet(HitByBulletEvent e) {
        back(150);
        turnRight(45);
    }

    // Reage quando bate em uma parede
    @Override
    public void onHitWall(HitWallEvent e) {

        int x = (int) (Math.random() * 200);
        int y = (int) (Math.random() * 180);

        back(x);
        turnRight(y);
    }
}
