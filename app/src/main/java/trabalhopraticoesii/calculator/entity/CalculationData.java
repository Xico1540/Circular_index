package trabalhopraticoesii.calculator.entity;
import java.util.ArrayList;
import java.util.List;

import trabalhopraticoesii.calculator.exception.CalculationException;

public class CalculationData {
    private double V, R, RR, WF, WC, EP, ES, L, LAVG, U, UAVG;

    /**
     * Constructs a new {@code CalculationData} object with the specified parameters.
     *
     * @param r             The recycled materials.
     * @param rR            The recovered recycled materials.
     * @param wF            The waste in the production of secondary material.
     * @param wC            The waste from the recycling process.
     * @param eP            The energy required to produce the main materials/products.
     * @param eS            The energy required to produce secondary materials.
     * @param l             The product lifetime.
     * @param lAVG          The average product lifetime.
     * @param u             The product utility.
     * @param uAVG          The average product utility.
     * @param v             The virgin material flow value.
     */
    public CalculationData(double r, double rR, double wF, double wC, double eP, double eS, double l, double lAVG, double u, double uAVG, double v) {
        this.R = r;
        this.RR = rR;
        this.WF = wF;
        this.WC = wC;
        this.EP = eP;
        this.ES = eS;
        this.L = l;
        this.LAVG = lAVG;
        this.U = u;
        this.UAVG = uAVG;
        this.V = v;
    }

    /**
     * Calculates and returns a list of all calculated values based on the specified parameters.
     *
     * @return A list containing calculated values such as total material flow, waste, energy,
     *         and circularity indices.
     * @throws CalculationException If an error occurs during the calculation process.
     */
    public List<Double> calculateAll() throws CalculationException{
        List<Double> list = new ArrayList<>();
        if(getW() <= getR()){
            throw new CalculationException("An error occurred while calculating W.");
        }
        if(getW() > getM()){
            throw new CalculationException("An error occurred while calculating W.");
        }
        if(getM() < getV()){
            throw new CalculationException("An error occurred while calculating M.");
        }
        if(getWC() > getWF()){
            throw new CalculationException("An error occurred while calculating WC.");
        }
        if(getLFI() < 0 || getLFI() > 1){
            throw new CalculationException("An error occurred while calculating LFI.");
        }
        if(getMCI() < 0.1 || getMCI() > 1){
            throw new CalculationException("An error occurred while calculating MCI.");
        }
        list.add(getV());
        list.add(getW());
        list.add(getRi());
        list.add(getM());
        list.add(getX());
        list.add(getFx());
        list.add(getLFI());
        list.add(getMCI());
        list.add(getU());
        list.add(getL());
        list.add(getES());
        list.add(getEP());
        list.add(getR());
        list.add(getRR());
        list.add(getWF());
        list.add(getWC());
        return list;
    }

    /**
     * Calculates and returns the total waste to eliminate (W) based on the virgin material flow (V) and
     * the recovered recycled material flow (RR).
     *
     * @return The total waste to eliminate (W) value.
     */
    public double getW(){
        return (V - RR);
    }

    /**
     * Calculates and returns the input of recycled material (Ri) based on the recycled material flow (R)
     * and the recovered recycled material flow (RR).
     *
     * @return The input of recycled material (Ri) value.
     */
    public double getRi() {
        return (R + RR);
    }

    /**
     * Calculates and returns the total material mass (M) based on the virgin material flow (V) and
     * the input of recycled material (Ri).
     *
     * @return The total material mass (M) value.
     */
    public double getM(){
        return (V + getRi());
    }

    /**
     * Calculates and returns(X) based on the product lifetime (L), average product lifetime (LAVG), product utility (U), and average product utility (UAVG).
     *
     * @return The (X) value.
     */
    public double getX() {
        return ((L/LAVG)*(U/UAVG));
    }

    /**
     * Calculates and returns the material flow efficiency factor (Fx) based on the material efficiency (X).
     *
     * @return The material flow efficiency factor (Fx) value.
     */
    public double getFx() {
        return (0.9/getX());
    }

    /**
     * Calculates and returns the material flow efficiency factor (LFI) based on the virgin material flow (V),
     * recovered recycled material flow (RR), total material flow (M), waste in secondary material production (WF),
     * and waste in recycling process (WC).
     *
     * @return The material flow efficiency factor (LFI) value.
     */
    public double getLFI() {
        return (2 * V - RR)/(2 * getM() + (WF - WC/2));
    }

    /**
     * Calculates and returns the Material Circularity Index (MCI) based on the virgin material flow (V),
     * recovered recycled material flow (RR), total material flow (M), waste in secondary material production (WF),
     * and waste in recycling process (WC).
     *
     * @return The Material Circularity Index (MCI) value.
     */
    public double getMCI() {
        return (1 - (getLFI() * getFx()));
    }

    /**
     * Gets the product utility (u) value.
     *
     * @return The product utility value.
     */
    public double getU() {
        return U;
    }

    /**
     * Gets the product lifetime (L) value.
     *
     * @return The product lifetime value.
     */
    public double getL() {
        return L;
    }

    /**
     * Gets the energy required to produce secondary materials (ES) value.
     *
     * @return The energy required to produce secondary materials value.
     */
    public double getES() {
        return ES;
    }

    /**
     * Gets the energy required to produce the main materials/products (EP) value.
     *
     * @return The energy required to produce the main materials/products value.
     */
    public double getEP() {
        return EP;
    }

    /**
     * Gets the recycled materials (R) value.
     *
     * @return The recycled materials value.
     */
    public double getR() {
        return R;
    }

    /**
     * Gets the recovered recycled materials (RR) value.
     *
     * @return The recovered recycled materials value.
     */
    public double getRR() {
        return RR;
    }

    /**
     * Gets the waste in the production of secondary material (WF) value.
     *
     * @return The waste in the production of secondary material value.
     */
    public double getWF() {
        return WF;
    }

    /**
     * Gets the waste from the recycling process (WC) value.
     *
     * @return The waste from the recycling process value.
     */
    public double getWC() {
        return WC;
    }

    /**
     * Gets the virgin material flow (V) value.
     *
     * @return The virgin material flow value.
     */
    public double getV() {
        return V;
    }
}
