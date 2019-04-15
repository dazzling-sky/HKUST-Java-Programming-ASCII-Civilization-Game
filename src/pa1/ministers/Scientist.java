package pa1.ministers;


import pa1.City;

public class Scientist extends Minister {

    /**
     * Calls the superclass' constructor
     *
     * @param intelligence
     * @param experience
     * @param leadership
     */
    public Scientist(int intelligence, int experience, int leadership) {
        // TODO
        super(intelligence, experience, leadership);
    }


    /**
     * @return tech discount rate equals to 1 - (intelligence + experience) / 1500
     */
    @Override
    public double getTechDiscountRate() {
        // TODO
        double percentage = ((double)(this.intelligence + this.experience)/1500);
        double discountRate = 1 - percentage;
        return discountRate;
    }

    /**
     * @param city to collect science points from
     * @return 1.5 times the amount collected by other types of minister
     */
    @Override
    public int collectSciencePoints(City city) {
        // TODO

        return (int)(super.collectSciencePoints(city)*1.5);
    }

    /**
     * Example string representation:
     * "Scientist | intelligence: 100 | experience: 100 | leadership: 100 | READY" - when isReady() == true
     * "Scientist | intelligence: 100 | experience: 100 | leadership: 100 | DONE" - when isReady() == false
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        // TODO
        return String.format("%s | intelligence: %d | experience: %d | leadership: %d | %s",
                "Scientist", this.intelligence, this.experience, this.leadership, super.isReady() == true ? "READY" : "DONE");
    }
}
