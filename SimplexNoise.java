public class SimplexNoise {
    private int numOct;
    private double frequency,pertinance;
    private SimplexOctave octave;
    public SimplexNoise(int seed, int numOctanves, double f,double pert){
        octave = new SimplexOctave(seed);
        numOct = numOctanves;
        frequency = f;
        pertinance = pert;
    }

    public double getNoise(double x, double  y) {
        double res = 0;
        double p = 1;
        double s = frequency;

        for (int i = 1; i <= numOct; i++) {
            res += octave.noise(x * s, y * s) * p;
            p *= pertinance;
            s *= 2;
        }
        res = Math.max(Math.min(res, 1d),-1d);
        return res;
    }
}
