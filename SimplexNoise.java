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
        double total = 0;
        for (int i = 1; i <= numOct; i++) {
            res += octave.noise(x * s, y * s) * p;
            total += 1*p;
            p *= pertinance;
            s *= 2;
        }
        res =res/total;
        if(Math.abs(res)>1)
        {
            System.out.println(res);
        }
        return res;
    }
}
