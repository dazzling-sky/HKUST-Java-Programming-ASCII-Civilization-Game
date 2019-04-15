package pa1.ministers;


        import pa1.City;
        import pa1.Cost;
        import pa1.Player;
        import pa1.exceptions.TooPoorException;
        import java.lang.Math;

public class Economist extends Minister {

    /**
     * Call the superclass' constructor
     * @param intelligence
     * @param experience
     * @param leadership
     */
    public Economist(int intelligence, int experience, int leadership) {
        // TODO
        super(intelligence, experience, leadership);
    }

    /**
     * @return improvement discount rate equals to 1 - (intelligence + experience) / 1500
     */
    @Override
    public double getImprovementDiscountRate() {
        // TODO
        double percentage = ((double)(this.intelligence + this.experience) / 1500);
        double discountRate = 1- percentage;
        return discountRate;
    }

    /**
     * @param city to collect gold from
     * @return 1.5 times the amount collected by other types of minister
     */
    @Override
    public int collectTax(City city) {
        // TODO
        return (int)(super.collectTax(city)*1.5);
    }


    /**
     * Economists get a bonus when doing crops improvements
     * Crop improvement still costs 500 gold
     * Crop is improved by 50 + round((intelligence + experience + leadership) * 0.2)
     * If the player does not have enough gold, throw an exception
     * Else decrease 500 golds from the player and improves crops by the calculated amount
     *
     * @param player
     * @param city
     * @throws TooPoorException
     */
    @Override
    public void improveCropYield(Player player, City city) throws TooPoorException {
        // TODO
        if(player.getGold() < 500){
            throw new TooPoorException(player, new Cost(500, 0, 0));
        }
        else{
            player.decreaseGold(500);
            city.improveCrops((int)(50 + Math.round((this.intelligence + this.experience + this.leadership) * 0.2)));
        }
    }

    /**
     * Example string representation:
     * "Economist | intelligence: 100 | experience: 100 | leadership: 100 | READY" - when isReady() == true
     * "Economist | intelligence: 100 | experience: 100 | leadership: 100 | DONE" - when isReady() == false
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        // TODO
        return String.format("%s | intelligence: %d | experience: %d | leadership: %d | %s",
                "Economist", this.intelligence, this.experience, this.leadership, super.isReady() == true ? "READY" : "DONE");
    }
}
