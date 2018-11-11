package ehrenserver;

/**
 *
 * @author sreis
 */
public class Main {

    /**maximum framerate. -1 for unlimited FPS.*/
    private static float FPS_CAP = 60;
    
    public static Ehre ehre;
    
    public static void main(String[] args) {

        ehre = new Ehre();
        ehre.start();
        
        long last_time = System.nanoTime();
        while (true) {
            long time = System.nanoTime();
            float delta_time = ((time - last_time) / 1000000000f);

            if (FPS_CAP != -1 && delta_time < 1f / FPS_CAP) {
                continue;
            }

            last_time = time;
            ehre.update(delta_time);
        }
    }
}
